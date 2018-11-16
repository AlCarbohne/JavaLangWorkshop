package applicationServer.tests;

import applicationServer.gui.VelocitySensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VelocitySensorTest {

    @Test
    void changeVelocity() {
        VelocitySensor sensor = new VelocitySensor();
        sensor.run();

        Assertions.assertEquals("0", Integer.toString(sensor.getVelocity()));

        sensor.changeVelocity(0);
        Assertions.assertEquals("0", Integer.toString(sensor.getVelocity()));

        sensor.changeVelocity(50);
        Assertions.assertEquals("50", Integer.toString(sensor.getVelocity()));

        sensor.changeVelocity(-50);
        Assertions.assertEquals("0", Integer.toString(sensor.getVelocity()));

        sensor.changeVelocity(-50);
        Assertions.assertEquals("0", Integer.toString(sensor.getVelocity()));

        sensor.changeVelocity(Integer.MAX_VALUE);
        Assertions.assertEquals("25000", Integer.toString(sensor.getVelocity()));

        sensor.changeVelocity(Integer.MIN_VALUE);
        Assertions.assertEquals("0", Integer.toString(sensor.getVelocity()));

        sensor.changeVelocity(Integer.MIN_VALUE);
        Assertions.assertEquals("0", Integer.toString(sensor.getVelocity()));

    }
}