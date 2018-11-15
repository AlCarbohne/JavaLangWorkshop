package applicationServer.impl;

import applicationServer.Service;

import java.net.Socket;

public abstract class ServiceImpl implements Service {

    private String name;
    private int socketNumber;
    private Socket socket;

    public ServiceImpl(String name, int socketNumber, Socket socket) {
        this.name = name;
        this.socketNumber = socketNumber;
        this.socket = socket;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
