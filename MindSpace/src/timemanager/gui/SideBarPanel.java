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
    ImageIcon fO = Images.folderOIcon;
    ImageIcon fF = Images.folderFIcon;

    Color whiteBG = new Color(225,225,225);
    Color whiteL = new Color(205,205,205);
    Color blackBG = new Color(50,50,50);
    Color blackL = new Color(70,70,70);

    private Color bIconBG = new Color(70, 70, 70);
    private Color wIconBG = new Color(205, 205, 205);

    JLabel home = new JLabel(hF);

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
                home.setIcon(hF);
                if (ThemeManager.isDarkMode()) {
                    home.setBackground(bIconBG);
                } else {
                    home.setBackground(wIconBG);
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
                mainFrame.showPage("MainPage");
                mainPanel = 0;
                System.out.println(mainPanel);
            }
        });

        paintProjects:{
            int projectN = 0;
            for (Project project : account.getProjects()) {
                projectN++;
                paintProject(mainFrame ,project , projectN);
            }
            revalidate();
            repaint();
        }
        add(home);
    }

    public void paintProject(MainFrame mainFrame , Project p, int n) {
        JLabel projectIcon = new JLabel(fO);
        projectIcons.add(projectIcon);
        projectIcon.setOpaque(true);
        projectIcon.setBounds(13, 13 + (49 * n), 36, 36); //y=62,111,160,109
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
//            g2d.setColor(wIconBG);
//            home.setBackground(whiteBG);
//            for (JLabel pIcons : projectIcons)
//                pIcons.setBackground(whiteBG);
//            g2d.setColor(wIconBG);
//            g2d.fillRoundRect(13, 13 + (49*mainPanel), 36, 36, 4, 4);
            switch (mainPanel) {
                case 0:
                    for (JLabel pIcons : projectIcons)
                        pIcons.setBackground(whiteBG);
                    g2d.setColor(wIconBG);
                    g2d.fillRoundRect(13, 13 + (49*mainPanel), 36, 36, 4, 4);
                    break;
                case 1,2,3,4:
                    for (JLabel pIcons : projectIcons) {
                        if (projectIcons.indexOf(pIcons) == mainPanel)
                            continue;
                        pIcons.setBackground(whiteBG);
                    }
                    g2d.setColor(wIconBG);
                    g2d.fillRoundRect(13, 13 + (49*mainPanel), 36, 36, 4, 4);
                    break;
            }
        }
    }
}
