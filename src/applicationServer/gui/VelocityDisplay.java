package applicationServer.gui;

import javax.swing.*;
import java.awt.*;

public class VelocityDisplay extends JLabel {
    private static final Font VELOCITY_DISPLAY_FONT = new Font("Sans", Font.BOLD, 72);

    public VelocityDisplay(int velocity) {

        super(Integer.toString(velocity));
        this.setFont(VELOCITY_DISPLAY_FONT);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void setText(String text) {
        if (text == null) {
            super.setText("ERROR");
            return;
        }

        String beginning = "";
        String ending = "";

        if (text.length() < 3) {
            beginning = "0";

            if (text.length() == 1) { //add a leading padding 0
                ending = "0";
            }
            ending += text;

        } else {

            beginning = text.substring(0, text.length() - 2);
            ending = text.substring(text.length() - 2);
        }

        super.setText(beginning + "." + ending);
    }
}
