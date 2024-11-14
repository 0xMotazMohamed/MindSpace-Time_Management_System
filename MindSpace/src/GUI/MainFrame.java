package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Time Management");
        setSize(736, 759);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

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
