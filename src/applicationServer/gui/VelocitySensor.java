package applicationServer.gui;

import applicationServer.impl.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VelocitySensor extends JFrame {

    private static final String WINDOW_TITLE = "TachoUI";
    private static final Font VELOCITY_DISPLAY_FONT = new Font("Sans", Font.BOLD, 72);
    private static final double MIN_VELOCITY = 0.0;
    private static final double MAX_VELOCITY = 0.0;
    private static final double VELOCITY_INCREMENT = 7.3;
    private static final double VELOCITY_DECREMENT = 4.3;

    private Client client;
    private double velocity;
    private JButton upButton;
    private JButton downButton;
    private JLabel velocityDisplay;
    private JLabel connectionStateDisplay;

    public static void main(String[] args) {
        new VelocitySensor().setVisible(true);
    }

    public VelocitySensor() {
        super(WINDOW_TITLE);

        this.velocity = 0.0;

        this.upButton = new UpButton();
        this.upButton.addActionListener(this::increaseVelocity);
        this.downButton = new DownButton();
        this.downButton.addActionListener(this::decreaseVelocity);
        this.velocityDisplay = new VelocityDisplay(this.velocity);
        this.connectionStateDisplay = new JLabel("Unknown connection state");

        this.add(this.upButton, BorderLayout.EAST);
        this.add(this.downButton, BorderLayout.WEST);
        this.add(this.velocityDisplay, BorderLayout.CENTER);
        this.add(this.connectionStateDisplay);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initConnection();
        new Thread(this::updaterThreadFunction).start();
    }

    private void decreaseVelocity(ActionEvent event) {
        changeVelocity(VELOCITY_DECREMENT);
    }

    private void increaseVelocity(ActionEvent event) {
        changeVelocity(VELOCITY_INCREMENT);
    }

    private synchronized void changeVelocity(double difference) {
        double newVelocity = this.velocity + difference;
        if (newVelocity < MIN_VELOCITY) {
            newVelocity = MIN_VELOCITY;
        } else if (newVelocity > MAX_VELOCITY) {
            newVelocity = MAX_VELOCITY;
        }
        this.velocity = newVelocity;
    }

    public void initConnection() {
        if (this.client == null) {
            return;
        }
        this.client = new Client();
        String serverResponse = this.client.sendRequests("tacho");
        if (serverResponse.equals("OK")) {
            this.connectionStateDisplay.setText("connected");
            this.connectionStateDisplay.setForeground(Color.GREEN);
        }
    }

    private void updaterThreadFunction() {

    }
}