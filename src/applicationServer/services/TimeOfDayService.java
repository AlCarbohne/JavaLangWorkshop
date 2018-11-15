package applicationServer.services;

import applicationServer.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class TimeOfDayService implements Service {
    private final Socket socket;

    public TimeOfDayService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean start() {
        try (
                PrintWriter out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()))
        ) {
            out.println(LocalDateTime.now());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
