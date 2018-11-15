package applicationServer.impl;

import applicationServer.Service;
import applicationServer.ServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    // TODO add configurable listen ports and maximum connection numbers
    public static final int LISTEN_PORT = 8000;
    public static final int MAX_CONNECTIONS = 12;

    private static Map<String, ServiceFactory> serviceMap = ServiceMap.get();

    private ServerSocket listenerSocket;
    private ExecutorService threadPool;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        // bind to server port
        threadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        try {
            this.listenerSocket = new ServerSocket(LISTEN_PORT);
            while (true) {
                // TODO check the number of connections alive
                try {
                    Socket s = listenerSocket.accept();
                    threadPool.submit(() -> runServerThread(s));
                } catch (IOException e) {
                    System.err.println("ERROR: encountered exception while trying to establish connection");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("FATAL: encountered exception while trying to bind to listening port, exiting");
            e.printStackTrace();
            threadPool.shutdownNow();
        }
    }

    public void runServerThread(Socket socket) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
