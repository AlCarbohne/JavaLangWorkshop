package applicationServer.gui;

import applicationServer.impl.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.SwingConstants.CENTER;

public class VelocitySensor extends JFrame {

    private static final String WINDOW_TITLE = "TachoUI";
    private static final int MIN_VELOCITY = 0;
    private static final int MAX_VELOCITY = 250_00;
    private static final int VELOCITY_INCREMENT = 730;
    private static final int VELOCITY_DECREMENT = 430;
    private static final int UPDATE_INTERVAL = 500;

    private Client client;
    private int velocity;
    private JButton upButton;
    private JButton downButton;
    private JLabel velocityDisplay;
    private JLabel connectionStateDisplay;

    private Thread updaterThread;

    public static void main(String[] args) {
        new VelocitySensor().run();
    }

    public VelocitySensor() {
        super(WINDOW_TITLE);

        this.velocity = 0;

        this.upButton = new UpButton();
        this.upButton.addActionListener(this::increaseVelocity);
        this.downButton = new DownButton();
        this.downButton.addActionListener(this::decreaseVelocity);
        this.velocityDisplay = new VelocityDisplay();
        this.connectionStateDisplay = new JLabel("Unknown connection state");
        this.connectionStateDisplay.setHorizontalAlignment(CENTER);

        this.add(this.upButton, BorderLayout.EAST);
        this.add(this.downButton, BorderLayout.WEST);
        this.add(this.velocityDisplay, BorderLayout.CENTER);
        this.add(this.connectionStateDisplay, BorderLayout.SOUTH);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.updaterThread = new Thread(this::updaterThreadFunction);
    }

    public void run() {
        this.setVisible(true);
        this.initConnection();
        this.updaterThread.start();
    }

    private void decreaseVelocity(ActionEvent event) {
        changeVelocity(-VELOCITY_DECREMENT);
    }

    private void increaseVelocity(ActionEvent event) {
        changeVelocity(VELOCITY_INCREMENT);
    }

    public synchronized void changeVelocity(int difference) {
        int newVelocity = this.velocity + difference;
        if (newVelocity < MIN_VELOCITY) {
            newVelocity = MIN_VELOCITY;
        } else if (newVelocity > MAX_VELOCITY) {
            newVelocity = MAX_VELOCITY;
        }
        this.velocity = newVelocity;
        this.velocityDisplay.setText(Integer.toString(this.velocity));
    }

    public boolean initConnection() {
        if (this.client != null) {
            return false;
        }
        this.client = new Client();
        String serverResponse = this.client.sendRequests("tacho");
        if (serverResponse.equals("OK")) {
            this.connectionStateDisplay.setText("connected");
            this.connectionStateDisplay.setForeground(Color.GREEN);
            return true;
        }
        return false;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public void updaterThreadFunction() {
        while (true) {
            try {
                if (this.client == null) {
                    initConnection();
                } else {
                    synchronized (this) {
                        this.client.sendRequests(Float.toString((float) this.velocity / 100));
                        this.velocityDisplay.setText(Integer.toString(this.velocity));
                    }
                }
                Thread.sleep(UPDATE_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}