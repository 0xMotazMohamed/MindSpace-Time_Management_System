package timemanager.gui;

import timemanager.data.dto.Account;
import timemanager.data.dto.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SideBarPanel extends JPanel {
    byte mainPanel = 0;

    ImageIcon hO = Images.homeOIcon;
    ImageIcon hF = Images.homeFIcon;
    ImageIcon aO = Images.alarmOIcon;
    ImageIcon aF = Images.alarmFIcon;
    ImageIcon fO = Images.folderOIcon;
    ImageIcon fF = Images.folderFIcon;

    Color whiteBG = new Color(225,225,225);
    Color whiteL = new Color(205,205,205);
    Color blackBG = new Color(50,50,50);
    Color blackL = new Color(70,70,70);

    private Color bIconBG = new Color(70, 70, 70);
    private Color wIconBG = new Color(205, 205, 205);

    JLabel home = new JLabel(hF);
    JLabel alarm = new JLabel(aO);

    ArrayList<JLabel> projectIcons = new ArrayList<>();

    public SideBarPanel(MainFrame mainFrame, Account account) {
        setLayout(null);

        home.setBounds(13,13, 36, 36);
        home.setOpaque(true);
        if (ThemeManager.isDarkMode()) {
            home.setBackground(bIconBG);
        } else {
            home.setBackground(wIconBG);
        }
        home.setBackground(wIconBG);
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    home.setBackground(bIconBG);
                } else {
                    home.setBackground(wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (mainPanel != 0)
                    if (ThemeManager.isDarkMode()) {
                        home.setBackground(blackBG);
                    } else {
                        home.setBackground(whiteBG);
                    }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                home.setIcon(hO);
                alarm.setIcon(aF);
                if (ThemeManager.isDarkMode()) {
                    home.setBackground(bIconBG);
                    alarm.setBackground(blackBG);
                } else {
                    home.setBackground(wIconBG);
                    alarm.setBackground(whiteBG);
                }
                for (JLabel pIcon : projectIcons) {
                    pIcon.setIcon(fO);
                    if (ThemeManager.isDarkMode()) {
                        pIcon.setBackground(blackBG);
                    } else {
                        pIcon.setBackground(whiteBG);
                    }
                }
                System.out.println("home Clicked");
                mainFrame.setSBP(SideBarPanel.this);
                mainFrame.getAppPanel().setSBP(SideBarPanel.this);
                mainFrame.getAppPanel().updateMiniProjectPanels();
                mainFrame.showPage("MainPage");
                mainPanel = 0;
                System.out.println(mainPanel);
            }
        });
        add(home);

        alarm.setBounds(13,62, 36, 36);
        alarm.setOpaque(true);
        alarm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    alarm.setBackground(bIconBG);
                } else {
                    alarm.setBackground(wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (mainPanel != 0)
                    if (ThemeManager.isDarkMode()) {
                        alarm.setBackground(blackBG);
                    } else {
                        alarm.setBackground(whiteBG);
                    }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                home.setIcon(hO);
                alarm.setIcon(aF);
                if (ThemeManager.isDarkMode()) {
                    alarm.setBackground(bIconBG);
                } else {
                    alarm.setBackground(wIconBG);
                }
                for (JLabel pIcon : projectIcons) {
                    pIcon.setIcon(fO);
                    if (ThemeManager.isDarkMode()) {
                        pIcon.setBackground(blackBG);
                    } else {
                        pIcon.setBackground(whiteBG);
                    }
                }
                System.out.println("alarm Clicked");
                mainFrame.setSBP(SideBarPanel.this);
                mainFrame.getAppPanel().setSBP(SideBarPanel.this);
                mainFrame.showPage("MainPage");
                mainPanel = 0;
                System.out.println(mainPanel);
            }
        });
        add(alarm);

        paintProjects:{
            int projectN = 0;
            for (Project project : account.getProjects()) {
                projectN++;
                paintProject(mainFrame, account ,project , projectN);
            }
            revalidate();
            repaint();
        }
    }

    public void paintProject(MainFrame mainFrame, Account account , Project p, int n) {
        JLabel projectIcon = new JLabel(fO);
        projectIcons.add(projectIcon);
        projectIcon.setOpaque(true);
        projectIcon.setBounds(13, 62 + (49 * n), 36, 36); //y=62,111,160,109
        add(projectIcon);

        for (JLabel pIcon : projectIcons) {
            if (pIcon.equals(projectIcon) && mainPanel == projectIcons.indexOf(pIcon)) {
                if (ThemeManager.isDarkMode()) {
                    projectIcon.setBackground(bIconBG);
                } else {
                    projectIcon.setBackground(wIconBG);
                }
            }
        }
        projectIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                projectIcon.setIcon(fF);
                if (ThemeManager.isDarkMode()) {
                    projectIcon.setBackground(bIconBG);
                } else {
                    projectIcon.setBackground(wIconBG);
                }
                home.setIcon(hO);
                alarm.setIcon(aO);
                if (ThemeManager.isDarkMode()) {
                    home.setBackground(blackBG);
                } else {
                    home.setBackground(whiteBG);
                }
                mainFrame.setProject(p);
                mainFrame.showPage("ProjectPage");

                for (JLabel pIcon : projectIcons) {
                    if (!pIcon.equals(projectIcon)) {
                        pIcon.setIcon(fO);
                        if (ThemeManager.isDarkMode()) {
                            pIcon.setBackground(blackBG);
                        } else {
                            pIcon.setBackground(whiteBG);
                        }
                    } else {
                        mainPanel = (byte) projectIcons.indexOf(pIcon);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (ThemeManager.isDarkMode()) {
                    projectIcon.setBackground(bIconBG);
                } else {
                    projectIcon.setBackground(wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                for (JLabel pIcon : projectIcons) {
                    if (!pIcon.equals(projectIcon)) {
                        if (ThemeManager.isDarkMode()) {
                            projectIcon.setBackground(blackBG);
                        } else {
                            projectIcon.setBackground(whiteBG);
                        }
                    } else {
                        if (mainPanel == (byte) (projectIcons.indexOf(pIcon) + 1)) {
                            System.out.println(mainPanel == (byte) (projectIcons.indexOf(pIcon) + 1));
                            if (ThemeManager.isDarkMode()) {
                                projectIcon.setBackground(Color.RED);
                            } else {
                                projectIcon.setBackground(Color.RED);
                            }
                        }
                    }
                }
            }
        });
    }

    public void assignPanel(int n) {
        this.mainPanel = (byte) n;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (ThemeManager.isDarkMode()) {
            g2d.setColor(blackBG);
            g2d.fillRect(0,0,62,698);
            g2d.setColor(blackL);
            g2d.fillRect(62,0,2,698);
            switch (mainPanel) {
                case 0:
                    for (JLabel pIcons : projectIcons)
                        pIcons.setBackground(blackBG);
                    g2d.setColor(bIconBG);
                    g2d.fillRoundRect(13, 13 + (49*mainPanel), 36, 36, 4, 4);
                    break;
                case 1,2,3,4:
                    for (JLabel pIcons : projectIcons) {
                        if (projectIcons.indexOf(pIcons) == mainPanel)
                            continue;
                        pIcons.setBackground(blackBG);
                    }
                    g2d.setColor(bIconBG);
                    g2d.fillRoundRect(13, 13 + (49*mainPanel), 36, 36, 4, 4);
                    break;
            }
        } else {
            g2d.setColor(whiteBG);
            g2d.fillRect(0,0,62,698);
            g2d.setColor(whiteL);
            g2d.fillRect(62,0,2,698);
            switch (mainPanel) {
                case 0:
                    if (ThemeManager.isDarkMode()) {
                        g2d.setColor(blackBG);
                        g2d.fillRoundRect(13, 62, 36, 36, 4, 4);
                        for (JLabel pIcons : projectIcons)
                            pIcons.setBackground(blackBG);
                        g2d.setColor(bIconBG);
                        g2d.fillRoundRect(13, 13 + (49*mainPanel), 36, 36, 4, 4);
                    } else {
                        g2d.setColor(whiteBG);
                        g2d.fillRoundRect(13, 62, 36, 36, 4, 4);
                        for (JLabel pIcons : projectIcons)
                            pIcons.setBackground(whiteBG);
                        g2d.setColor(wIconBG);
                        g2d.fillRoundRect(13, 13 + (49*mainPanel), 36, 36, 4, 4);
                    }
                    break;
                case 1,2,3,4:
                    if (ThemeManager.isDarkMode()) {
                        g2d.setColor(blackBG);
                        g2d.fillRoundRect(13, 13, 36, 36, 4, 4);
                        g2d.fillRoundRect(13, 62, 36, 36, 4, 4);
                        for (JLabel pIcons : projectIcons) {
                            if (projectIcons.indexOf(pIcons) == mainPanel)
                                continue;
                            pIcons.setBackground(blackBG);
                        }
                        g2d.setColor(bIconBG);
                        g2d.fillRoundRect(13, 13 + (49*(mainPanel+1)), 36, 36, 4, 4);
                    } else {
                        g2d.setColor(whiteBG);
                        g2d.fillRoundRect(13, 13, 36, 36, 4, 4);
                        g2d.fillRoundRect(13, 62, 36, 36, 4, 4);
                        for (JLabel pIcons : projectIcons) {
                            if (projectIcons.indexOf(pIcons) == mainPanel)
                                continue;
                            pIcons.setBackground(whiteBG);
                        }
                        g2d.setColor(wIconBG);
                        g2d.fillRoundRect(13, 13 + (49*(mainPanel+1)), 36, 36, 4, 4);
                    }
                    break;
                case 5:
                    if (ThemeManager.isDarkMode()) {
                        g2d.setColor(blackBG);
                        g2d.fillRoundRect(13, 13, 36, 36, 4, 4);
                        for (JLabel pIcons : projectIcons) {
                            if (projectIcons.indexOf(pIcons) == mainPanel)
                                continue;
                            pIcons.setBackground(blackBG);
                        }
                        g2d.setColor(bIconBG);
                        g2d.fillRoundRect(13, 62, 36, 36, 4, 4);
                    } else {
                        g2d.setColor(whiteBG);
                        g2d.fillRoundRect(13, 13, 36, 36, 4, 4);
                        for (JLabel pIcons : projectIcons) {
                            if (projectIcons.indexOf(pIcons) == mainPanel)
                                continue;
                            pIcons.setBackground(whiteBG);
                        }
                        g2d.setColor(wIconBG);
                        g2d.fillRoundRect(13, 62, 36, 36, 4, 4);
                    }
            }
        }
    }
}
