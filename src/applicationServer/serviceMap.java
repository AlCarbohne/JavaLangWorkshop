package applicationServer;

import java.util.List;

public interface serviceMap {
    /**
     * the currently available services
     */
    public List<ServiceFactory> getServices();
}
