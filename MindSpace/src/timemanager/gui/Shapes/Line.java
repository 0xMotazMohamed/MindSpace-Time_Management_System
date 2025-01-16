package timemanager.gui.Shapes;

import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    private int x1,x2, y1, y2;
    private Color color;

    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
