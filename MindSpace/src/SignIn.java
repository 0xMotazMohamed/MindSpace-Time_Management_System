import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn implements ActionListener{

    JFrame frame = new JFrame("Time Management");
    JButton signInButton = new JButton("Sign In");
    JButton resetButton = new JButton("Reset");
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel emailLabel = new JLabel("Email:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel titleLabel = new JLabel("Sign In");
    JLabel messageLabel = new JLabel("test");

    Font titleFont = new Font(null, Font.BOLD, 48);
    Font textFont = new Font(null, Font.PLAIN, 24);
    Font inputFont = new Font(null, Font.PLAIN, 16);
    Dimension labelSize;
    int x;
    int y;
//    JPanel panel = new JPanel(new GridBagLayout());
//    GridBagConstraints gbc = new GridBagConstraints();

    public void SignIn() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.setLayout(null);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.insets = new Insets(0,0,50,0);
//        panel.add(titleLabel, gbc);
//
//        gbc.gridy = 2;
//        gbc.insets = new Insets(0,0,0,0);
//        panel.add(messageLabel, gbc);
//
//        gbc.gridy = 1;
//        gbc.insets = new Insets(0,150,0,250);
//        panel.add(emailLabel, gbc);
//
//        gbc.gridx = 0;
//        gbc.insets = new Insets(0,0,0,0);
//        panel.add(emailField);

        titleLabel.setFont(titleFont);
        labelSize = titleLabel.getPreferredSize();
        x = (720-labelSize.width) /2;
        y = (720-labelSize.height) /2;
        titleLabel.setBounds(x,y-200,labelSize.width,labelSize.height);

        messageLabel.setFont(new Font(null, Font.BOLD,16));

        passwordLabel.setFont(textFont);
        labelSize = passwordLabel.getPreferredSize();
        x = (720-labelSize.width) /2;
        y = (720-labelSize.height) /2;
        passwordLabel.setBounds(x-140, y-30, labelSize.width, labelSize.height);

        emailLabel.setFont(textFont);
        labelSize = emailLabel.getPreferredSize();
        emailLabel.setBounds(x-140, y-100, labelSize.width, labelSize.height);

        emailField.setFont(inputFont);
        emailField.setBounds(x-10, y-98, 220, 32);
        passwordField.setFont(inputFont);
        passwordField.setBounds(x-10, y-28, 220, 32);

        signInButton.setFont(inputFont);
        labelSize = signInButton.getSize();
        x = (720-labelSize.width) /2;
        y = (720-labelSize.height) /2;
        signInButton.setBounds(x-150, y+30, 100, 30);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);

        resetButton.setFont(inputFont);
        resetButton.setBounds(x, y+30, 100, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);


        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(emailField);
        frame.add(passwordField);
        frame.add(titleLabel);
        frame.add(signInButton);
        frame.add(resetButton);
        //frame.add(messageLabel, BorderLayout.CENTER);
//        frame.add(label, BorderLayout.CENTER);
//        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            emailField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == signInButton) {
            String Email = emailField.getText().toLowerCase();
            String Password = String.valueOf(passwordField.getPassword());

            if (Data.getMap().containsKey(Email)) {
                if (Data.getMap().get(Email).equals(Password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Sign In successful");
                }else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong Password");
                }
            }else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Wrong Email");
            }
        }

    }


//        boolean logIn = false;
//        while (!logIn) {
//            String Email = JOptionPane.showInputDialog("Enter Email:");
//            if (Data.map.containsKey(Email.toLowerCase())) {
//                JOptionPane.showMessageDialog(null, "Right Email");
//                String Password = JOptionPane.showInputDialog("Enter Password");
//                if (0 == Password.compareTo(Data.map.get(Email.toLowerCase()))) {
//                    JOptionPane.showMessageDialog(null, "Right Password");
//                    logIn = true;
//                } else {
//                    JOptionPane.showMessageDialog(null, "Worng Password");
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Wrong Email");
//            }
//        }
    }
