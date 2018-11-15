package applicationServer.impl;

import java.io.PrintWriter;
import java.io.Writer;

public class LoggingPrintWriter extends PrintWriter {
    public LoggingPrintWriter(Writer out) {
        super(out);
    }
    @Override
    public void println(String s) {
        System.out.println("[" + Thread.currentThread().getName() + ", write] " + s);
        super.println(s);
    }
}