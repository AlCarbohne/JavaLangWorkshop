package applicationServer.impl;

import applicationServer.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class ServiceMap {
    public static Map<String,ServiceFactory> get() {
        Map<String,ServiceFactory> map = new HashMap<>();
        map.put("ping", PingService::new);
        return map;
    }
}
