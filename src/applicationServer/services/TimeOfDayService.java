package applicationServer.services;

import applicationServer.Service;

import java.net.Socket;

public class TimeOfDayService implements Service {
    private final Socket socket;

    public TimeOfDayService(Socket socket) {
        this.socket = socket;
    }
}
