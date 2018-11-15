package applicationServer.services;

import applicationServer.Service;

import java.io.*;
import java.net.Socket;

public class UppercaseService implements Service {
    InputStream inputStream;
    OutputStream outputStream;

    public UppercaseService(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(this.inputStream));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(this.outputStream))
        ) {
            String s;
            while (!(s = in.readLine()).isEmpty()) {
                out.println(s.toUpperCase());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
