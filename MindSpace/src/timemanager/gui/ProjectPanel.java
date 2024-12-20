package timemanager.gui;

import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;
import timemanager.data.dto.flyweight.Status;
import timemanager.data.dto.flyweight.StatusType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectPanel extends JPanel {
    private Color projectBG = new Color(255, 255, 255);
    private Color statusBG = new Color(202, 202, 202);
    private Color CompletedBG = new Color(92, 179, 56);
    private Color InProgrssBG = new Color(236,232,82);
    private Color ToDoBG = new Color(251,65,65);
    private Color taskBG = new Color(174, 174, 174);//174
    Color blackL = new Color(70,70,70);

    Font titleFont = new Font(null, Font.BOLD, 24);
    Font textFont = new Font(null, Font.PLAIN, 12);
    Font subTitleFont = new Font(null, Font.BOLD, 12);

    Project p;

    public ProjectPanel(Project p) {
        setLayout(null);

        Dimension labelSize;

        JLabel projectName = new JLabel(p.getName(), SwingConstants.CENTER);
        projectName.setFont(titleFont);
        labelSize = projectName.getPreferredSize();
        projectName.setBounds(10,0,labelSize.width, labelSize.height);
        add(projectName);

        JTextArea projectDes = new JTextArea(3,50);
        projectDes.setLineWrap(true);
        projectDes.setText(p.getDescription());
        projectDes.setBounds(10,30,260,50);
        projectDes.setBackground(projectBG);
        projectDes.setFont(textFont);
        projectDes.setEditable(false);
        projectDes.setFocusable(false);

        JLabel completedLabel = new JLabel("Completed", SwingConstants.CENTER);
        completedLabel.setFont(subTitleFont);
        completedLabel.setBounds(10,90,80,30);
        JLabel inProgressLabel = new JLabel("InProgress", SwingConstants.CENTER);
        inProgressLabel.setFont(subTitleFont);
        inProgressLabel.setBounds(100,90,80,30);
        JLabel toDoLabel = new JLabel("ToDo", SwingConstants.CENTER);
        toDoLabel.setBounds(190,90,80,30);
        toDoLabel.setFont(subTitleFont);

        int taskN = 0;
        for (Task task : p.getToDoTasks()) {
            taskN ++;
            paintTask(task, taskN, 195);
        }
        taskN = 0;
        for (Task task : p.getInProgressTasks()) {
            taskN ++;
            paintTask(task, taskN, 105);
        }
        taskN = 0;
        for (Task task : p.getCompletedTasks()) {
            taskN ++;
            paintTask(task, taskN, 15);
        }

        add(projectDes);
        add(completedLabel);
        add(inProgressLabel);
        add(toDoLabel);
    }

    public void paintTask(Task task, int n, int x) {
        JLabel taskName = new JLabel(task.getTitle(), SwingConstants.CENTER);
        taskName.setOpaque(true);
        taskName.setFont(textFont);
        taskName.setBounds(195,(95  + (n * 30)),70,25);
        taskName.setBackground(taskBG);

        taskName.addMouseListener(new MouseAdapter() {
            int xOffset, yOffset;
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                StatusType statusType = task.getStatus().getStatus();
                if (e.getX() >= 10 && e.getX() <= 90 && e.getY() >= 90 && e.getY() <= 290) {
                    if (!statusType.equals(StatusType.COMPLETED)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getCompletedTasks());
                        task.setStatus(new Status("Completed"));
                        revalidate();
                        repaint();
                    }
                } else if (e.getX() >= 100 && e.getX() <= 180 && e.getY() >= 90 && e.getY() <= 290) {
                    if (!statusType.equals(StatusType.INPROGRESS)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getInProgressTasks());
                        task.setStatus(new Status("InProgress"));
                        revalidate();
                        repaint();
                    }
                } else if (e.getX() >= 190 && e.getX() <= 270 && e.getY() >= 90 && e.getY() <= 290) {
                    if (!statusType.equals(StatusType.TODO)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getToDoTasks());
                        task.setStatus(new Status("ToDo"));
                        revalidate();
                        repaint();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - xOffset - getLocationOnScreen().x;
                int newY = e.getYOnScreen() - yOffset - getLocationOnScreen().y;
                taskName.setLocation(newX, newY);
            }
        });

        add(taskName);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(projectBG);
        g2d.fillRoundRect(0,0,280,300,20,20);

        g2d.setColor(statusBG);
        g2d.fillRoundRect(10,90,80,200,10,10);
        g2d.setColor(CompletedBG);
        g2d.fillRoundRect(10,90,80,30,10,10);

        g2d.setColor(statusBG);
        g2d.fillRoundRect(100,90,80,200,10,10);
        g2d.setColor(InProgrssBG);
        g2d.fillRoundRect(100,90,80,30,10,10);

        g2d.setColor(statusBG);
        g2d.fillRoundRect(190,90,80,200,10,10);
        g2d.setColor(ToDoBG);
        g2d.fillRoundRect(190,90,80,30,10,10);
    }
}
