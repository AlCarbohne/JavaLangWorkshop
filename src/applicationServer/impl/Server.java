package applicationServer.impl;

import applicationServer.Service;
import applicationServer.ServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class Server {

    // TODO add configurable listen ports and maximum connection numbers
    public static final int LISTEN_PORT = 8000;
    public static final int MAX_CONNECTIONS = 12;

    private ServerSocket listenerSocket;
    private final ExecutorService threadPool;
    private final Semaphore connections;

    public static void main(String[] args) throws IOException {
        new Server().run();
    }

    public Server() throws IOException {
        // bind to server port
        this.connections = new Semaphore(MAX_CONNECTIONS);
        this.threadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        try {
            this.listenerSocket = new ServerSocket(LISTEN_PORT);
            log("Created " + listenerSocket);
        } catch (IOException e) {
            System.err.println("FATAL: encountered exception while trying to bind to listening port, exiting");
            // destroy *EVERYTHING*
            this.threadPool.shutdownNow();
            throw e;
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
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
            log("Accepted connection from " + socket);
            String command = in.readLine();
            ServiceFactory sf = ServiceMap.get(command);
            if (sf == null) {
                out.println("Service \"" + command + "\" does not exist");
                out.flush();
            } else {
                out.println("OK");
                out.flush();
                log("Service \"" + command + "\" starting");
                Service service = sf.create(socket);
                service.start();
                log("Service \"" + command + "\" exiting");
                socket.close();
                this.connections.release();
                log("Closed connection to " + socket);
            }
        } catch (IOException e) {
            log("IOException while negotiating with client: " + e.getMessage());
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                //eat the exception
                log("IOException while closing socket: " + e.getMessage());
            }
        }
    }

    static void log(String msg) {
        System.out.println(String.format("[%1$tF %1$tT.%1$tL %2$s] %3$s", LocalDateTime.now(), Thread.currentThread().getName(), msg));
    }

}
