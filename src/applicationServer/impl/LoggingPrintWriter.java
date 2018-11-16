package applicationServer.impl;

import java.io.PrintWriter;
import java.io.Writer;

class LoggingPrintWriter extends PrintWriter {
    LoggingPrintWriter(Writer out) {
        super(out);
    }
    @Override
    public void println(String s) {
        Server.log("write >> " + s);
        super.println(s);
    }
}