package org.example.app;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import org.example.data.dao.AccountDAOImpl;
import org.example.data.dao.GitHubDownloader;
import org.example.data.dao.GitHubUploader;
import org.example.gui.MainFrame;

import javax.swing.*;

public class SimpleApp {
    public static void main(String[] args) {
        GitHubDownloader.download();
        //Load accounts on startup
        AccountDAOImpl.getInstance().loadAccounts();

        //Display accounts (test)
        System.out.println("accounts:");
        AccountDAOImpl.getInstance().getAccounts().forEach((email, account) -> {
            System.out.println("Name: " + account.getName());
            System.out.println("Email: " + account.getEmail());
            System.out.println("Password: " + account.getPassword());
            System.out.println("Projects: " + account.getProjects());
        });

        //Save when shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            AccountDAOImpl.getInstance().saveAccounts();
            GitHubUploader.upload();
        }));
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(MainFrame::new);
    }
}