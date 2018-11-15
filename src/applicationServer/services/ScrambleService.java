package applicationServer.services;

import applicationServer.Service;

import java.net.Socket;

public class ScrambleService implements Service {
    private Socket socket;

    public ScrambleService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean start() {
        return super.start();
    }
}
