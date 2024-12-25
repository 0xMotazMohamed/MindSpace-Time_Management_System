package timemanager.gui;

import javax.swing.*;
import java.awt.*;

public class Images {
            static ImageIcon homeF = new ImageIcon("MindSpace/res/icons/home_filled.png");
            static Image scaledHomeF = homeF.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
            static ImageIcon homeFIcon = new ImageIcon(scaledHomeF);

            static ImageIcon homeO = new ImageIcon("MindSpace/res/icons/home_outline2.png");
            static Image scaledHomeO = homeO.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
            static ImageIcon homeOIcon = new ImageIcon(scaledHomeO);

            static ImageIcon folderF = new ImageIcon("MindSpace/res/icons/folder_filled2.png");
            static Image scaledFolderF = folderF.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
            static ImageIcon folderFIcon = new ImageIcon(scaledFolderF);

            static ImageIcon folderO = new ImageIcon("MindSpace/res/icons/folder_outline2.png");
            static Image scaledFolderO = folderO.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
            static ImageIcon folderOIcon = new ImageIcon(scaledFolderO);

            static ImageIcon settingsF = new ImageIcon("MindSpace/res/icons/settings_filled.png");
            static Image scaledSettingsF = settingsF.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
            static ImageIcon settingsFIcon = new ImageIcon(scaledSettingsF);

            static ImageIcon settingsO = new ImageIcon("MindSpace/res/icons/settings_outline.png");
            static Image scaledSettingsO = settingsO.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
            static ImageIcon settingsOIcon = new ImageIcon(scaledSettingsO);
}
