package applicationServer.impl;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class AbstractClient {

    public static final String DEFAULT_HOST = "localhost";
    public static final short DEFAULT_PORT = Server.LISTEN_PORT;

    private boolean isShutdown = false;
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private Socket socket;

    public static void main(String[] args) {
        new Client();
    }

    public AbstractClient() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public AbstractClient(String hostName, short hostPort) {
        try {
            this.socket = new Socket(hostName, hostPort);
            this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Could not resolve host!");
        } catch (IOException e) {
            System.out.println("Unexpected IO Exception occurred in AbstractClient");
        }
    }

    public void run(Function<String,String> lineTransformer) {
        throw new UnsupportedOperationException("not yet");
    }

    /**
     * @param command which to send to the server
     * @return the answer from the server
     */
    public String sendRequests(String command) {
        writeCommand(command);
        return readResults();
    }

    private String readResults() {
        try {
            if (!this.isShutdown) {
                return inputStream.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error in getting inputStream at client");
        }
        return null;
    }

    /**
     * @param command the command to send the Server - appends a \n
     */
    private void writeCommand(String command) {
        outputStream.println(command);
        outputStream.flush();
        System.out.println("AbstractClient:\t" + command);
    }

    public void close() {
        this.isShutdown = true;
        // closing the output stream automagically frees the socket and
        // will never throw an exception
        this.outputStream.close();
    }
}
