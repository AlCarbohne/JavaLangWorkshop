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

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", Server.LISTEN_PORT)) {

            sendRequests(socket);

        } catch (UnknownHostException e) {
            System.out.println("Could not resolve host!");
        } catch (IOException e) {
            System.out.println("Unexpected IO Exception occurred in Client");
        }
    }

    private void sendRequests(Socket socket) {
        List<String> commands = new ArrayList<>();
        commands.add("ping");

        writeCommands(socket, commands);

        readResults(socket);
    }

    private void readResults(Socket socket) {
        try (InputStream inputStream = socket.getInputStream()) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            inputStream,
                            StandardCharsets.UTF_8)
            );

            while (!this.isShutdown) {
                System.out.println(reader.readLine());
                sleep(100);
            }

        } catch (IOException e) {
            System.out.println("Error in getting inputStream at client");
        } catch (InterruptedException e) {
            System.out.println("Error when sleeping before next response-fetch in Client");
        }
    }

    private void writeCommands(Socket socket, List<String> commands) {
        try (OutputStream outputStream = socket.getOutputStream()) {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            outputStream,
                            StandardCharsets.UTF_8)
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

        } catch (IOException e) {
            System.out.println("OutputStream of Client failed");
        }
    }

    @Override
    public void close() {
        this.isShutdown = true;
    }
}
