package GUI;

import javax.swing.*;
import java.awt.*;

import StartUp.SignIn;

public class SignInPanel extends JPanel {
    public SignInPanel() {
        setLayout(null);

        JFrame frame = new JFrame("Time Management");
        JButton signInButton = new JButton("Sign In");
        JButton resetButton = new JButton("Reset");
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel titleLabel = new JLabel("Sign In");
        JLabel errorLabel = new JLabel("");

        Font titleFont = new Font(null, Font.BOLD, 48);
        Font textFont = new Font(null, Font.PLAIN, 24);
        Font inputFont = new Font(null, Font.PLAIN, 16);
        Font errorFont = new Font(null, Font.BOLD, 12);
        Dimension labelSize;
        int x;
        int y;

        titleLabel.setFont(titleFont);
        labelSize = titleLabel.getPreferredSize();
        x = (720 - labelSize.width) / 2;
        y = (720 - labelSize.height) / 2;
        titleLabel.setBounds(x, y - 200, labelSize.width, labelSize.height);

        errorLabel.setFont(new Font(null, Font.BOLD, 16));

        passwordLabel.setFont(textFont);
        labelSize = passwordLabel.getPreferredSize();
        x = (720 - labelSize.width) / 2;
        y = (720 - labelSize.height) / 2;
        passwordLabel.setBounds(x - 140, y - 30, labelSize.width, labelSize.height);

        emailLabel.setFont(textFont);
        labelSize = emailLabel.getPreferredSize();
        emailLabel.setBounds(x - 140, y - 100, labelSize.width, labelSize.height);

        emailField.setFont(inputFont);
        emailField.setBounds(x - 10, y - 98, 220, 32);
        passwordField.setFont(inputFont);
        passwordField.setBounds(x - 10, y - 28, 220, 32);

        errorLabel.setFont(errorFont);
        errorLabel.setBounds(x - 8, y, 300, 50);
        errorLabel.setForeground(Color.red);

        signInButton.setFont(inputFont);
        labelSize = signInButton.getSize();
        x = (720 - labelSize.width) / 2;
        y = (720 - labelSize.height) / 2;
        signInButton.setBounds(x - 150, y + 30, 100, 30);
        signInButton.setFocusable(false);
        signInButton.addActionListener(e -> {
            if (e.getSource() == signInButton) {
                String Email = emailField.getText().toLowerCase();
                String Password = String.valueOf(passwordField.getPassword());

                switch (SignIn.logIn(Email, Password)) {
                    case 10:
                        errorLabel.setText("* Email must not be Empty");
                        errorLabel.setLocation(errorLabel.getX(), 270);
                        break;
                    case 20:
                        errorLabel.setText("* Password must not be Empty");
                        errorLabel.setLocation(errorLabel.getX(), 340);
                        break;
                    case 9:
                        errorLabel.setText("* Wrong Email");
                        errorLabel.setLocation(errorLabel.getX(), 270);
                        break;
                    case 19:
                        errorLabel.setText("* Wrong Password");
                        errorLabel.setLocation(errorLabel.getX(), 340);
                        break;
                    default:
                        errorLabel.setText("");
                        System.out.println("Well Done");
                }
            }
        });


        resetButton.setFont(inputFont);
        resetButton.setBounds(x, y + 30, 100, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> {
            if (e.getSource() == resetButton) {
                emailField.setText("");
                passwordField.setText("");
            }
        });

        add(emailLabel);
        add(passwordLabel);
        add(emailField);
        add(passwordField);
        add(titleLabel);
        add(signInButton);
        add(resetButton);
        add(errorLabel);

        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }
}
