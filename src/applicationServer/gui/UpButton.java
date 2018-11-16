package applicationServer.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class UpButton extends JButton {
    private final String IMAGE_PATH = "/applicationServer/gui/resources/uparrow.png";

    public UpButton() {
        try {
            Image img = ImageIO.read(getClass().getResource(this.IMAGE_PATH));
            this.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Error fetching image for UpButton");
        }

        this.setMinimumSize(new Dimension(200, 200));
    }
}
