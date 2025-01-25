package org.example.gui;

import org.example.bao.BAOFactory;
import org.example.bao.SignIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignInPanel extends JPanel {
    MainFrame mainFrame;
    BAOFactory baoFactory;
    SignIn SI;

    JTextField emailField;
    JPasswordField passwordField;
    JButton passwordButton;
    JLabel errorLabel;

    public SignInPanel(MainFrame mainFrame, BAOFactory baoFactory) {
        this.mainFrame = mainFrame;
        this.baoFactory = baoFactory;
        SI = baoFactory.getSignIn();
        setLayout(null);

        JButton signInButton = new JButton("Sign In");
        JButton resetButton = new JButton("Reset");
        emailField = new JTextField();
        passwordField = new JPasswordField();
        passwordButton = new JButton("⚪");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel titleLabel = new JLabel("Sign In");
        errorLabel = new JLabel("");
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

        passwordButton.setBounds(passwordField.getX() + 220, 322, 32,32);
        passwordButton.setFont(textFont);
        passwordButton.setFocusable(false);
        passwordButton.setBorderPainted(false);
        passwordButton.setContentAreaFilled(false);
        passwordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        passwordButton.addActionListener(new ActionListener() {
            private boolean isPasswordVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    passwordButton.setText("⚫");
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordButton.setText("⚪");
                    passwordField.setEchoChar('\u2022');
                }
            }
        });
        add(passwordButton);

        emailField.setFont(inputFont);
        emailField.setBounds(passwordField.getX(), 252, 220, 32);

        errorLabel.setFont(errorFont);
        errorLabel.setBounds(passwordField.getX(), 270, 220, 50);
        errorLabel.setForeground(Color.red);

        signInButton.setFont(inputFont);
        signInButton.setBounds(240, 390, 100, 30);
        signInButton.setFocusable(true);
        signInButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    Entering();
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entering();
            }
        });


        resetButton.setFont(inputFont);
        resetButton.setBounds(380, 390, 100, 30);
        resetButton.setFocusable(true);
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
                emailField.setText("");
                passwordField.setText("");
                errorLabel.setText("");
                mainFrame.showPage("SignUpPage");
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

    private void Entering() {
        String Email = emailField.getText().toLowerCase();
        String Password = String.valueOf(passwordField.getPassword());

        switch (SI.logIn(Email, Password)) {
            case 10:
                errorLabel.setLocation(errorLabel.getX(), 270);
                errorLabel.setText("* Email must not be Empty");
                break;
            case 20:
                errorLabel.setLocation(errorLabel.getX(), 340);
                errorLabel.setText("* Password must not be Empty");
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
                emailField.setText("");
                passwordField.setText("");
                passwordField.setEchoChar('\u2022');
                passwordButton.setText("⚪");
                errorLabel.setText("");
                mainFrame.setAccount(baoFactory.getDaoFactory().getAccountDAO().getAccountByEmail(Email));
                mainFrame.showPage("MainPage");
        }
    }
}
