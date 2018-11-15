package applicationServer.services;

import applicationServer.Service;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class TachoService implements Service {
    BufferedReader inputStream;
    PrintWriter outputStream;

    public TachoService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
}