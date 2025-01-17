package org.example.gui.Shapes;

import javax.swing.*;
import java.awt.*;

public class RoundRectangle extends JPanel{
    private int x, y, width, height, arcWidth, arcHeight;
    private Color color;

    public RoundRectangle(int x, int y, int width, int height, int arc, Color color) {
        setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.arcWidth = arc;
        this.arcHeight = arc;
        this.color = color;
        setOpaque(false);
    }

    public void updateColor(Color newcolor) {
        this.color = color;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }
}
