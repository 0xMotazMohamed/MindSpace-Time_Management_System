package timemanager.gui;

import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;
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
    private Color pressedtaskBG = new Color(123, 123, 123);//174

    Font titleFont = new Font(null, Font.BOLD, 24);
    Font textFont = new Font(null, Font.PLAIN, 12);
    Font subTitleFont = new Font(null, Font.BOLD, 12);

    Project p;

    JLabel projectName;
    JTextArea projectDes;
    JLabel completedLabel;
    JLabel inProgressLabel;
    JLabel toDoLabel;

    Dimension labelSize;

    public ProjectPanel(Project p) {
        setLayout(null);
        this.p = p;

        projectName = new JLabel(p.getName(), SwingConstants.CENTER);
        projectName.setFont(titleFont);
        labelSize = projectName.getPreferredSize();
        projectName.setBounds(10,0,labelSize.width, labelSize.height);
        add(projectName);

        projectDes = new JTextArea(3,50);
        projectDes.setLineWrap(true);
        projectDes.setText(p.getDescription());
        projectDes.setBounds(10,30,260,50);
        projectDes.setBackground(projectBG);
        projectDes.setFont(textFont);
        projectDes.setEditable(false);
        projectDes.setFocusable(false);

        completedLabel = new JLabel("Completed", SwingConstants.CENTER);
        completedLabel.setFont(subTitleFont);
        completedLabel.setBounds(10,90,80,30);
        inProgressLabel = new JLabel("InProgress", SwingConstants.CENTER);
        inProgressLabel.setFont(subTitleFont);
        inProgressLabel.setBounds(100,90,80,30);
        toDoLabel = new JLabel("ToDo", SwingConstants.CENTER);
        toDoLabel.setFont(subTitleFont);
        toDoLabel.setBounds(190,90,80,30);

        paintTasks:{
            int taskN = 0;
            for (Task task : p.getToDoTasks()) {
                taskN++;
                paintTask(task, taskN, 195);
            }
            taskN = 0;
            for (Task task : p.getInProgressTasks()) {
                taskN++;
                paintTask(task, taskN, 105);
            }
            taskN = 0;
            for (Task task : p.getCompletedTasks()) {
                taskN++;
                paintTask(task, taskN, 15);
            }
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
        taskName.setBounds(x,(95  + (n * 30)),70,25);
        taskName.setBackground(taskBG);

        final Point[] initialClick = new Point[1];

        taskName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick[0] = e.getPoint();
                taskName.setBackground(pressedtaskBG);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int finalX = taskName.getX() + 40;
                int finalY = taskName.getY() + 15;
                taskName.setBackground(taskBG);
                StatusType statusType = task.getStatus().getStatus();

                if (finalX >= 10 && finalX <= 90 && finalY >= 90 && finalY <= 290) {
                    if (!statusType.equals(StatusType.COMPLETED)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getCompletedTasks());
                        remove(taskName);
                        updateTasksDisplay();
                    } else {
                        remove(taskName);
                        updateTasksDisplay();
                    }
                } else if (finalX >= 100 && finalX <= 180 && finalY >= 90 && finalY <= 290) {
                    if (!statusType.equals(StatusType.INPROGRESS)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getInProgressTasks());
                        remove(taskName);
                        updateTasksDisplay();
                    } else {
                        remove(taskName);
                        updateTasksDisplay();
                    }
                } else if (finalX >= 190 && finalX <= 270 && finalY >= 90 && finalY <= 290) {
                    if (!statusType.equals(StatusType.TODO)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getToDoTasks());
                        remove(taskName);
                        updateTasksDisplay();
                    } else {
                        remove(taskName);
                        updateTasksDisplay();
                    }
                } else {
                    remove(taskName);
                    updateTasksDisplay();
                }
            }
        });

        taskName.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - initialClick[0].x;
                int deltaY = e.getY() - initialClick[0].y;

                taskName.setLocation(taskName.getX() + deltaX, taskName.getY() + deltaY);
            }
        });
        add(taskName);
        revalidate();
        repaint();
    }

    public void updateTasksDisplay() {
        removeAll();
        repaint();
        add(projectName);
        add(projectDes);
        add(completedLabel);
        add(inProgressLabel);
        add(toDoLabel);
        int taskN = 0;
        for (Task task : p.getToDoTasks()) {
            taskN++;
            paintTask(task, taskN, 195);
        }
        taskN = 0;
        for (Task task : p.getInProgressTasks()) {
            taskN++;
            paintTask(task, taskN, 105);
        }
        taskN = 0;
        for (Task task : p.getCompletedTasks()) {
            taskN++;
            paintTask(task, taskN, 15);
        }
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
