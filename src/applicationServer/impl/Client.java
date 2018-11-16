package applicationServer.impl;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            Socket socket = new Socket("localhost", Server.LISTEN_PORT);
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (UnknownHostException e) {
            System.out.println("Could not resolve host!");
        } catch (IOException e) {
            System.out.println("Unexpected IO Exception occurred in AbstractClient");
        }
    }

    private boolean isShutdown = false;
    private InputStream inputStream;
    private OutputStream outputStream;

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
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(this.inputStream, StandardCharsets.UTF_8)
            );

            if (!this.isShutdown) {
                String line = reader.readLine();
                if (line != null) {
                    return line;
                }
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
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(this.outputStream, StandardCharsets.UTF_8)
        );

        try {
            writer.write(command + "\n");
            writer.flush();
            System.out.println("AbstractClient:\t" + command);
        } catch (IOException e) {
            System.out.println("Error writing the command inside of AbstractClient");
        }

    }

    public void close() {
        this.isShutdown = true;
    }
}
