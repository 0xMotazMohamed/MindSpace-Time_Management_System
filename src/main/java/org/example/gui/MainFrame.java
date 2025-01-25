package org.example.gui;

import org.example.bao.BAOFactory;
import org.example.data.dao.AccountDAOFactory;
import org.example.data.dto.Account;
import org.example.data.dto.Project;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    AccountDAOFactory accountDaoFactory = new AccountDAOFactory();
    BAOFactory baoFactory = new BAOFactory(accountDaoFactory);


    private Account account;
    private SideBarPanel SBP;
    private MainBarPanel MBP;
    private AppPanel appPanel;

    Color whiteBG = new Color(225,225,225);
    Color whiteL = new Color(205,205,205);
    Color blackBG = new Color(50,50,50);
    Color blackL = new Color(70,70,70);
    Color bIconBG = new Color(70, 70, 70);
    Color wIconBG = new Color(205, 205, 205);

    public MainFrame() {
        setTitle("Time Management");
        setIconImage(Images.programImage);
        setSize(736, 759);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        appPanel = new AppPanel(this, baoFactory, account);
        mainPanel.add(appPanel, "MainPage");
        mainPanel.add(new SideBarPanel(this, account),"SideBar");
    }

    public void setProjectPanel(Project project) {
        mainPanel.add(new ProjectPanel(this, SBP, MBP, account, project),"ProjectPage");
    }

    public void setSBP(SideBarPanel sideBarPanel) {
        this.SBP = sideBarPanel;
    }

    public void setMBP(MainBarPanel mainBarPanel) {
        this.MBP = mainBarPanel;
    }

    public AppPanel getAppPanel() {
        return appPanel;
    }

    public void showPage(String pageName) {
            cardLayout.show(mainPanel, pageName);
    }
}
