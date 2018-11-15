package applicationServer.tests;

import applicationServer.impl.Client;
import applicationServer.impl.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ClientTest {

    @BeforeAll
    static void setUp() {
        new Thread(() -> new Server().run()).start();
    }

    @Test
    void testPing() {
        Client client = new Client();

        Assertions.assertEquals("OK", client.sendRequests("ping"));

        client.close();
    }

    @Test
    void testUppercase() {
        Client client = new Client();

        Assertions.assertEquals("OK", client.sendRequests("uppercase"));
        Assertions.assertEquals("ABCDEFGH1", client.sendRequests("aBcdeFGh1"));

        client.close();
    }

    @Test
    void testScramble() {
        Client client = new Client();

        String command = "A AB abc abcd aBcdE";
        String expected = "A AB abc acbd adcBE";
        Assertions.assertEquals(expected, client.sendRequests(command));

        client.close();
    }

    @Test
    void testTacho() {
        Client client = new Client();

        Assertions.assertEquals("OK", client.sendRequests("uppercase"));
        Assertions.assertEquals("ABCDEFGH1", client.sendRequests("aBcdeFGh1"));

        client.close();
    }

    @Test
    void testWrongService() {
        Client client = new Client();

        String command = "upperPing";
        String expected = "Service \"" + command + "\" does not exist";
        Assertions.assertEquals(expected, client.sendRequests(command));

        client.close();
    }

}