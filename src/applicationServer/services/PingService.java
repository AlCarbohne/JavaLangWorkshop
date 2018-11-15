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
        //ToDO implement ping logic
        return false;
    }
}
