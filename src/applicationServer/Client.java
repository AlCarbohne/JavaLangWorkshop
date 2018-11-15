package applicationServer;

public interface Client {
    void connect();
    void close();
    void run();
}
