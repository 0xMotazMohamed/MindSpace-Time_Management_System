package timemanager.gui.Shapes;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends JPanel {
    private int x, y, width, height;
    private Color color;

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        setOpaque(false);
    }

    public void updateColor(Color newColor) {
        this.color = newColor;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
