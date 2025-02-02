package org.example.gui;

import org.example.data.dto.Account;
import org.example.data.dto.Project;
import org.example.data.dto.features.Pomodoro;
import org.example.data.dto.features.Reminder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SideBarPanel extends JPanel {
    MainFrame mainFrame;
    byte mainPanel = 0;

    ImageIcon hO = Images.homeOIcon;
    ImageIcon hF = Images.homeFIcon;
    ImageIcon aO = Images.alarmOIcon;
    ImageIcon aF = Images.alarmFIcon;
    ImageIcon cO = Images.calenderOIcon;
    ImageIcon cF = Images.calenderFIcon;
    ImageIcon sO = Images.sunOIcon;
    ImageIcon sF = Images.sunFIcon;
    ImageIcon mO = Images.moonOIcon;
    ImageIcon mF = Images.moonFIcon;
    ImageIcon fO = Images.folderOIcon;
    ImageIcon fF = Images.folderFIcon;
    ImageIcon fAO = Images.folderAddOIcon;
    ImageIcon fAF = Images.folderAddFIcon;

    JLabel home = new JLabel(hF);
    JLabel pomodoro = new JLabel(aO);
    JLabel reminder = new JLabel(cO);
    JLabel themeMode = new JLabel(mO);
    JLabel folderAdd = new JLabel(fAO);

    ArrayList<JLabel> projectIcons = new ArrayList<>();

    public SideBarPanel(MainFrame mainFrame, Account account) {
        this.mainFrame = mainFrame;
        setLayout(null);

        home.setBounds(13,13, 36, 36);
        home.setOpaque(true);
        home.setBackground(mainFrame.wIconBG);
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    home.setBackground(mainFrame.bIconBG);
                } else {
                    home.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (mainPanel != 0)
                    if (ThemeManager.isDarkMode()) {
                        home.setBackground(mainFrame.blackBG);
                    } else {
                        home.setBackground(mainFrame.whiteBG);
                    }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                home.setIcon(hF);
                if (ThemeManager.isDarkMode()) {
                    home.setBackground(mainFrame.bIconBG);
                    pomodoro.setBackground(mainFrame.blackBG);
                    reminder.setBackground(mainFrame.blackBG);
                    folderAdd.setBackground(mainFrame.blackBG);
                } else {
                    home.setBackground(mainFrame.wIconBG);
                    pomodoro.setBackground(mainFrame.whiteBG);
                    reminder.setBackground(mainFrame.whiteBG);
                    folderAdd.setBackground(mainFrame.whiteBG);
                }
                for (JLabel pIcon : projectIcons) {
                    pIcon.setIcon(fO);
                    if (ThemeManager.isDarkMode()) {
                        pIcon.setBackground(mainFrame.blackBG);
                    } else {
                        pIcon.setBackground(mainFrame.whiteBG);
                    }
                }
                mainFrame.setSBP(SideBarPanel.this);
                mainFrame.getAppPanel().setSBP(SideBarPanel.this);
                mainFrame.getAppPanel().setMBP(mainFrame.getAppPanel().MBP);
                mainFrame.showPage("MainPage");
                mainPanel = 0;
            }
        });
        add(home);

        pomodoro.setBounds(13,555, 36, 36);
        pomodoro.setOpaque(true);
        pomodoro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pomodoro.setIcon(aF);
                if (ThemeManager.isDarkMode()) {
                    pomodoro.setBackground(mainFrame.bIconBG);
                } else {
                    pomodoro.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pomodoro.setIcon(aO);
                if (ThemeManager.isDarkMode()) {
                    pomodoro.setBackground(mainFrame.blackBG);
                } else {
                    pomodoro.setBackground(mainFrame.whiteBG);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                pomodoro.setIcon(aO);
                if (ThemeManager.isDarkMode()) {
                    pomodoro.setBackground(mainFrame.blackBG);
                } else {
                    pomodoro.setBackground(mainFrame.whiteBG);
                }
                Pomodoro pomodoro = new Pomodoro();
            }
        });
        add(pomodoro);

        reminder.setBounds(13,503, 36, 36);
        reminder.setOpaque(true);
        reminder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                reminder.setIcon(cF);
                if (ThemeManager.isDarkMode()) {
                    reminder.setBackground(mainFrame.bIconBG);
                } else {
                    reminder.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                reminder.setIcon(cO);
                if (ThemeManager.isDarkMode()) {
                    reminder.setBackground(mainFrame.blackBG);
                } else {
                    reminder.setBackground(mainFrame.whiteBG);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                reminder.setIcon(cO);
                if (ThemeManager.isDarkMode()) {
                    reminder.setBackground(mainFrame.blackBG);
                } else {
                    reminder.setBackground(mainFrame.whiteBG);
                }
                Reminder reminder = new Reminder();
                reminder.createReminder();
            }
        });
        add(reminder);

        themeMode.setBounds(13,607, 36, 36);
        themeMode.setOpaque(true);
        themeMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ThemeManager.toggleTheme(mainFrame);
                if (ThemeManager.isDarkMode()) {
                    themeMode.setIcon(sF);
                    themeMode.setBackground(mainFrame.bIconBG);
                } else {
                    themeMode.setIcon(mF);
                    themeMode.setBackground(mainFrame.wIconBG);

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    themeMode.setIcon(sF);
                    themeMode.setBackground(mainFrame.bIconBG);
                } else {
                    themeMode.setIcon(mF);
                    themeMode.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    themeMode.setIcon(sO);
                    themeMode.setBackground(mainFrame.blackBG);
                } else {
                    themeMode.setIcon(mO);
                    themeMode.setBackground(mainFrame.whiteBG);
                }
            }
        });
        add(themeMode);

        folderAdd.setOpaque(true);
        folderAdd.setBounds(13, 13 + (49), 36, 36);
        folderAdd.setVisible(true);
        folderAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                folderAdd.setIcon(fAO);
                if (ThemeManager.isDarkMode()) {
                    folderAdd.setBackground(mainFrame.bIconBG);
                } else {
                    folderAdd.setBackground(mainFrame.wIconBG);
                }
                AddingProject addingProject = new AddingProject(account);
                Project project = addingProject.getProject();
                paintProject(mainFrame, project, account.getProjects().size());
                if (account.getProjects().size() <= 4) {
                    mainFrame.getAppPanel().buildingMiniProjects();
                }
                revalidate();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                folderAdd.setIcon(fAF);
                if (ThemeManager.isDarkMode()) {
                    folderAdd.setBackground(mainFrame.bIconBG);
                } else {
                    folderAdd.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                folderAdd.setIcon(fAO);
                if (ThemeManager.isDarkMode()) {
                    folderAdd.setBackground(mainFrame.blackBG);
                } else {
                    folderAdd.setBackground(mainFrame.whiteBG);
                }
            }
        });
        add(folderAdd);

        paintProjects:{
            int projectN = 0;
            for (Project project : account.getProjects()) {
                paintProject(mainFrame, project, ++projectN);
            }
            revalidate();
            repaint();
        }

        if (ThemeManager.isDarkMode()) {
            home.setBackground(mainFrame.bIconBG);
            folderAdd.setBackground(mainFrame.blackBG);
            reminder.setBackground(mainFrame.blackBG);
            pomodoro.setBackground(mainFrame.blackBG);
            themeMode.setBackground(mainFrame.blackBG);
        } else {
            home.setBackground(mainFrame.wIconBG);
            folderAdd.setBackground(mainFrame.whiteBG);
            reminder.setBackground(mainFrame.whiteBG);
            pomodoro.setBackground(mainFrame.whiteBG);
            themeMode.setBackground(mainFrame.whiteBG);
        }
    }

    public void paintProject(MainFrame mainFrame, Project p, int n) {
        JLabel projectIcon = new JLabel(fO);
        projectIcons.add(projectIcon);
        projectIcon.setOpaque(true);
        projectIcon.setBounds(13, 13 + (49 * n), 36, 36); //y=13,62,111,160
        add(projectIcon);

        for (JLabel pIcon : projectIcons) {
            if (pIcon.equals(projectIcon) && mainPanel == projectIcons.indexOf(pIcon)) {
                if (ThemeManager.isDarkMode()) {
                    projectIcon.setBackground(mainFrame.bIconBG);
                } else {
                    projectIcon.setBackground(mainFrame.wIconBG);
                }
            }
        }

        projectIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.setIcon(hO);
                for (JLabel pIcon : projectIcons) {
                        pIcon.setIcon(fO);
                }
                projectIcon.setIcon(fF);
                if (ThemeManager.isDarkMode()) {
                    projectIcon.setBackground(mainFrame.bIconBG);
                } else {
                    projectIcon.setBackground(mainFrame.wIconBG);
                }
                assignPanel(n);
                mainFrame.setProjectPanel(p);
                mainFrame.showPage("ProjectPage");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    projectIcon.setBackground(mainFrame.bIconBG);
                } else {
                    projectIcon.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    for (JLabel pIcon : projectIcons) {
                        if (projectIcons.indexOf(pIcon)+1 == getPanel()) {
                            pIcon.setBackground(mainFrame.bIconBG);
                            continue;
                        }
                        pIcon.setBackground(mainFrame.blackBG);
                    }
                } else {
                    for (JLabel pIcon : projectIcons) {
                        if (projectIcons.indexOf(pIcon)+1 == getPanel()) {
                            pIcon.setBackground(mainFrame.wIconBG);
                            continue;
                        }
                        pIcon.setBackground(mainFrame.whiteBG);
                    }
                }
            }
        });

        if (n < 9) {
            folderAdd.setBounds(13, 13 + (49 * (n + 1)), 36, 36);
        } else {
            folderAdd.setVisible(false);
        }
    }

    public void assignPanel(int n) {
        this.mainPanel = (byte) n;
    }
    public int getPanel(){
        return mainPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (ThemeManager.isDarkMode()) {
            g2d.setColor(mainFrame.blackBG);
            g2d.fillRect(0,0,62,698);
            g2d.setColor(mainFrame.blackL);
            g2d.fillRect(62,0,2,698);
            pomodoro.setBackground(mainFrame.blackBG);
            reminder.setBackground(mainFrame.blackBG);
            folderAdd.setBackground(mainFrame.blackBG);
            switch (getPanel()) {
                case 0:
                    for (JLabel pIcon : projectIcons)
                        pIcon.setBackground(mainFrame.blackBG);
                    home.setBackground(mainFrame.bIconBG);
                    break;
                case 1,2,3,4:
                    for (JLabel pIcon : projectIcons) {
                        pIcon.setBackground(mainFrame.blackBG);
                    }
                    projectIcons.get(getPanel()-1).setBackground(mainFrame.bIconBG);
                    home.setBackground(mainFrame.blackBG);
                    break;
            }
        } else {
            g2d.setColor(mainFrame.whiteBG);
            g2d.fillRect(0,0,62,698);
            g2d.setColor(mainFrame.whiteL);
            g2d.fillRect(62,0,2,698);
            pomodoro.setBackground(mainFrame.whiteBG);
            reminder.setBackground(mainFrame.whiteBG);
            folderAdd.setBackground(mainFrame.whiteBG);
            switch (mainPanel) {
                case 0:
                    for (JLabel pIcon : projectIcons)
                        pIcon.setBackground(mainFrame.whiteBG);
                    g2d.setColor(mainFrame.wIconBG);
                    home.setBackground(mainFrame.wIconBG);
                    break;
                case 1,2,3,4:
                    for (JLabel pIcon : projectIcons) {
                        pIcon.setBackground(mainFrame.whiteBG);
                    }
                    projectIcons.get(getPanel()-1).setBackground(mainFrame.wIconBG);
                    home.setBackground(mainFrame.whiteBG);
                    break;

            }
        }
    }
}
