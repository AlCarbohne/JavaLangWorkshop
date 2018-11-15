package applicationServer.services;

import applicationServer.Service;

import java.net.Socket;

public class UppercaseService implements Service {
    Socket socket;

    public UppercaseService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean start() {
        return false;
    }
}
