package applicationServer.services;

import applicationServer.Service;
import applicationServer.ServiceFactory;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketService implements Service, ServiceFactory {

    public SocketService(BufferedReader in, PrintWriter out) {
        // this factory being called is not the point
        throw new UnsupportedOperationException("SocketService can only be instantiated by passing a raw socket");
    }
    // not supposed to be called, necessary to implement ServiceFactory
    public Service create(BufferedReader in, PrintWriter out) {
        return new SocketService(in,out);
    }

    public SocketService(Socket socket) {
        System.out.println("Success!!!!!!!");
    }

    public boolean start() {
        return true;
    }
}
