package applicationServer.impl;

import applicationServer.ServiceFactory;
import applicationServer.services.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceMap {
    public static Map<String, ServiceFactory> get() {
        Map<String, ServiceFactory> map = new HashMap<>();
        map.put("ping", PingService::new);
        map.put("timeofday", TimeOfDayService::new);
        map.put("uppercase", UppercaseService::new);
        map.put("tacho", TachoService::new);
        map.put("scramble", ScrambleService::new);
        map.put("cts", CaseTogglingService::new);
        map.put("lowercase", new Transmogrifier((s) -> s.toLowerCase())::create);
        return map;
    }

    
    /**
     * for the end user, otherwise the service names just have to be known
     *
     * @return list of every service name
     */
    public static List<String> serviceNames() {
        return new ArrayList<>(ServiceMap.get().keySet());
    }
}
