import javax.swing.*;
import java.awt.*;

public class SignIn {

    JFrame frame = new JFrame();
    JButton signInButton = new JButton("Sign In");
    JButton resetButton = new JButton("Reset");
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel emailLabel = new JLabel("Email:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel titleLabel = new JLabel("Sign In");

    public void SignIn() {

        emailLabel.setBounds(50,150,75,25);
        passwordLabel.setBounds(50,200,75,25);

        emailField.setBounds(125,150,200,25);
        passwordField.setBounds(125,200,200,25);

        titleLabel.setBounds(160,40,120,50);
        titleLabel.setFont(new Font(null,Font.BOLD,36));

        signInButton.setBounds(100,250,100,30);
        resetButton.setBounds(220,250,100,30);

        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(emailField);
        frame.add(passwordField);
        frame.add(titleLabel);
        frame.add(signInButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480,480);
        frame.setLayout(null);
        frame.setVisible(true);

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

}
