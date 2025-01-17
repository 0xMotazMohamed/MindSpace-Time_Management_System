package org.example.gui.Shapes;

import javax.swing.*;
import java.awt.*;

public class Stroke extends JPanel {
    private int x, y, width, height;
    private Color color;
    private Color BGcolor = new Color(233,233,233);

    public Stroke(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        setOpaque(false);
    }

    public  void updateColor(Color newColor) {
        this.color = newColor;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);

        GradientPaint gradientPaint = new GradientPaint(0,0,color,20,0,BGcolor);
        g2d.setPaint(gradientPaint);
//        Rectangle r = new Rectangle(0,0,20,20);
//        g2d.draw(r);
//        g2d.fill(r);
    }
}
