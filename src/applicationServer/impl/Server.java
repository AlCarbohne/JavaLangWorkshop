package applicationServer.impl;

import applicationServer.Service;
import applicationServer.ServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;


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
            this.listenerSocket = new ServerSocket(LISTEN_PORT);
            while (true) {
                // TODO check the number of connections alive
                new ServerThread(this.listenerSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("FATAL: encountered exception while trying to bind to listening port, exiting");
//            threadPool.shutdownNow();
        }
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
                    BufferedReader in = new LoggingBufferedReader(new InputStreamReader(this.socket.getInputStream()));
                    PrintWriter out = new LoggingPrintWriter(new OutputStreamWriter(this.socket.getOutputStream()))
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
                    this.socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class LoggingBufferedReader extends BufferedReader {
        public LoggingBufferedReader(Reader in) {
            super(in);
        }
        @Override
        public String readLine() throws IOException {
            String s = super.readLine();
            System.out.println("[" + Thread.currentThread().getName() + ", read] " + s);
            return s;
        }
    }

    private class LoggingPrintWriter extends PrintWriter {
        public LoggingPrintWriter(Writer out) {
            super(out);
        }
        @Override
        public void println(String s) {
            System.out.println("[" + Thread.currentThread().getName() + ", write] " + s);
            super.println(s);
        }
    }
}
