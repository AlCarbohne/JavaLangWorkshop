package applicationServer.tests;

import applicationServer.impl.Client;
import applicationServer.impl.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void testPing() {
        Thread thread = new Thread(Server::new);
        Client client = new Client();

        Assertions.assertEquals("OK", client.sendRequests("ping"));

        thread.interrupt();
        client.close();
    }

    @Test
    void testUppercase() {
        Thread thread = new Thread(Server::new);
        Client client = new Client();

        Assertions.assertEquals("OK", client.sendRequests("uppercase"));
        Assertions.assertEquals("ABCDEFGH", client.sendRequests("aBcdeFGh"));

        thread.interrupt();
        client.close();
    }

}