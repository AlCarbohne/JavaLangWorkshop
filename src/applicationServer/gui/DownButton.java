package applicationServer.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class DownButton extends JButton {
    private final String IMAGE_PATH = "/applicationServer/gui/resources/arrow-down-64.png";

    public DownButton() {
        try {
            Image img = ImageIO.read(getClass().getResource(this.IMAGE_PATH));
            this.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Error fetching image for DownButton");
        }

        this.setMinimumSize(new Dimension(50, 50));
        this.setMaximumSize(new Dimension(50, 50));
    }
}
