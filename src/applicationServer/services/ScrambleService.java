package applicationServer.services;

import applicationServer.Service;

import java.io.*;

public class ScrambleService implements Service {
    BufferedReader inputStream;
    PrintWriter outputStream;

    public ScrambleService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        try {
            String line;
            while (!(line = inputStream.readLine()).isEmpty()) {
                // TODO actually do something here. anything.
                outputStream.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
