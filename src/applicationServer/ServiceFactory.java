package applicationServer;

import java.net.Socket;

public interface ServiceFactory {
    Service create(Socket socket);
}
