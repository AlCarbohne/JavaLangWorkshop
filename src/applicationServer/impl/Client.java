package applicationServer.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Client implements applicationServer.Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    private boolean isShutdown = false;

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", Server.LISTEN_PORT)) {

            while (!this.isShutdown) {
                sendRequests(socket);
            }

        } catch (UnknownHostException e) {
            System.out.println("Could not resolve host!");
        } catch (IOException e) {
            System.out.println("Unexpected IO Exception occurred in Client");
        }
    }

    private void sendRequests(Socket socket) {
        List<String> commands = new ArrayList<>();
        commands.add("ping");

        try (OutputStream outputStream = socket.getOutputStream()) {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            outputStream,
                            StandardCharsets.UTF_8)
            );

            commands.forEach(s -> {
                try {
                    writer.write(s);
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
