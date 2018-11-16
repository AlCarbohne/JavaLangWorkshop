package applicationServer.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class DownButton extends JButton {
    private final String IMAGE_PATH = "resources/water.bmp";

    public DownButton() {
        try {
            Image img = ImageIO.read(getClass().getResource(this.IMAGE_PATH));
            this.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Error fetching image");
        }
    }

    @Override
    public void doClick() {
        super.doClick();
    }
}
