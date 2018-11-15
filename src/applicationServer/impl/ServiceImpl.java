package applicationServer.impl;

import applicationServer.Service;

import java.net.Socket;

public abstract class ServiceImpl implements Service {

    private Socket socket;

    public ServiceImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
