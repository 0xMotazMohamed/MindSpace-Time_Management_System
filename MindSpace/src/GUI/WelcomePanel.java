package GUI;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel(MainFrame mainFrame) {
        setLayout(null);

        JLabel titleLabel = new JLabel("Welcome", SwingConstants.CENTER);
        JLabel titleLabel1 = new JLabel("Time Management", SwingConstants.CENTER);
        JLabel messageLabel = new JLabel("Your App To Organize Your Life", SwingConstants.CENTER);
        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign Up");

        Font titleFont = new Font(null, Font.BOLD, 48);
        Font textFont = new Font(null, Font.PLAIN, 24);
        Font inputFont = new Font(null, Font.PLAIN, 16);

        Dimension labelSize;

        titleLabel.setFont(titleFont);
        labelSize = titleLabel.getPreferredSize();
        titleLabel.setBounds(360 - (labelSize.width/2), 100, labelSize.width, labelSize.height);

        titleLabel1.setFont(titleFont);
        labelSize = titleLabel1.getPreferredSize();
        titleLabel1.setBounds(360 - (labelSize.width/2), 150, labelSize.width, labelSize.height);

        messageLabel.setFont(textFont);
        labelSize = messageLabel.getPreferredSize();
        messageLabel.setBounds(360 - (labelSize.width/2),220, labelSize.width, labelSize.height);

        signInButton.setFont(inputFont);
        signInButton.setBounds(240, 400, 100, 30);
        signInButton.setFocusable(false);
        signInButton.addActionListener(e -> mainFrame.showPage("SignInPage"));

        signUpButton.setFont(inputFont);
        signUpButton.setBounds(380, 400, 100, 30);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(e -> mainFrame.showPage("SignUpPage"));

        add(titleLabel);
        add(titleLabel1);
        add(messageLabel);
        add(signInButton);
        add(signUpButton);
    }
}
