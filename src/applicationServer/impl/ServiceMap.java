package applicationServer.impl;

import applicationServer.ServiceFactory;
import applicationServer.services.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceMap {

    private static Map<String, ServiceFactory> serviceMap = new HashMap<>();
    static {
        serviceMap.put("ls", ServiceListerService::new);
        serviceMap.put("ping", PingService::new);
        serviceMap.put("timeofday", TimeOfDayService::new);
        serviceMap.put("uppercase", UppercaseService::new);
        serviceMap.put("tacho", TachoService::new);
        serviceMap.put("scramble", ScrambleService::new);
        serviceMap.put("cts", CaseTogglingService::new);
        serviceMap.put("lowercase", new Transmogrifier((s) -> s.toLowerCase())::create);
        serviceMap.put("raw", SocketService::new);
    }

    public static ServiceFactory get(String serviceName) {
        return serviceMap.get(serviceName);
    }

    static void registerService(String serviceName, ServiceFactory serviceFactory) {
        serviceMap.put(serviceName, serviceFactory);
    }

    
    /**
     * for the end user, otherwise the service names just have to be known
     *
     * @return list of every service name
     */
    public static List<String> getServiceNames() {
        return new ArrayList<>(serviceMap.keySet());
    }
}
