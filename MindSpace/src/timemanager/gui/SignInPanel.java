package timemanager.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import timemanager.business.bao.*;

public class SignInPanel extends JPanel {
    SignIn SI = new BaoFactory().getSignIn();
    public SignInPanel(MainFrame mainFrame) {
        setLayout(null);
        JButton signInButton = new JButton("Sign In");
        JButton resetButton = new JButton("Reset");
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel titleLabel = new JLabel("Sign In");
        JLabel errorLabel = new JLabel("");
        JLabel signUpLabel = new JLabel("<html><u>Don't have email yet? Create new one.</u></html>");

        Font titleFont = new Font(null, Font.BOLD, 48);
        Font textFont = new Font(null, Font.PLAIN, 24);
        Font inputFont = new Font(null, Font.PLAIN, 16);
        Font errorFont = new Font(null, Font.BOLD, 12);
        Font linkFont = new Font(null, Font.PLAIN, 12);

        Dimension labelSize;
        int x;

        titleLabel.setFont(titleFont);
        labelSize = titleLabel.getPreferredSize();
        titleLabel.setBounds(360 - (labelSize.width/2), 150, labelSize.width, labelSize.height);

        passwordLabel.setFont(textFont);
        labelSize = passwordLabel.getPreferredSize();
        x = 360 - ((labelSize.width + 20 + 220) / 2);
        passwordLabel.setBounds(x , 320, labelSize.width, labelSize.height);

        emailLabel.setFont(textFont);
        labelSize = emailLabel.getPreferredSize();
        emailLabel.setBounds(x, 250, labelSize.width, labelSize.height);

        passwordField.setFont(inputFont);
        passwordField.setBounds(x + (passwordLabel.getWidth() + 20), 322, 220, 32);

        emailField.setFont(inputFont);
        emailField.setBounds(passwordField.getX(), 252, 220, 32);

        errorLabel.setFont(errorFont);
        errorLabel.setBounds(passwordField.getX(), 270, 220, 50);
        errorLabel.setForeground(Color.red);

        signInButton.setFont(inputFont);
        signInButton.setBounds(240, 390, 100, 30);
        signInButton.setFocusable(false);
        signInButton.addActionListener(e -> {
            if (e.getSource() == signInButton) {
                String Email = emailField.getText().toLowerCase();
                String Password = String.valueOf(passwordField.getPassword());

                switch (SI.logIn(Email, Password)) {
                    case 10:
                        errorLabel.setLocation(errorLabel.getX(), 270);
                        errorLabel.setText("* Email must not be Empty");
                        break;
                    case 20:
                        errorLabel.setText("* Password must not be Empty");
                        errorLabel.setLocation(errorLabel.getX(), 340);
                        break;
                    case 9:
                        errorLabel.setLocation(errorLabel.getX(), 270);
                        errorLabel.setText("* Wrong Email");
                        break;
                    case 19:
                        errorLabel.setLocation(errorLabel.getX(), 340);
                        errorLabel.setText("* Wrong Password");
                        break;
                    default:
                        errorLabel.setText("");
                        mainFrame.showPage("MainPage");
                }
            }
        });


        resetButton.setFont(inputFont);
        resetButton.setBounds(380, 390, 100, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> {
            if (e.getSource() == resetButton) {
                emailField.setText("");
                passwordField.setText("");
                errorLabel.setText("");
            }
        });

        signUpLabel.setFont(linkFont);
        signUpLabel.setForeground(new Color(0,107,255));
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showPage("SignUpPage");
                emailField.setText("");
                passwordField.setText("");
                errorLabel.setText("");
            }
        });
        labelSize = signUpLabel.getPreferredSize();
        signUpLabel.setBounds(360 - (labelSize.width/ 2) , 430, labelSize.width, labelSize.height);

        add(emailLabel);
        add(passwordLabel);
        add(emailField);
        add(passwordField);
        add(titleLabel);
        add(signInButton);
        add(resetButton);
        add(errorLabel);
        add(signUpLabel);
    }
}
