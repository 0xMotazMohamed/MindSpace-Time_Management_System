package timemanager.gui;

import timemanager.data.dto.Account;
import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;
import timemanager.data.dto.flyweight.StatusType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProjectPanel extends JPanel {
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

    Font titleFont = new Font(null, Font.BOLD, 36);
    Font textFont = new Font(null, Font.PLAIN, 16);
    Font subTitleFont = new Font(null, Font.BOLD, 24);

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

    int lastX = 85;
    int lastY = 260;

    public ProjectPanel(MainFrame mainFrame,SideBarPanel SBP , Account account, Project project) {
        setLayout(null);
        this.p = project;

        add(SBP);

        projectName = new JLabel(p.getName(), SwingConstants.CENTER);
        projectName.setFont(titleFont);
        labelSize = projectName.getPreferredSize();
        projectName.setBounds(90,80,labelSize.width, labelSize.height);

        projectDes = new JTextArea(5,200);
        projectDes.setLineWrap(true);
        projectDes.setText(p.getDescription());
        projectDes.setBounds(85,125,550,90);
        projectDes.setFont(textFont);
        projectDes.setEditable(false);
        projectDes.setFocusable(false);
        projectDes.setOpaque(false);

        completedLabel = new JLabel("Completed", SwingConstants.LEFT);
        completedLabel.setFont(subTitleFont);
        completedLabel.setBounds(95,230,160,30);
        inProgressLabel = new JLabel("InProgress", SwingConstants.LEFT);
        inProgressLabel.setFont(subTitleFont);
        inProgressLabel.setBounds(95,380,160,30);
        toDoLabel = new JLabel("ToDo", SwingConstants.LEFT);
        toDoLabel.setFont(subTitleFont);
        toDoLabel.setBounds(95,530,160,30);

        paintTasks:{
            for (Task task : p.getToDoTasks()) {
                paintTask(task, 1, 210);
            }
            for (Task task : p.getInProgressTasks()) {
                paintTask(task, 1, 115);
            }
            for (Task task : p.getCompletedTasks()) {
                paintTask(task, 1, 20);
            }
        }

        add(projectName);
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

        labelSize = taskName.getPreferredSize();
        taskName.setBounds(lastX + 10,lastY + 10
                , labelSize.width, labelSize.height);
        if (lastX > 650) {
            lastX = 85;
            lastY += labelSize.height + 10;
        } else {
            lastX += labelSize.width + 10;
        }
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

    public void painComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }
}
