package applicationServer.services;

import applicationServer.Service;

import java.net.Socket;

public class PingService implements Service {
    private Socket socket;

    public PingService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean start() {
        return true;
    }
}
