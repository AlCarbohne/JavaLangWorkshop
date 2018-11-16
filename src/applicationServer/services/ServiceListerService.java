package applicationServer.services;

import applicationServer.Service;
import applicationServer.impl.ServiceMap;

import java.io.*;
import java.time.LocalDateTime;

public class ServiceListerService implements Service {
    BufferedReader inputStream;
    PrintWriter outputStream;

    public ServiceListerService(BufferedReader inputStream, PrintWriter outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public boolean start() {
        for (String serviceName : ServiceMap.getServiceNames()) {
            outputStream.println(serviceName);
        }
        outputStream.flush();
        return true;
    }
}
