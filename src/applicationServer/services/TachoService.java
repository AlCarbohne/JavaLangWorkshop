package applicationServer.services;

import applicationServer.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TachoService implements Service {
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    public TachoService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        try {
            String s;
            while (!(s = this.inputStream.readLine()).isEmpty()) {
                /*
                 * just send a message to acknowledge and do not process it
                 */
                this.outputStream.println("OK");
                this.outputStream.flush();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}