package applicationServer.gui;

import applicationServer.impl.Client;

import javax.swing.*;
import java.awt.*;

public class VelocitySensor extends JFrame {

    private static final String WINDOW_TITLE = "TachoUI";

    private final Client client;
    private double velocity;
    private JButton upButton;
    private JButton downButton;
    private JLabel velocityDisplay;

    public VelocitySensor() {
        super(WINDOW_TITLE);

        this.velocity = 0.0;
        this.upButton = new UpButton();
        this.downButton = new DownButton();
        this.velocityDisplay = new VelocityDisplay(this.velocity);

        this.add(this.upButton, BorderLayout.NORTH);
        this.add(this.downButton, BorderLayout.SOUTH);
        this.add(this.velocityDisplay, BorderLayout.CENTER);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.client = new Client();
    }
}
