package applicationServer.impl;

import applicationServer.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class ServiceMap implements applicationServer.ServiceMap {
    private List<ServiceFactory> serviceList;
    private static final List<String> serviceNames = new ArrayList<>();

    public ServiceMap(List<ServiceFactory> serviceList) {
        this.serviceList = new ArrayList<>();
        populateServiceNames();
        serviceNames.forEach(serviceName -> this.serviceList.add(new ServiceFactoryImpl(serviceName)));
    }

    private void populateServiceNames() {
        serviceNames.add("ping");
        serviceNames.add("uppercase");
        serviceNames.add("timeofday");
        serviceNames.add("scramble");
        serviceNames.add("tacho");
    }

    @Override
    public List<ServiceFactory> getServices() {
        return this.serviceList;
    }
}
