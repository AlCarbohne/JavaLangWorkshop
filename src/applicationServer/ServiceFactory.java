package applicationServer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public interface ServiceFactory {
    default Service create(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                        outputStream,
                        StandardCharsets.UTF_8)
        );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        inputStream,
                        StandardCharsets.UTF_8
                )
        );
        return create(reader, writer);
    }
    Service create(BufferedReader reader, PrintWriter writer);
}
