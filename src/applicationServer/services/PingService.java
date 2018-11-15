package applicationServer.services;

import applicationServer.impl.ServiceImpl;

import java.net.Socket;

public class PingService extends ServiceImpl {

    private Socket socket;

    public PingService(String name, int socketNumber, Socket socket) {
        super(name, socketNumber, socket);
        this.socket = socket;
    }

    @Override
    public boolean start() {
        //ToDO implement ping logic
        return super.start();
    }
}
