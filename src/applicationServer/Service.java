package applicationServer;

import java.io.InputStream;
import java.io.OutputStream;

public interface Service {
    String getName();

    boolean start();

    void shutdown();

}
