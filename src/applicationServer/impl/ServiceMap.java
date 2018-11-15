package applicationServer.impl;

import applicationServer.ServiceFactory;
import applicationServer.services.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceMap {
    public static Map<String, ServiceFactory> get() {
        Map<String, ServiceFactory> map = new HashMap<>();
        map.put("ping", PingService::new);
        map.put("timeofday", TimeOfDayService::new);
        map.put("uppercase", UppercaseService::new);
        map.put("tacho", Tachoservice::new);
        map.put("scramble", ScrambleService::new);
        return map;
    }
}
