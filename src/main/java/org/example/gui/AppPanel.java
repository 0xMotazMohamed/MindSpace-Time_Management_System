package org.example.gui;

import org.example.bao.BAOFactory;
import org.example.data.dto.Account;
import org.example.data.dto.Project;
import org.example.gui.shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AppPanel extends JPanel {
    Dimension labelSize;

    Rectangle mainBG = new Rectangle(0,0,720,62,null);
    Rectangle mainL = new Rectangle(0,0,720,2,null);

    SideBarPanel SBP;
    MainBarPanel MBP;
    ArrayList<MiniProjectPanel> miniProjectPanels = new ArrayList<>();
    JLabel addingProjectLabel;
    Account account;

    public AppPanel(MainFrame mainFrame, BAOFactory baoFactory, Account account) {
        setLayout(null);
        this.account = account;

        Font textFont = new Font(null, Font.PLAIN, 16);

        addingProjectLabel = new JLabel("<html><u>+ New Project</u></html>", SwingConstants.CENTER);

        if (ThemeManager.isDarkMode()) {
            mainBG.updateColor(mainFrame.blackBG);
            mainL.updateColor(mainFrame.blackL);
        } else {
            mainBG.updateColor(mainFrame.whiteBG);
            mainL.updateColor(mainFrame.whiteL);
        }

        MainBarPanel mainBarPanel = new MainBarPanel(mainFrame, baoFactory,account);
        mainBarPanel.setBounds(0,0,720,64);
        setMBP(mainBarPanel);
        mainFrame.setMBP(MBP);
        add(mainBarPanel);

        SideBarPanel sideBarPanel = new SideBarPanel(mainFrame, account);
        sideBarPanel.setBounds(0,64,64,656);
        mainFrame.setSBP(sideBarPanel);
        sideBarPanel.assignPanel(0);
        add(sideBarPanel);

        addingProjectLabel.setFont(textFont);
        addingProjectLabel.setForeground(new Color(0, 107, 255));
        labelSize = addingProjectLabel.getPreferredSize();
        addingProjectLabel.setBounds(100, 100, labelSize.width, labelSize.height);
        addingProjectLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addingProjectLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddingProject addingProject = new AddingProject(account);
                Project p = addingProject.getProject();
                if (p != null)
                    if (account.getProjects().size() == 4) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(400, 400, 300, 300);
                        add(miniProjectPanel);
                        remove(addingProjectLabel);
                        sideBarPanel.paintProject(mainFrame, p,4);
                    } else if (account.getProjects().size() == 3) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(80, 400, 300, 300);
                        add(miniProjectPanel);
                        addingProjectLabel.setBounds(400, 400, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                        sideBarPanel.paintProject(mainFrame, p ,3);
                    } else if (account.getProjects().size() == 2) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(400, 80, 300, 300);
                        add(miniProjectPanel);
                        addingProjectLabel.setBounds(100, 400, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                        sideBarPanel.paintProject(mainFrame, p ,2);
                    } else if (account.getProjects().size() == 1) {
                        MiniProjectPanel miniProjectPanel = new MiniProjectPanel(p);
                        miniProjectPanels.add(miniProjectPanel);
                        miniProjectPanel.setBounds(80, 80, 300, 300);
                        add(miniProjectPanel);
                        addingProjectLabel.setBounds(400, 100, addingProjectLabel.getWidth(),addingProjectLabel.getHeight());
                        sideBarPanel.paintProject(mainFrame, p,1);
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

        buildingMiniProjects();

        add(addingProjectLabel);
    }

    public void buildingMiniProjects() {
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

    public void setSBP(SideBarPanel SBP) {
        this.SBP = SBP;
        add(SBP);
    }

    public void setMBP(MainBarPanel MBP) {
        this.MBP = MBP;
        add(MBP);
    }

    public void updateMiniProjectPanels() {
        for (MiniProjectPanel miniProjectPanel : miniProjectPanels)
            miniProjectPanel.updateTasksDisplay();
    }
}
