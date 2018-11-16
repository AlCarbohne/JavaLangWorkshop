package applicationServer.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

class LoggingBufferedReader extends BufferedReader {
    LoggingBufferedReader(Reader in) {
        super(in);
    }
    @Override
    public String readLine() throws IOException {
        String s = super.readLine();
        Server.log("read << " + s);
        return s;
    }
}
