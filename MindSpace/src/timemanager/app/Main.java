package timemanager.app;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import timemanager.data.dao.DataImpl;
import timemanager.gui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Load accounts on startup
        DataImpl.loadAccounts();

        //Display accounts (test)
        System.out.println("accounts:");
//        DataImpl.getAccounts().forEach((username, account));

        //Save when shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(DataImpl::saveAccounts));
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(MainFrame::new);
    }
}