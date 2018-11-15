package applicationServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface ServiceFactory {
    default Service create(Socket socket) throws IOException {
        return create(socket.getInputStream(), socket.getOutputStream());
    }

    Service create(InputStream inputStream, OutputStream outputStream);
}
