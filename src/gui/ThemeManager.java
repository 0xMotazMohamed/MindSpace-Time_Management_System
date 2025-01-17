package org.example.gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import org.example.gui.MainFrame;

import javax.swing.*;

public class ThemeManager {
    private static boolean isDarkMode = false;

    public static void toggleTheme(MainFrame mainFrame) {
        try {
            if (isDarkMode) {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
                isDarkMode = false;
            } else {
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
                isDarkMode = true;
            }
            SwingUtilities.updateComponentTreeUI(mainFrame);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static String mode() {
        if (isDarkMode()) {
            return "Switch to Light Mode";
        } else
            return "Switch to Dark Mode";
    }
}
