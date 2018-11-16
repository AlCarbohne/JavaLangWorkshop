package applicationServer.gui;

import applicationServer.impl.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class VelocitySensor extends JFrame {

    private static final String WINDOW_TITLE = "TachoUI";
    private static final Font VELOCITY_DISPLAY_FONT = new Font("Sans", Font.BOLD, 72);

    private final Client client;
    private double velocity;
    private JButton upButton;
    private JButton downButton;
    private JLabel velocityDisplay;

    public VelocitySensor() {
        super(WINDOW_TITLE);

        velocity = 0.0;
        upButton = new UpButton();
        downButton = new DownButton();
        velocityDisplay = new JLabel(Double.toString(velocity));
        velocityDisplay.setFont(VELOCITY_DISPLAY_FONT);
        velocityDisplay.setHorizontalTextPosition(SwingConstants.CENTER);
        velocityDisplay.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(upButton, BorderLayout.NORTH);
        this.add(downButton, BorderLayout.SOUTH);
        this.add(velocityDisplay, BorderLayout.CENTER);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        client = new Client();
    }
}
