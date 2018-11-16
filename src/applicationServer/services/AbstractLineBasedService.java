package applicationServer.services;

import applicationServer.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

abstract class AbstractLineBasedService implements Service {
    protected BufferedReader inputStream;
    protected PrintWriter outputStream;
    public AbstractLineBasedService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
    @Override
    public boolean start() {
        String line;
        try {
            while (!(line = inputStream.readLine()).isEmpty()) {
                outputStream.println(transmogrify(line));
                outputStream.flush();
            }
        } catch (IOException e) {
            System.err.println(this.getClass().getName() + " >> Encountered IOException while processing input.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    abstract String transmogrify(String line);
}
