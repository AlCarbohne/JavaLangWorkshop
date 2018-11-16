package applicationServer.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class DownButton extends JButton {
    private final String IMAGE_PATH = "src/applicationServer/gui/resources/downarrow.png";

    public DownButton() {
        try {
            Image img = ImageIO.read(getClass().getResource(this.IMAGE_PATH));
            this.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Error fetching image for DownButton");
        }

        this.setMinimumSize(new Dimension(200, 200));
    }
}
