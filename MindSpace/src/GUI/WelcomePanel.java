package GUI;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel(MainFrame mainFrame) {
        setLayout(null);

        JFrame frame = new JFrame("Time Management");
        JLabel titleLabel = new JLabel("Welcome");
        JLabel titleLabel1 = new JLabel("Time Management");
        JLabel messageLabel = new JLabel("Your App To Organize Your Life");
        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign Up");

        Font titleFont = new Font(null, Font.BOLD, 48);
        Font textFont = new Font(null, Font.PLAIN, 24);
        Font inputFont = new Font(null, Font.PLAIN, 16);
        Dimension labelSize;
        int x;
        int y;


        titleLabel.setFont(titleFont);
        labelSize = titleLabel.getPreferredSize();
        x = (720-labelSize.width) /2;
        y = (720-labelSize.height) /2;
        titleLabel.setBounds(x,y-300,labelSize.width,labelSize.height);

        titleLabel1.setFont(titleFont);
        labelSize = titleLabel1.getPreferredSize();
        x = (720-labelSize.width) /2;
        y = (720-labelSize.height) /2;
        titleLabel1.setBounds(x,y-250,labelSize.width,labelSize.height);

        messageLabel.setFont(textFont);
        labelSize = messageLabel.getPreferredSize();
        x = (720-labelSize.width) /2;
        y = (720-labelSize.height) /2;
        messageLabel.setBounds(x,y-200,labelSize.width,labelSize.height);

        signInButton.setFont(inputFont);
        labelSize = signInButton.getSize();
        x = (720-labelSize.width) /2;
        y = (720-labelSize.height) /2;
        signInButton.setBounds(x-150, y, 100, 30);
        signInButton.setFocusable(false);
        signInButton.addActionListener(e -> mainFrame.showPage("SignInPage"));

        signUpButton.setFont(inputFont);
        signUpButton.setBounds(x, y, 100, 30);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(e -> mainFrame.showPage("SignUpPage"));

        add(titleLabel);
        add(titleLabel1);
        add(messageLabel);
        add(signInButton);
        add(signUpButton);
    }
}
