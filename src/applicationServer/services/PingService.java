package applicationServer.services;

import applicationServer.Service;
import applicationServer.impl.ServiceImpl;

import java.net.Socket;

public class PingService extends ServiceImpl implements Service {

    private Socket socket;

    public PingService(Socket socket) {
        super(socket);
        this.socket = socket;
    }

    @Override
    public boolean start() {
        //ToDO implement ping logic
        return super.start();
    }
}
