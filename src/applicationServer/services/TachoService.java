package applicationServer.services;

import applicationServer.Service;

import java.io.InputStream;
import java.io.OutputStream;

public class TachoService implements Service {
    InputStream inputStream;
    OutputStream outputStream;

    public TachoService(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
}