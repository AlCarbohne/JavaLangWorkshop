package applicationServer;

import io.vavr.collection.List;

public interface serviceMap {
    /**
     * the currently available services
     */
    public List<ServiceFactory> getServices();
}
