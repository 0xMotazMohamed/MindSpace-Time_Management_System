package timemanager.gui;

import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;
import timemanager.data.dto.flyweight.StatusType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MiniProjectPanel extends JPanel {
    private Color wProjectBG = new Color(255, 255, 255);
    private Color wStatusBG = new Color(202, 202, 202);
    private Color wTaskBG = new Color(174, 174, 174);
    private Color wPressedtaskBG = new Color(123, 123, 123);

    private Color bProjectBG = new Color(0,0,0);
    private Color bStatusBG = new Color(53, 53, 53);
    private Color bTaskBG = new Color(81, 81, 81);
    private Color bPressedtaskBG = new Color(132, 132, 132);

    private Color CompletedBG = new Color(92, 179, 56);
    private Color InProgrssBG = new Color(236,232,82);
    private Color ToDoBG = new Color(251,65,65);

    Font titleFont = new Font(null, Font.BOLD, 24);
    Font textFont = new Font(null, Font.PLAIN, 12);
    Font subTitleFont = new Font(null, Font.BOLD, 12);

    private Color wText = new Color(0,0,0);
    private Color bText = new Color(255,255,255);

    Project p;

    ArrayList<JLabel> taskNames = new ArrayList<>();

    JLabel projectName;
    JTextArea projectDes;
    JLabel completedLabel;
    JLabel inProgressLabel;
    JLabel toDoLabel;

    Dimension labelSize;

    public MiniProjectPanel(Project p) {
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
        projectDes.setFont(textFont);
        projectDes.setEditable(false);
        projectDes.setFocusable(false);
        projectDes.setOpaque(false);

        completedLabel = new JLabel("Completed", SwingConstants.CENTER);
        completedLabel.setFont(subTitleFont);
        completedLabel.setBounds(15,90,80,30);
        inProgressLabel = new JLabel("InProgress", SwingConstants.CENTER);
        inProgressLabel.setFont(subTitleFont);
        inProgressLabel.setBounds(110,90,80,30);
        toDoLabel = new JLabel("ToDo", SwingConstants.CENTER);
        toDoLabel.setFont(subTitleFont);
        toDoLabel.setBounds(205,90,80,30);

        paintTasks:{
            int taskN = 0;
            for (Task task : p.getToDoTasks()) {
                taskN++;
                paintTask(task, taskN, 210);
                if (taskN == 5)
                    break;
            }
            taskN = 0;
            for (Task task : p.getInProgressTasks()) {
                taskN++;
                paintTask(task, taskN, 115);
                if (taskN == 5)
                    break;
            }
            taskN = 0;
            for (Task task : p.getCompletedTasks()) {
                taskN++;
                paintTask(task, taskN, 20);
                if (taskN == 5)
                    break;
            }
        }

        add(projectDes);
        add(completedLabel);
        add(inProgressLabel);
        add(toDoLabel);
    }

    public void paintTask(Task task, int n, int x) {
        JLabel taskName = new JLabel(task.getTitle(), SwingConstants.CENTER);
        taskNames.add(taskName);
        taskName.setOpaque(true);
        taskName.setFont(textFont);
        taskName.setBounds(x,(95  + (n * 30)),70,25);
        if (ThemeManager.isDarkMode()) {
            taskName.setBackground(bTaskBG);
        } else {
            taskName.setBackground(wTaskBG);
        }

        final Point[] initialClick = new Point[1];

        taskName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick[0] = e.getPoint();
                if (ThemeManager.isDarkMode()) {
                    taskName.setBackground(bPressedtaskBG);
                } else {
                    taskName.setBackground(wPressedtaskBG);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int finalX = taskName.getX() + 40;
                int finalY = taskName.getY() + 15;
                if (ThemeManager.isDarkMode()) {
                    taskName.setBackground(bTaskBG);
                } else {
                    taskName.setBackground(wTaskBG);
                }
                StatusType statusType = task.getStatus().getStatus();

                if (finalX >= 15 && finalX <= 95 && finalY >= 90 && finalY <= 290) {
                    if (!statusType.equals(StatusType.COMPLETED)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getCompletedTasks());
                        remove(taskName);
                        updateTasksDisplay();
                    } else {
                        remove(taskName);
                        updateTasksDisplay();
                    }
                } else if (finalX >= 110 && finalX <= 190 && finalY >= 90 && finalY <= 290) {
                    if (!statusType.equals(StatusType.INPROGRESS)) {
                        p.transferTask(task, p.getTasksByStatus(statusType), p.getInProgressTasks());
                        remove(taskName);
                        updateTasksDisplay();
                    } else {
                        remove(taskName);
                        updateTasksDisplay();
                    }
                } else if (finalX >= 205 && finalX <= 285 && finalY >= 90 && finalY <= 290) {
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
            paintTask(task, taskN, 210);
            if (taskN == 5)
                break;
        }
        taskN = 0;
        for (Task task : p.getInProgressTasks()) {
            taskN++;
            paintTask(task, taskN, 115);
            if (taskN == 5)
                break;
        }
        taskN = 0;
        for (Task task : p.getCompletedTasks()) {
            taskN++;
            paintTask(task, taskN, 20);
            if (taskN == 5)
                break;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (ThemeManager.isDarkMode()) {
            g2d.setColor(bProjectBG);
            for (JLabel labels : taskNames)
                labels.setBackground(bTaskBG);
        } else {
            g2d.setColor(wProjectBG);
            for (JLabel labels : taskNames)
                labels.setBackground(wTaskBG);
        }
        g2d.fillRoundRect(0,0,300,300,20,20);

        if (ThemeManager.isDarkMode()) {
            g2d.setColor(bStatusBG);
        } else {
            g2d.setColor(wStatusBG);
        }
        g2d.fillRoundRect(15,90,80,200,10,10);
        g2d.fillRoundRect(110,90,80,200,10,10);
        g2d.fillRoundRect(205,90,80,200,10,10);

        g2d.setColor(CompletedBG);
        g2d.fillRoundRect(15,90,80,30,10,10);
        g2d.setColor(InProgrssBG);
        g2d.fillRoundRect(110,90,80,30,10,10);
        g2d.setColor(ToDoBG);
        g2d.fillRoundRect(205,90,80,30,10,10);

        if (ThemeManager.isDarkMode()) {
            completedLabel.setForeground(Color.white);
            inProgressLabel.setForeground(Color.white);
            toDoLabel.setForeground(Color.white);
        } else {
            completedLabel.setForeground(Color.black);
            inProgressLabel.setForeground(Color.black);
            toDoLabel.setForeground(Color.black);
        }
    }
}
