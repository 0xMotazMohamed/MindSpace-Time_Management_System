package org.example.gui;

import org.example.bao.BAOFactory;
import org.example.data.dto.Account;
import org.example.data.dto.Project;
import org.example.gui.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AppPanel extends JPanel {
    Color blackForLightMode = new Color(32,32,32);
    Color whiteForDarkMode = new Color(233,233,233);

    Color whiteBG = new Color(225,225,225);
    Color whiteL = new Color(205,205,205);
    Color blackBG = new Color(50,50,50);
    Color blackL = new Color(70,70,70);

    Dimension labelSize;

    Rectangle mainBG = new Rectangle(0,0,720,62,null);
    Rectangle mainL = new Rectangle(0,0,720,2,null);

    SideBarPanel SBP;
    ArrayList<MiniProjectPanel> miniProjectPanels = new ArrayList<>();

    public AppPanel(MainFrame mainFrame, SideBarPanel sideBar, BAOFactory baoFactory, Account account) {
        setLayout(null);

        Font titleFont = new Font(null, Font.BOLD, 36);
        Font lineFont = new Font(null, Font.PLAIN, 24);
        Font textFont = new Font(null, Font.PLAIN, 16);

        JLabel titleLabel = new JLabel("Mind Space", SwingConstants.CENTER);
        JLabel dateLabel = new JLabel("12/3/2024",SwingConstants.CENTER);
        JLabel addingProjectLabel = new JLabel("<html><u>+ New Project</u></html>", SwingConstants.CENTER);

        if (ThemeManager.isDarkMode()) {
            mainBG.updateColor(blackBG);
            mainL.updateColor(blackL);
        } else {
            mainBG.updateColor(whiteBG);
            mainL.updateColor(whiteL);
        }

        mainBG.setBounds(0,0,720,62);
        add(mainBG);
        mainL.setBounds(0,62,720,2);
        add(mainL);

        SideBarPanel sideBarPanel = new SideBarPanel(mainFrame, account);
        sideBarPanel.setBounds(0,64,64,654);
        mainFrame.setSBP(sideBarPanel);
        sideBarPanel.assignPanel(0);
        add(sideBarPanel);

        titleLabel.setFont(titleFont);
        labelSize = titleLabel.getPreferredSize();
        titleLabel.setBounds(360 - (labelSize.width/2), 0, labelSize.width, labelSize.height);
        dateLabel.setFont(lineFont);
        labelSize = dateLabel.getPreferredSize();
        dateLabel.setBounds(20, 10, labelSize.width, labelSize.height);

        addingProjectLabel.setFont(textFont);
        addingProjectLabel.setForeground(new Color(0, 107, 255));
        labelSize = addingProjectLabel.getPreferredSize();
        addingProjectLabel.setBounds(100, 100, labelSize.width, labelSize.height);
        addingProjectLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addingProjectLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddingProject addingProject = new AddingProject(baoFactory, account);
                Project p = addingProject.getProject();
                if (p != null)
                    if (account.getProjects().size() == 4) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(400, 400, 300, 300);
                        add(miniProjectPanel);
                        remove(addingProjectLabel);
                        sideBarPanel.paintProject(mainFrame, account, p,4);
                    } else if (account.getProjects().size() == 3) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(80, 400, 300, 300);
                        add(miniProjectPanel);
                        addingProjectLabel.setBounds(400, 400, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                        sideBarPanel.paintProject(mainFrame, account, p ,3);
                    } else if (account.getProjects().size() == 2) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(400, 80, 300, 300);
                        add(miniProjectPanel);
                        addingProjectLabel.setBounds(100, 400, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                        sideBarPanel.paintProject(mainFrame, account, p ,2);
                    } else if (account.getProjects().size() == 1) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(80, 80, 300, 300);
                        add(miniProjectPanel);
                        addingProjectLabel.setBounds(400, 100, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                        sideBarPanel.paintProject(mainFrame, account , p,1);
                    }
                revalidate();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                addingProjectLabel.setForeground(new Color(90, 90, 90));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addingProjectLabel.setForeground(new Color(0,107,255));
            }
        });

        buildProjects:{
            int numProjectOn = 0;
            for (Project p : account.getProjects()) {
                numProjectOn++;
                MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                miniProjectPanel.setSize(300, 300);

                if (numProjectOn == 1) {
                    miniProjectPanel.setBounds(80, 80, 300, 300);
                    add(miniProjectPanel);
                    addingProjectLabel.setBounds(400, 100, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                } else if (numProjectOn == 2) {
                    miniProjectPanel.setBounds(400, 80, 300, 300);
                    add(miniProjectPanel);
                    addingProjectLabel.setBounds(100, 400, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                }else if (numProjectOn == 3) {
                    miniProjectPanel.setBounds(80, 400, 300, 300);
                    add(miniProjectPanel);
                    addingProjectLabel.setBounds(400, 400, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                } else if (numProjectOn == 4) {
                    miniProjectPanel.setBounds(400, 400, 300, 300);
                    add(miniProjectPanel);
                    remove(addingProjectLabel);
                }
            }
            revalidate();
            repaint();
        }

        JButton toggleButton = new JButton(ThemeManager.mode());
        toggleButton.setBounds(275, 600, 150,20);
        toggleButton.addActionListener(e -> {
            ThemeManager.toggleTheme(mainFrame);
            toggleButton.setText(ThemeManager.isDarkMode() ? "Switch to Light Mode" : "Switch to Dark Mode");
            if (ThemeManager.isDarkMode()) {
                mainBG.updateColor(blackBG);
            } else {
                mainBG.updateColor(whiteBG);
            }
        });

        JButton test = new JButton("log out");
        test.setBounds(275, 650, 150, 20);
        test.addActionListener(e -> mainFrame.showPage("WelcomePage"));
        add(test);

        add(titleLabel);
        add(dateLabel);
        add(addingProjectLabel);
        add(toggleButton);
    }

    public void setSBP(SideBarPanel sideBarPanel) {
        this.SBP = sideBarPanel;
        add(SBP);
    }

    public void updateMiniProjectPanels() {
        for (MiniProjectPanel miniProjectPanel : miniProjectPanels)
            miniProjectPanel.updateTasksDisplay();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
