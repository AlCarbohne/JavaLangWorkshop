package applicationServer.services;

import applicationServer.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TachoService implements Service {
    BufferedReader inputStream;
    PrintWriter outputStream;

    public TachoService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        try {
            String s;
            while (!(s = this.inputStream.readLine()).isEmpty()) {
                /**
                 *
                 */
                this.outputStream.println("OK");
                this.outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}