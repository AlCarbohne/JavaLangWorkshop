package applicationServer.services;

import applicationServer.Service;

import java.net.Socket;

public class Tachoservice implements Service {
    private Socket socket;

    public Tachoservice(Socket socket) {
        this.socket = socket;
    }
}
