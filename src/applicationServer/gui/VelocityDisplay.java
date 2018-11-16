package applicationServer.gui;

import javax.swing.*;
import java.awt.*;

public class VelocityDisplay extends JLabel {
    private static final Font VELOCITY_DISPLAY_FONT = new Font("Sans", Font.BOLD, 72);

    public VelocityDisplay(double velocity) {

        super(Double.toString(velocity));
        this.setFont(VELOCITY_DISPLAY_FONT);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
