package timemanager.app;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import timemanager.data.dao.DataImpl;
import timemanager.data.dao.GitHubDownloader;
import timemanager.data.dao.GitHubUploader;
import timemanager.data.dto.Account;
import timemanager.gui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GitHubDownloader.download();
        //Load accounts on startup
        DataImpl.loadAccounts();

        //Display accounts (test)
        System.out.println("accounts:");
        DataImpl.getAccounts().forEach((email, account) -> {
            System.out.println("Name: " + account.getName());
            System.out.println("Email: " + account.getEmail());
            System.out.println("Password: " + account.getPassword());
            System.out.println("Projects: " + account.getProjects());
        });

        //Save when shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
           DataImpl.saveAccounts();
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