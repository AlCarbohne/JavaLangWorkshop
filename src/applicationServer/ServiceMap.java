package applicationServer;

import java.util.List;

public interface ServiceMap {
    /**
     * the currently available services
     */
    public List<ServiceFactory> getServices();
}
