package org.example.gui;

import org.example.bao.BAOFactory;
import org.example.data.dao.AccountDAOImpl;
import org.example.data.dao.GitHubDownloader;
import org.example.data.dao.GitHubUploader;
import org.example.data.dto.Account;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel {
    SettingsPanel(MainFrame mainFrame, BAOFactory baoFactory, Account account) {
        confirmUser(mainFrame, baoFactory ,account);
    }

    private void settings(MainFrame mainFrame, BAOFactory baoFactory,Account account) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        JTextField emailField = new JTextField(account.getEmail(),15);
        emailField.setFocusable(false);
        emailField.setBackground(Color.LIGHT_GRAY);
        emailField.setForeground(Color.GRAY);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(account.getName(),15);
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(account.getPassword(),15);
        panel.add(passwordField, gbc);

        Object[] options = {"Confirm Changes", "Delete Account","Cancel"};
        int response = JOptionPane.showOptionDialog(null,
                panel, "Edit Account", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (response == 0) {
            String newUsername = usernameField.getText();
            if (newUsername.isEmpty()) {
                int wrong = JOptionPane.showConfirmDialog(null,
                        "Enter UserName", "Wrong Input",
                        JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                if (wrong == JOptionPane.OK_OPTION) {
                    settings(mainFrame, baoFactory, account);
                }
            }
            String newPassword = String.valueOf(passwordField.getPassword());
            if (newPassword.isEmpty()) {
                int wrong = JOptionPane.showConfirmDialog(null,
                        "Enter Password", "Wrong Input",
                        JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                if (wrong == JOptionPane.OK_OPTION) {
                    settings(mainFrame, baoFactory, account);
                }
            }
            account.setName(newUsername);
            account.setPassword(newPassword);
            System.out.println(account.getName());
            System.out.println(account.getPassword());
            baoFactory.getDaoFactory().getAccountDAO().updateData();
        } else if (response == 1) {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure deleting the account?",
                    "Deleting Account",JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) {
                baoFactory.getDaoFactory().getAccountDAO().deleteAccount(emailField.getText());
                mainFrame.showPage("WelcomePage");
                baoFactory.getDaoFactory().getAccountDAO().updateData();
            }
        }
    }

    private void confirmUser(MainFrame mainFrame, BAOFactory baoFactory,Account account) {
        JPasswordField passwordField = new JPasswordField();
        int confirm = JOptionPane.showConfirmDialog(null,
                new Object[]{"Current Password", passwordField},
                "Enter Current Password", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (confirm == JOptionPane.OK_OPTION) {
            if (String.valueOf(passwordField.getPassword()).equals(account.getPassword())) {
                settings(mainFrame, baoFactory, account);
            } else {
                int wrong = JOptionPane.showConfirmDialog(null,
                        "Wrong, Try Again!","Wrong Password",
                        JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                if (wrong == JOptionPane.OK_OPTION) {
                    confirmUser(mainFrame, baoFactory ,account);
                }
            }
        }
    }
}
