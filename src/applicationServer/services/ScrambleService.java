package applicationServer.services;

import applicationServer.Service;

import java.io.*;

public class ScrambleService implements Service {
    BufferedReader inputStream;
    PrintWriter outputStream;

    public ScrambleService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        try {
            String line;
            while (!(line = inputStream.readLine()).isEmpty()) {
                // CAUTION: this only works on regular spaces
                String newLine = "";
                for (String word : line.split(" ")) {
                    newLine += mangle(word) + " ";
                }
                // crop last space
                outputStream.println(newLine.substring(0, newLine.length() - 1));
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static String mangle(String word) {
        char[] chars = word.toCharArray();
        for (int i = 1; i < (chars.length / 2); i++) {
            // swap inner characters pairwise
            char c = chars[chars.length - (i+1)];
            chars[chars.length - (i+1)] = chars[i];
            chars[i] = c;
        }
        return String.valueOf(chars);
    }
}
