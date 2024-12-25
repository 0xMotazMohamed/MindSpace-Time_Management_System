package timemanager.gui;

import timemanager.business.bao.BAOFactory;
import timemanager.data.dao.DAOFactory;
import timemanager.data.dto.Account;
import timemanager.data.dto.Project;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    DAOFactory daoFactory = new DAOFactory();
    BAOFactory baoFactory = new BAOFactory(daoFactory);
    private Account account;

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
//        this.account = account;
        mainPanel.add(new AppPanel(this, baoFactory, account), "MainPage");
        mainPanel.add(new SideBarPanel(this, account),"SideBar");
    }

    public void setProject(Project project) {
        mainPanel.add(new ProjectPanel(this, project),"ProjectPage");
    }

    public void showPage(String pageName) {
            cardLayout.show(mainPanel, pageName);
    }
}
