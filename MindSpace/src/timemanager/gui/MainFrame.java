package timemanager.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;


    public MainFrame() {
        setTitle("Time Management");
        setSize(736, 759);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("timemanager/gui/icon.png");
        this.setIconImage(icon.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new AppPanelabdo(this), "MainPage");
        mainPanel.add(new WelcomePanel(this), "WelcomePage");
        mainPanel.add(new SignInPanel(this), "SignInPage");
        mainPanel.add(new SignUpPanel(this), "SignUpPage");

        add(mainPanel);
        setResizable(false);
        setVisible(true);
    }

    public void showPage(String pageName) {
            cardLayout.show(mainPanel, pageName);
    }
}
