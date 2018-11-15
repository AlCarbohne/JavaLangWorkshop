package applicationServer.services;

import applicationServer.Service;

import java.io.*;
import java.time.LocalDateTime;

public class TimeOfDayService implements Service {
    BufferedReader inputStream;
    PrintWriter outputStream;

    public TimeOfDayService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        outputStream.println(LocalDateTime.now());
        outputStream.flush();
        return true;
    }
}
