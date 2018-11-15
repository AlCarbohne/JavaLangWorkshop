package applicationServer.tests;

import applicationServer.impl.Client;
import applicationServer.impl.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void testPing() {
        Thread thread = new Thread(() -> {
            Server server = new Server();
            server.run();
        });
        Client client = new Client();

        Assertions.assertEquals("OK", client.sendRequests("ping"));

        thread.interrupt();
        client.close();
    }

    @Test
    void testUppercase() {
        Thread thread = new Thread(() -> {
            Server server = new Server();
            server.run();
        });
        Client client = new Client();

        Assertions.assertEquals("OK", client.sendRequests("uppercase"));
        Assertions.assertEquals("ABCDEFGH1", client.sendRequests("aBcdeFGh1"));

        thread.interrupt();
        client.close();
    }

}