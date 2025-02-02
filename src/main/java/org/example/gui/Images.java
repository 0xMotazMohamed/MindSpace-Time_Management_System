package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

// handle as home filled

public class Images {
    static ImageIcon homeFIcon;
    static ImageIcon homeOIcon;
    static ImageIcon alarmFIcon;
    static ImageIcon alarmOIcon;
    static ImageIcon calenderFIcon;
    static ImageIcon calenderOIcon;
    static ImageIcon sunFIcon;
    static ImageIcon sunOIcon;
    static ImageIcon moonFIcon;
    static ImageIcon moonOIcon;
    static ImageIcon folderAddFIcon;
    static ImageIcon folderAddOIcon;
    static ImageIcon folderFIcon;
    static ImageIcon folderOIcon;
    static ImageIcon fileFIcon;
    static ImageIcon fileOIcon;
    static ImageIcon pencilFIcon;
    static ImageIcon pencilOIcon;
    static ImageIcon settingsFIcon;
    static ImageIcon settingsOIcon;
    static ImageIcon exitFIcon;
    static ImageIcon exitOIcon;
    static ImageIcon deleteFIcon;
    static ImageIcon deleteOIcon;
    static ImageIcon highIcon;
    static ImageIcon mediumIcon;
    static ImageIcon lowIcon;
    static Image programImage;

    static {
        try {
            InputStream homeF = Images.class.getClassLoader().getResourceAsStream("icons/home_filled.png");
            InputStream homeO = Images.class.getClassLoader().getResourceAsStream("icons/home_outline.png");
            InputStream alarmF = Images.class.getClassLoader().getResourceAsStream("icons/alarm_filled.png");
            InputStream alarmO = Images.class.getClassLoader().getResourceAsStream("icons/alarm_outline.png");
            InputStream calenderF = Images.class.getClassLoader().getResourceAsStream("icons/calendar_filled.png");
            InputStream calenderO = Images.class.getClassLoader().getResourceAsStream("icons/calendar_outline.png");
            InputStream sunF = Images.class.getClassLoader().getResourceAsStream("icons/sun_filled.png");
            InputStream sunO = Images.class.getClassLoader().getResourceAsStream("icons/sun_outline.png");
            InputStream moonF = Images.class.getClassLoader().getResourceAsStream("icons/moon_filled.png");
            InputStream moonO = Images.class.getClassLoader().getResourceAsStream("icons/moon_outline.png");
            InputStream folderAddF = Images.class.getClassLoader().getResourceAsStream("icons/folderAdd_filled.png");
            InputStream folderAddO = Images.class.getClassLoader().getResourceAsStream("icons/folderAdd_outline.png");
            InputStream folderF = Images.class.getClassLoader().getResourceAsStream("icons/folder_filled.png");
            InputStream folderO = Images.class.getClassLoader().getResourceAsStream("icons/folder_outline.png");
            InputStream fileF = Images.class.getClassLoader().getResourceAsStream("icons/file_filled.png");
            InputStream fileO = Images.class.getClassLoader().getResourceAsStream("icons/file_outline.png");
            InputStream pencilF = Images.class.getClassLoader().getResourceAsStream("icons/pencil_filled.png");
            InputStream pencilO = Images.class.getClassLoader().getResourceAsStream("icons/pencil_outline.png");
            InputStream settingsF = Images.class.getClassLoader().getResourceAsStream("icons/settings_filled.png");
            InputStream settingsO = Images.class.getClassLoader().getResourceAsStream("icons/settings_outline.png");
            InputStream exitF = Images.class.getClassLoader().getResourceAsStream("icons/exit_filled.png");
            InputStream exitO = Images.class.getClassLoader().getResourceAsStream("icons/exit_outline.png");
            InputStream deleteF = Images.class.getClassLoader().getResourceAsStream("icons/delete_filled.png");
            InputStream deleteO = Images.class.getClassLoader().getResourceAsStream("icons/delete_outline.png");
            InputStream high = Images.class.getClassLoader().getResourceAsStream("icons/high.png");
            InputStream medium = Images.class.getClassLoader().getResourceAsStream("icons/medium.png");
            InputStream low = Images.class.getClassLoader().getResourceAsStream("icons/low.png");

            InputStream icon = Images.class.getClassLoader().getResourceAsStream("icons/MindSpace.png");
            programImage = new ImageIcon(icon.readAllBytes()).getImage();

            homeFIcon = new ImageIcon(new ImageIcon(homeF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            homeOIcon = new ImageIcon(new ImageIcon(homeO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            alarmFIcon = new ImageIcon(new ImageIcon(alarmF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            alarmOIcon = new ImageIcon(new ImageIcon(alarmO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            calenderFIcon = new ImageIcon(new ImageIcon(calenderF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            calenderOIcon = new ImageIcon(new ImageIcon(calenderO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            sunFIcon = new ImageIcon(new ImageIcon(sunF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            sunOIcon = new ImageIcon(new ImageIcon(sunO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            moonFIcon = new ImageIcon(new ImageIcon(moonF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            moonOIcon = new ImageIcon(new ImageIcon(moonO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            folderAddFIcon = new ImageIcon(new ImageIcon(folderAddF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            folderAddOIcon = new ImageIcon(new ImageIcon(folderAddO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            folderFIcon = new ImageIcon(new ImageIcon(folderF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            folderOIcon = new ImageIcon(new ImageIcon(folderO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            fileFIcon = new ImageIcon(new ImageIcon(fileF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            fileOIcon = new ImageIcon(new ImageIcon(fileO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            pencilFIcon = new ImageIcon(new ImageIcon(pencilF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            pencilOIcon = new ImageIcon(new ImageIcon(pencilO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            settingsFIcon = new ImageIcon(new ImageIcon(settingsF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            settingsOIcon = new ImageIcon(new ImageIcon(settingsO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            exitFIcon = new ImageIcon(new ImageIcon(exitF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            exitOIcon = new ImageIcon(new ImageIcon(exitO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            deleteFIcon = new ImageIcon(new ImageIcon(deleteF.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            deleteOIcon = new ImageIcon(new ImageIcon(deleteO.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            highIcon = new ImageIcon(new ImageIcon(high.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            mediumIcon = new ImageIcon(new ImageIcon(medium.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
            lowIcon = new ImageIcon(new ImageIcon(low.readAllBytes()).getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load icons");
        }
    }
}
