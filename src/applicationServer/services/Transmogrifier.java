package applicationServer.services;

import applicationServer.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Function;

public class Transmogrifier {
    Function<String,String> transformer;
    public Transmogrifier(Function<String, String> transformer) {
        this.transformer = transformer;
    }

    public Service create(BufferedReader in, PrintWriter out) {
        return (
          () -> {
              String s;
              try {
                  while (!(s = in.readLine()).isEmpty()) {
                      out.println(transformer.apply(s));
                      out.flush();
                  }
              } catch (IOException e) {
                  return false;
              }
              return true;
          }
          );
    }
}
