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
//        threadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        try {
            listenerSocket = new ServerSocket(LISTEN_PORT);
            while (true) {
                // TODO check the number of connections alive
                new ServerThread(listenerSocket.accept()).start();
            }
        } catch (IOException e) {
            log("FATAL: encountered exception while trying to bind to listening port, exiting");
//            threadPool.shutdownNow();
        }
    }

    private static void log(String s) {
        System.out.println(s);
    }

    class ServerThread extends Thread {
        private Socket socket;
        private ServerThread(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            // read from socket and
            // wait for application service request
            /*
                An application service request must be the first message from the client,
                terminated by a newline. The message must contain the name of an available
                service or the string "ls" to list all available services on the server.
             */
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                String command = in.readLine();
                ServiceFactory sf = serviceMap.get(command);
                if (sf == null) {
                    out.println("Service \"" + command + "\" does not exist");
                    out.flush();
                } else {
                    out.println("OK");
                    out.flush();
                    out.close();
                    in.close();
                    Service service = sf.create(socket);
                    service.start();
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
