package applicationServer.services;

import applicationServer.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ScrambleService implements Service {
    InputStream inputStream;
    OutputStream outputStream;

    public ScrambleService(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        return false;
    }
}
