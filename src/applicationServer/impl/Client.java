package applicationServer.impl;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Client implements applicationServer.Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    private boolean isShutdown = false;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    @Override
    public void run() {
        try {
            this.socket = new Socket("localhost", Server.LISTEN_PORT);
            this.inputStream = this.socket.getInputStream();
            this.outputStream = this.socket.getOutputStream();

            sendRequests();

        } catch (UnknownHostException e) {
            System.out.println("Could not resolve host!");
        } catch (IOException e) {
            System.out.println("Unexpected IO Exception occurred in Client");
        }
    }

    private void sendRequests() {
        List<String> commands = new ArrayList<>();
        commands.add("ping");

        writeCommands(commands);

        readResults();
    }

    private void readResults() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(this.inputStream, StandardCharsets.UTF_8)
            );

            while (!this.isShutdown) {
                String line = reader.readLine();
                System.out.println(line);
                sleep(100);
            }

        } catch (IOException e) {
            System.out.println("Error in getting inputStream at client");
        } catch (InterruptedException e) {
            System.out.println("Error when sleeping before next response-fetch in Client");
        }
    }

    private void writeCommands(List<String> commands) {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(this.outputStream, StandardCharsets.UTF_8)
        );

        commands.forEach(s -> {
            try {
                System.out.println("Client:\t" + s);
                writer.write(s);
                writer.flush();
            } catch (IOException e) {
                System.out.println("Error writing the command inside of Client");
            }
        });

    }

    @Override
    public void close() {
        this.isShutdown = true;
    }
}
