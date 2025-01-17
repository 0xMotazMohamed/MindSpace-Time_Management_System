package org.example.gui;

import org.example.bao.BAOFactory;
import org.example.data.dao.DAOFactory;
import org.example.data.dto.Account;
import org.example.data.dto.Project;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    DAOFactory daoFactory = new DAOFactory();
    BAOFactory baoFactory = new BAOFactory(daoFactory);


    private Account account;
    private Project project;
    private SideBarPanel SBP;
    private AppPanel appPanel;
    private ProjectPanel projectPanel ;
    private ReminderPanel reminderPanel;

    public MainFrame() {
        setTitle("Time Management");
        setSize(736, 759);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("timemanager/gui/icon.png");
        this.setIconImage(icon.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new WelcomePanel(this), "WelcomePage");
        mainPanel.add(new SignInPanel(this, baoFactory), "SignInPage");
        mainPanel.add(new SignUpPanel(this, baoFactory), "SignUpPage");

        add(mainPanel);
        setResizable(false);
        setVisible(true);
    }

    public void setAccount(Account account) {
        this.account = account;
        appPanel = new AppPanel(this, SBP, baoFactory, account);
        mainPanel.add(appPanel, "MainPage");
        mainPanel.add(new SideBarPanel(this, account),"SideBar");
    }

    public void setProject(Project project) {
        this.project = project;
        mainPanel.add(new ProjectPanel(this,SBP ,account, project),"ProjectPage");
    }

    public void setSBP(SideBarPanel sideBarPanel) {
        this.SBP = sideBarPanel;
    }

    public AppPanel getAppPanel() {
        return appPanel;
    }

    public void setProjectPanel(ProjectPanel projectPanel) {
        this.projectPanel = projectPanel;
    }

    public ProjectPanel getProjectPanel() {
        return projectPanel;
    }

    public void showPage(String pageName) {
            cardLayout.show(mainPanel, pageName);
    }
}
