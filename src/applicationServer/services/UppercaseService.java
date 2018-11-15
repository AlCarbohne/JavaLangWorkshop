package applicationServer.services;

import applicationServer.Service;

import java.io.*;

public class UppercaseService implements Service {
    BufferedReader inputStream;
    PrintWriter outputStream;

    public UppercaseService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        try  {
            String s;
            while (!(s = inputStream.readLine()).isEmpty()) {
                outputStream.println(s.toUpperCase());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
