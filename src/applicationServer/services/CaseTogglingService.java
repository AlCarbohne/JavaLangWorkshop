package applicationServer.services;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class CaseTogglingService extends AbstractLineBasedService {
    public CaseTogglingService(BufferedReader in, PrintWriter out) {
        super(in, out);
    }
    public String transmogrify(String line) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c <= 'Z' && c >= 'A') {
                chars[i] = ((char)(c + ((char)('a' - 'A'))));
            } else if (c <= 'z' && c >= 'a') {
                chars[i] = ((char)(c - ((char)('a' - 'A'))));
            }
        }
        return String.valueOf(chars);
    }
}
