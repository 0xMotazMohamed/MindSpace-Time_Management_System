package timemanager.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import timemanager.business.bao.BaoFactory;
import timemanager.business.bao.SignUp;

public class SignUpPanel extends JPanel {
    public SignUpPanel(MainFrame mainFrame) {
        SignUp SU = new BaoFactory().getSignUp();
        setLayout(null);

        JButton signUpButton = new JButton("Sign Up");
        JButton resetButton = new JButton("Reset");
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel usernameLabel = new JLabel("Username:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel titleLabel = new JLabel("Sign Up");
        JLabel errorLabel = new JLabel("");
        JLabel errorLabel2 = new JLabel("");
        JLabel signInLabel = new JLabel("<html><u>Have email Already? SignIn.</u></html>");

        Font titleFont = new Font(null, Font.BOLD, 48);
        Font textFont = new Font(null, Font.PLAIN, 24);
        Font inputFont = new Font(null, Font.PLAIN, 16);
        Font instructorFont = new Font(null, Font.BOLD, 12);
        Font errorFont = new Font(null, Font.BOLD, 12);
        Font linkFont = new Font(null, Font.PLAIN, 12);

        Dimension labelSize;
        int x;

        titleLabel.setFont(titleFont);
        labelSize = titleLabel.getPreferredSize();
        titleLabel.setBounds(360 - (labelSize.width/2), 100, labelSize.width, labelSize.height);

        usernameLabel.setFont(textFont);
        labelSize = usernameLabel.getPreferredSize();
        x = 360 - (labelSize.width + 20 + 220) / 2;
        usernameLabel.setBounds(x, 200, labelSize.width, labelSize.height);

        passwordLabel.setFont(textFont);
        labelSize = passwordLabel.getPreferredSize();
        passwordLabel.setBounds(x, 340, labelSize.width, labelSize.height);

        emailLabel.setFont(textFont);
        labelSize = emailLabel.getPreferredSize();
        emailLabel.setBounds(x, 270, labelSize.width, labelSize.height);


        usernameField.setFont(instructorFont);
        usernameField.setBackground(Color.LIGHT_GRAY);
        usernameField.setText("Username");
        usernameField.setForeground(Color.GRAY);
        usernameField.setBounds(x + (usernameLabel.getWidth() + 20), 202, 220, 32);
        usernameField.setFocusable(false);
        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (usernameField.getForeground().equals(Color.GRAY)) {
                    usernameField.setFont(inputFont);
                    usernameField.setText("");
                    usernameField.setBackground(Color.white);
                    usernameField.setForeground(null);
                    usernameField.setFocusable(true);
                    usernameField.grabFocus();
                }
                if (emailField.getText().isEmpty()) {
                    emailField.setFont(instructorFont);
                    emailField.setBackground(Color.LIGHT_GRAY);
                    emailField.setText("example@gmail.com");
                    emailField.setForeground(Color.GRAY);
                    emailField.setFocusable(false);
                }
                if (passwordField.getText().isEmpty()) {
                    passwordField.setFont(instructorFont);
                    passwordField.setBackground(Color.LIGHT_GRAY);
                    passwordField.setText("123456789");
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setFocusable(false);
                }
            }
        });

        emailField.setFont(instructorFont);
        emailField.setBackground(Color.LIGHT_GRAY);
        emailField.setText("example@gmail.com");
        emailField.setForeground(Color.GRAY);
        emailField.setBounds(usernameField.getX(), 272, 220, 32);
        emailField.setFocusable(false);
        emailField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (emailField.getForeground().equals(Color.GRAY)) {
                    emailField.setFont(inputFont);
                    emailField.setText("");
                    emailField.setBackground(Color.white);
                    emailField.setForeground(null);
                    emailField.setFocusable(true);
                    emailField.grabFocus();
                }
                if (usernameField.getText().isEmpty()) {
                    usernameField.setFont(instructorFont);
                    usernameField.setBackground(Color.LIGHT_GRAY);
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.GRAY);
                    usernameField.setFocusable(false);
                }
                if (passwordField.getText().isEmpty()) {
                    passwordField.setFont(instructorFont);
                    passwordField.setBackground(Color.LIGHT_GRAY);
                    passwordField.setText("123456789");
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setFocusable(false);
                }
            }
        });


        passwordField.setFont(instructorFont);
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setText("123456789");
        passwordField.setForeground(Color.GRAY);
        passwordField.setBounds(usernameField.getX(), 342, 220, 32);
        passwordField.setFocusable(false);
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passwordField.getForeground().equals(Color.GRAY)) {
                    passwordField.setFont(inputFont);
                    passwordField.setText("");
                    passwordField.setBackground(Color.white);
                    passwordField.setForeground(null);
                    passwordField.setFocusable(true);
                    passwordField.grabFocus();
                }
                if (usernameField.getText().isEmpty()) {
                    usernameField.setFont(instructorFont);
                    usernameField.setBackground(Color.LIGHT_GRAY);
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.GRAY);
                    usernameField.setFocusable(false);
                }
                if (emailField.getText().isEmpty()) {
                    emailField.setFont(instructorFont);
                    emailField.setBackground(Color.LIGHT_GRAY);
                    emailField.setText("example@gmail.com");
                    emailField.setForeground(Color.GRAY);
                    emailField.setFocusable(false);
                }
            }
        });

        errorLabel.setFont(errorFont);
        errorLabel.setBounds(usernameField.getX(), 220, 400, 50);
        errorLabel.setForeground(Color.red);

        errorLabel2.setFont(errorFont);
        errorLabel2.setBounds(usernameField.getX(), 220, 400, 50);
        errorLabel2.setForeground(Color.red);
        
        signUpButton.setFont(inputFont);
        signUpButton.setBounds(240, 410, 100, 30);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(e -> {
            if (e.getSource() == signUpButton) {
                String Username = "";
                String Email = "";
                String Password = "";
                if (!usernameField.getForeground().equals(Color.GRAY))
                    Username = usernameField.getText();
                if (!emailField.getForeground().equals(Color.GRAY))
                    Email = emailField.getText().toLowerCase();
                if (!passwordField.getForeground().equals(Color.GRAY))
                    Password = String.valueOf(passwordField.getPassword());

                switch (SU.SignUp(Username ,Email, Password)) {
                    case 10:
                        errorLabel.setLocation(errorLabel.getX(), 220);
                        errorLabel.setText("* Username must not be Empty");
                        errorLabel2.setText("");
                        break;
                    case 20:
                        errorLabel.setLocation(errorLabel.getX(), 290);
                        errorLabel.setText("* Email must not be Empty");
                        errorLabel2.setText("");
                        break;
                    case 30:
                        errorLabel.setLocation(errorLabel.getX(), 360);
                        errorLabel.setText("* Password must not be Empty");
                        errorLabel2.setText("");
                        break;
                    case 9:
                        errorLabel.setLocation(errorLabel.getX(), 220);
                        errorLabel.setText("* Username can only contain alphanumeric characters.");
                        errorLabel2.setLocation(errorLabel.getX() + 8, 235);
                        errorLabel2.setText("Username must be between 5 and 15 characters long.");
                        break;
                    case 19:
                        errorLabel.setLocation(errorLabel.getX(), 290);
                        errorLabel.setText("* Email must contain '@'.");
                        errorLabel2.setLocation(errorLabel.getX() + 8, 305);
                        errorLabel2.setText("Email must end with '.com'.");
                        break;
                    case 29:
                        errorLabel.setLocation(errorLabel.getX(), 360);
                        errorLabel.setText("* Password must be at least 8 characters long.");
                        errorLabel2.setLocation(errorLabel.getX() + 8, 375);
                        errorLabel2.setText("Password must contain at least one uppercase letter and one digit.");
                        break;
                    default:
                        errorLabel.setText("");
                        errorLabel2.setText("");
                        mainFrame.showPage("MainPage");
                }
            }
        });


        resetButton.setFont(inputFont);
        resetButton.setBounds(380, 410, 100, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> {
            if (e.getSource() == resetButton) {
                usernameField.setFont(instructorFont);
                usernameField.setBackground(Color.LIGHT_GRAY);
                usernameField.setText("Username");
                usernameField.setForeground(Color.GRAY);
                usernameField.setFocusable(false);
                emailField.setFont(instructorFont);
                emailField.setBackground(Color.LIGHT_GRAY);
                emailField.setText("example@gmail.com");
                emailField.setForeground(Color.GRAY);
                emailField.setFocusable(false);
                passwordField.setFont(instructorFont);
                passwordField.setBackground(Color.LIGHT_GRAY);
                passwordField.setText("123456789");
                passwordField.setForeground(Color.GRAY);
                passwordField.setFocusable(false);
                errorLabel.setText("");
                errorLabel2.setText("");
            }
        });

        signInLabel.setFont(linkFont);
        signInLabel.setForeground(new Color(0,107,255));
        signInLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showPage("SignInPage");
                usernameField.setFont(instructorFont);
                usernameField.setBackground(Color.LIGHT_GRAY);
                usernameField.setText("Username");
                usernameField.setForeground(Color.GRAY);
                usernameField.setFocusable(false);
                emailField.setFont(instructorFont);
                emailField.setBackground(Color.LIGHT_GRAY);
                emailField.setText("example@gmail.com");
                emailField.setForeground(Color.GRAY);
                emailField.setFocusable(false);
                passwordField.setFont(instructorFont);
                passwordField.setBackground(Color.LIGHT_GRAY);
                passwordField.setText("123456789");
                passwordField.setForeground(Color.GRAY);
                passwordField.setFocusable(false);
                errorLabel.setText("");
                errorLabel2.setText("");
            }
        });
        labelSize = signInLabel.getPreferredSize();
        signInLabel.setBounds(360 - (labelSize.width /2), 450, labelSize.width, labelSize.height);

        add(usernameLabel);
        add(emailLabel);
        add(passwordLabel);
        add(usernameField);
        add(emailField);
        add(passwordField);
        add(titleLabel);
        add(resetButton);
        add(signUpButton);
        add(errorLabel);
        add(errorLabel2);
        add(signInLabel);
    }
}
