package applicationServer.services;

import applicationServer.Service;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class PingService implements Service {

    public PingService(BufferedReader in, PrintWriter out) {
    }

    @Override
    public boolean start() {
        return true;
    }
}
