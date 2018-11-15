package applicationServer.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class LoggingBufferedReader extends BufferedReader {
    public LoggingBufferedReader(Reader in) {
        super(in);
    }
    @Override
    public String readLine() throws IOException {
        String s = super.readLine();
        System.out.println("[" + Thread.currentThread().getName() + ", read] " + s);
        return s;
    }
}
