package com.gavincode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

record Ball(Color color, Dimension dimension) {}

public class SpringPanel extends JPanel implements ActionListener {
    private final Ball ball;
    Timer timer;
    private static final double MASS = 1;
    private static final double STIFFNESS = 0.5;
    private static final double DAMPING = 0.5;

    private double position = 0;
    private double velocity = 0;

    public SpringPanel() {
        ball = new Ball(new Color(24, 117, 209), new Dimension(50, 50));
        timer = new Timer((1000/60), this);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var gball = (Graphics2D)g.create();
        int x = (int) position;
        int y = getHeight() / 2;
        gball.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gball.setColor(ball.color());
        gball.fillOval(x, y, ball.dimension().width, ball.dimension().height);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        double equilibriumPosition = 500;
        double springForce = -STIFFNESS * (position - equilibriumPosition);
        double dampingForce = -DAMPING * velocity;
        var oldX = (int)position;

        double acc = (springForce + dampingForce) / MASS;
        //var timeInterval = 100 / 60;
        var timeInterval = 0.3;
        velocity += acc * timeInterval;
        position += velocity * timeInterval;
        if ((int)position == equilibriumPosition && Math.abs(velocity) < 0.004) {
            timer.stop();
        }

        var newX = (int)position;
        var distance = 0;
        if (newX > oldX) {
            distance = newX - oldX;
        } else {
            distance = oldX - newX;
        }
        // x and y are on the left top corner.
        repaint(Math.min(newX, oldX), getHeight()/2, ball.dimension().width + distance, ball.dimension().height);
    }

    public void start() {
        if (timer.isRunning()) {
            timer.stop();
        }
        position = 0;
        velocity = 0;
        repaint();
        timer.start();
    }

    public void stop() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }
}
