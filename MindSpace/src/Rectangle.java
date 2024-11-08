import javax.swing.*;
import java.awt.*;

public class Rectangle extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(50,50,300,300);
        g.setColor(Color.RED);
        g.fillRect(50,50,100,60);
    }
}
