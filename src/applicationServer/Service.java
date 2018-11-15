package applicationServer;

public interface Service {

    default boolean start() {
        return false;
    }

}
