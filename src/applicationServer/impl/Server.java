package applicationServer.impl;

import applicationServer.Service;
import applicationServer.ServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class Server {

    // TODO add configurable listen ports and maximum connection numbers
    private static final int LISTEN_PORT = 8000;
    private static final int MAX_CONNECTIONS = 12;

    private static Map<String, ServiceFactory> serviceMap = ServiceMap.get();

    private ServerSocket listenerSocket;
    private final ExecutorService threadPool;
    private final Semaphore connections;

    public static void main(String[] args) {
        new Server().run();
    }

    public Server() {
        // bind to server port
        this.connections = new Semaphore(MAX_CONNECTIONS);
        this.threadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        try {
            this.listenerSocket = new ServerSocket(LISTEN_PORT);
        } catch (IOException e) {
            System.err.println("FATAL: encountered exception while trying to bind to listening port, exiting");
            e.printStackTrace();
            this.threadPool.shutdownNow();
        }
    }

    static int getListenPort() {
        return LISTEN_PORT;
    }

    public void run() {
        while (true) {
            try {
                // acquire license to accept a connection from the connections semaphore
                // NOTE: it is the connection thread's responsibility to release this
                // license, i.e. to call connections.release()!
                this.connections.acquire();
                Socket s = this.listenerSocket.accept();
                this.threadPool.submit(() -> runServerThread(s));
            } catch (IOException e) {
                System.err.println("ERROR: encountered exception while trying to establish connection");
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.err.println();
                e.printStackTrace();
            }
        }
    }

    private void runServerThread(Socket socket) {
        // read from socket and
        // wait for application service request
        /*
            An application service request must be the first message from the client,
            terminated by a newline. The message must contain the name of an available
            service or the string "ls" to list all available services on the server.
         */
        try (
                BufferedReader in = new LoggingBufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new LoggingPrintWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String command = in.readLine();
            ServiceFactory sf = serviceMap.get(command);
            if (sf == null) {
                out.println("Service \"" + command + "\" does not exist");
                out.flush();
            } else {
                out.println("OK");
                out.flush();
                Service service = sf.create(in, out);
                service.start();
                socket.close();
                this.connections.release();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
