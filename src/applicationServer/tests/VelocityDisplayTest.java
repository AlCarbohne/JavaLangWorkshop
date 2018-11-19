package applicationServer.tests;

import applicationServer.gui.VelocityDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VelocityDisplayTest {

    @Test
    void setText() {
        VelocityDisplay display = new VelocityDisplay(0);
        Assertions.assertEquals("   0.00   ", display.getText()); //initial padding

        display.setText("000");
        Assertions.assertEquals("0.00", display.getText());

        display.setText("99999");
        Assertions.assertEquals("999.99", display.getText());

        display.setText(null);
        Assertions.assertEquals("ERROR", display.getText());
    }
}