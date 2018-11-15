package applicationServer.impl;

import applicationServer.Service;
import applicationServer.ServiceFactory;

public class ServiceFactoryImpl implements ServiceFactory {
    private String name;

    public ServiceFactoryImpl(String name) {
        this.name = name;
    }

    @Override
    public Service createService() {
        return new ServiceImpl(this.name);
    }
}
