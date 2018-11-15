package applicationServer.services;

import applicationServer.Service;

import java.io.*;
import java.net.Socket;

public class UppercaseService implements Service {
    Socket socket;

    public UppercaseService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean start() {
        try (
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            String s;
            while (!(s = in.readLine()).isEmpty()) {
                out.println(s.toUpperCase());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
