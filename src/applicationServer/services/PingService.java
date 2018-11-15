package applicationServer.services;

import applicationServer.Service;

import java.io.InputStream;
import java.io.OutputStream;

public class PingService implements Service {

    public PingService(InputStream in, OutputStream out) {
    }

    @Override
    public boolean start() {
        return true;
    }
}
