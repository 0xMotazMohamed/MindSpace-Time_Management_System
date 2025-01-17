package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class Images {
    static ImageIcon homeF = new ImageIcon("MindSpace/res/icons/home_filled.png");
    static Image scaledHomeF = homeF.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon homeFIcon = new ImageIcon(scaledHomeF);

    static ImageIcon homeO = new ImageIcon("MindSpace/res/icons/home_outline.png");
    static Image scaledHomeO = homeO.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon homeOIcon = new ImageIcon(scaledHomeO);

    static ImageIcon alarmF = new ImageIcon("MindSpace/res/icons/alarm_filled.png");
    static Image scaledAlarmF = alarmF.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon alarmFIcon = new ImageIcon(scaledAlarmF);

    static ImageIcon alarmO = new ImageIcon("MindSpace/res/icons/alarm_outline.png");
    static Image scaledAlarmO = alarmO.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon alarmOIcon = new ImageIcon(scaledAlarmO);

    static ImageIcon folderF = new ImageIcon("MindSpace/res/icons/folder_filled.png");
    static Image scaledFolderF = folderF.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon folderFIcon = new ImageIcon(scaledFolderF);

    static ImageIcon folderO = new ImageIcon("MindSpace/res/icons/folder_outline.png");
    static Image scaledFolderO = folderO.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon folderOIcon = new ImageIcon(scaledFolderO);

    static ImageIcon settingsF = new ImageIcon("MindSpace/res/icons/settings_filled.png");
    static Image scaledSettingsF = settingsF.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon settingsFIcon = new ImageIcon(scaledSettingsF);

    static ImageIcon settingsO = new ImageIcon("MindSpace/res/icons/settings_outline.png");
    static Image scaledSettingsO = settingsO.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
    static ImageIcon settingsOIcon = new ImageIcon(scaledSettingsO);
}
