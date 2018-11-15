package applicationServer.impl;

import applicationServer.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class ServiceImpl implements Service {

    private String name;
    private int socketNumber;
    private Socket socket;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean start(InputStream inStream, OutputStream outputStream) {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
