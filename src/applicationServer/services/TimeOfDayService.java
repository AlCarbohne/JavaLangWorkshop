package applicationServer.services;

import applicationServer.Service;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class TimeOfDayService implements Service {
    InputStream inputStream;
    OutputStream outputStream;

    public TimeOfDayService(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        try (
                PrintWriter out = new PrintWriter(new OutputStreamWriter(this.outputStream))
        ) {
            out.println(LocalDateTime.now());
            out.flush();
        }
        return true;
    }
}
