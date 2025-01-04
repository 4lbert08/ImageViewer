package software.ulpgc.app;

import software.ulpgc.architecture.view.ImageDisplay;
import software.ulpgc.architecture.view.ViewPort;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final SwingImageDeserializer deserializer;
    private final List<ImageDisplay.PaintOrder> orders;
    private ImageDisplay.Shift shift = Shift.Null;
    private ImageDisplay.Released released = Released.Null;

    public SwingImageDisplay(SwingImageDeserializer deserializer) {
        this.deserializer = deserializer;
        this.orders = new ArrayList<>();
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }


    private int initX;

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                SwingImageDisplay.this.initX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initX);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int offset = e.getX() - SwingImageDisplay.this.initX;
                shift.offset(offset);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());

        for (PaintOrder order : orders) {
            paintOrder(g, order);
        }
    }

    private void paintOrder(Graphics g, ImageDisplay.PaintOrder order) {
        BufferedImage image = (BufferedImage) deserializer.deserialize(order.content());
        ViewPort viewPort = ViewPort.ofSize(width(), this.getHeight()).fit(image.getWidth(), image.getHeight());
        g.drawImage(image, viewPort.x() + order.offset(), viewPort.y(), viewPort.width(), viewPort.height(), null);
    }

    @Override
    public int width() {
        return this.getWidth();
    }

    @Override
    public void paint(PaintOrder... orders) {
        removeAll();
        this.orders.clear();
        Collections.addAll(this.orders, orders);
        repaint();
    }

    @Override
    public void on(Shift shift) {
        this.shift=shift;
    }

    @Override
    public void on(Released released) {
        this.released=released;
    }
}
