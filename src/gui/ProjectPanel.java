package org.example.gui;

import org.example.data.dto.Account;
import org.example.data.dto.Project;
import org.example.data.dto.features.Task;
import org.example.data.dto.flyweight.StatusType;
import org.example.gui.MainFrame;
import org.example.gui.SideBarPanel;
import org.example.gui.ThemeManager;

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
    SideBarPanel SBP;

    ArrayList<JLabel> taskNames = new ArrayList<>();

    JLabel projectName;
    JTextArea projectDes;
    JLabel completedLabel;
    JLabel inProgressLabel;
    JLabel toDoLabel;

    Dimension labelSize;

    int lastX; //95
    int lastY; //260

    public ProjectPanel(MainFrame mainFrame, SideBarPanel SBP , Account account, Project project) {
        setLayout(null);
        this.p = project;
        this.SBP = SBP;

        add(SBP);
        revalidate();
        repaint();

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

        toDoLabel = new JLabel("ToDo", SwingConstants.LEFT);
        toDoLabel.setFont(subTitleFont);
        toDoLabel.setBounds(100,230,160,30);
        inProgressLabel = new JLabel("InProgress", SwingConstants.LEFT);
        inProgressLabel.setFont(subTitleFont);
        inProgressLabel.setBounds(100,390,160,30);
        completedLabel = new JLabel("Completed", SwingConstants.LEFT);
        completedLabel.setFont(subTitleFont);
        completedLabel.setBounds(100,550,160,30);

        paintTasks:{
            lastX = 95;
            lastY = 260;
            for (Task task : p.getToDoTasks()) {
                paintTask(task, 0);
            }
            lastX = 95;
            lastY = 420;
            for (Task task : p.getInProgressTasks()) {
                paintTask(task, 1);
            }
            lastX = 95;
            lastY = 580;
            for (Task task : p.getCompletedTasks()) {
                paintTask(task, 2);
            }
        }

        add(projectName);
        add(projectDes);
        add(completedLabel);
        add(inProgressLabel);
        add(toDoLabel);
    }

    public void paintTask(Task task,  int n) {
        JLabel taskName = new JLabel(task.getTitle(), SwingConstants.CENTER);
        taskNames.add(taskName);
        taskName.setOpaque(true);
        taskName.setFont(textFont);

        labelSize = taskName.getPreferredSize();
        if (lastX + 10 + labelSize.width > 670) {
            lastX = 95;
            lastY += labelSize.height + 10;
        }
        if (lastY  + 10 + labelSize.height > (355 + 160*n)) {
            return;
        }
        taskName.setBounds(lastX + 10, lastY + 10
                , labelSize.width + 5, labelSize.height);
        lastX += 10 + labelSize.width + 5;
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

                    if (finalX >= 100 && finalX <= 680) {
                        if (finalY >= 265 && finalY <= 360) {
                            if (!statusType.equals(StatusType.TODO)) {
                                p.transferTask(task, p.getTasksByStatus(statusType),
                                        p.getToDoTasks());
                                remove(taskName);
                                updateTasksDisplay();
                            } else {
                                remove(taskName);
                                updateTasksDisplay();
                            }
                        } else if (finalY >= 425 && finalY <= 520) {
                            if (!statusType.equals(StatusType.INPROGRESS)) {
                                p.transferTask(task, p.getTasksByStatus(statusType),
                                        p.getInProgressTasks());
                                remove(taskName);
                                updateTasksDisplay();
                            } else {
                                remove(taskName);
                                updateTasksDisplay();
                            }
                        } else if (finalY >= 585 && finalY <= 680) {
                            if (!statusType.equals(StatusType.COMPLETED)) {
                                p.transferTask(task, p.getTasksByStatus(statusType),
                                        p.getCompletedTasks());
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
        add(SBP);
        add(projectName);
        add(projectDes);
        add(completedLabel);
        add(inProgressLabel);
        add(toDoLabel);
        lastX = 95;
        lastY = 260;
        for (Task task : p.getToDoTasks()) {
            paintTask(task, 0);
        }
        lastX = 95;
        lastY = 420;
        for (Task task : p.getInProgressTasks()) {
            paintTask(task, 1);
        }
        lastX = 95;
        lastY = 580;
        for (Task task : p.getCompletedTasks()) {
            paintTask(task, 2);
        }
    }

    public void setSBP(SideBarPanel sideBarPanel) {
        this.SBP = sideBarPanel;
        add(SBP);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (ThemeManager.isDarkMode()) {
            g2d.setColor(bProjectBG);
            completedLabel.setForeground(Color.white);
            inProgressLabel.setForeground(Color.white);
            toDoLabel.setForeground(Color.white);
            for (JLabel labels : taskNames)
                labels.setBackground(bTaskBG);
        } else {
            g2d.setColor(wProjectBG);
            completedLabel.setForeground(Color.black);
            inProgressLabel.setForeground(Color.black);
            toDoLabel.setForeground(Color.black);
            for (JLabel labels : taskNames)
                labels.setBackground(wTaskBG);
        }
        g2d.fillRoundRect(80,80,625,625,30,30);
        g2d.setColor(ToDoBG);
        g2d.fillRoundRect(90,230,80,50,20,20);
        g2d.fillRoundRect(90,255,600,115,20,20);
        g2d.setColor(InProgrssBG);
        g2d.fillRoundRect(90,390,140,50,20,20);
        g2d.fillRoundRect(90,415,600,115,20,20);
        g2d.setColor(CompletedBG);
        g2d.fillRoundRect(90,550,140,50,20,20);
        g2d.fillRoundRect(90,575,600,115,20,20);
        if (ThemeManager.isDarkMode()) {
            g2d.setColor(bStatusBG);
            g2d.fillRoundRect(100,265,580,95,10,10);
            g2d.fillRoundRect(100,425,580,95,10,10);
            g2d.fillRoundRect(100,585,580,95,10,10);
        } else {
            g2d.setColor(wStatusBG);
            g2d.fillRoundRect(100,265,580,95,10,10);
            g2d.fillRoundRect(100,425,580,95,10,10);
            g2d.fillRoundRect(100,585,580,95,10,10);
        }
    }
}
