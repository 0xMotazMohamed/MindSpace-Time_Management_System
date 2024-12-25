package timemanager.gui;

import timemanager.data.dto.Project;

import javax.swing.*;
import java.awt.*;

public class ProjectPanel extends JPanel {
    public ProjectPanel(MainFrame mainFrame, Project project) {
        setLayout(null);


    }

    public void painComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }
}
