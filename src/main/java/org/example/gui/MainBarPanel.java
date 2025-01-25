package org.example.gui;

import org.example.bao.BAOFactory;
import org.example.data.dto.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainBarPanel extends JPanel {
    MainFrame mainFrame;

    ImageIcon sO = Images.settingsOIcon;
    ImageIcon sF = Images.settingsFIcon;
    ImageIcon eO = Images.exitOIcon;
    ImageIcon eF = Images.exitFIcon;

    private Color colorText = new Color(225, 0, 0);

    Dimension labelSize;

    JLabel setting = new JLabel(sO);
    JLabel exit = new JLabel(eO);
    JLabel titleLabel;

    public MainBarPanel(MainFrame mainFrame, BAOFactory baoFactory, Account account) {
        this.mainFrame = mainFrame;
        setLayout(null);

        Font titleFont = new Font(null, Font.BOLD, 36);

        titleLabel = new JLabel("Mind Space", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(colorText);
        labelSize = titleLabel.getPreferredSize();
        titleLabel.setBounds(360 - (labelSize.width/2), 25 - (labelSize.height/2), labelSize.width, labelSize.height);
        add(titleLabel);

        setting.setBounds(668,13, 36, 36);
        setting.setOpaque(true);
        if (ThemeManager.isDarkMode()) {
            setting.setBackground(mainFrame.blackBG);
        } else {
            setting.setBackground(mainFrame.whiteBG);
        }
        setting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setting.setIcon(sF);
                if (ThemeManager.isDarkMode()) {
                    setting.setBackground(mainFrame.bIconBG);
                } else {
                    setting.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setting.setIcon(sO);
                if (ThemeManager.isDarkMode()) {
                    setting.setBackground(mainFrame.blackBG);
                } else {
                    setting.setBackground(mainFrame.whiteBG);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                setting.setIcon(sO);
                if (ThemeManager.isDarkMode()) {
                    setting.setBackground(mainFrame.blackBG);
                } else {
                    setting.setBackground(mainFrame.whiteBG);
                }
                SettingsPanel settingsPanel = new SettingsPanel(mainFrame, baoFactory, account);
            }
        });
        add(setting);

        exit.setBounds(13,13, 36, 36);
        exit.setOpaque(true);
        if (ThemeManager.isDarkMode()) {
            exit.setBackground(mainFrame.blackBG);
        } else {
            exit.setBackground(mainFrame.whiteBG);
        }
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setIcon(eF);
                if (ThemeManager.isDarkMode()) {
                    exit.setBackground(mainFrame.bIconBG);
                } else {
                    exit.setBackground(mainFrame.wIconBG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setIcon(eO);
                if (ThemeManager.isDarkMode()) {
                    exit.setBackground(mainFrame.blackBG);
                } else {
                    exit.setBackground(mainFrame.whiteBG);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                exit.setIcon(eO);
                if (ThemeManager.isDarkMode()) {
                    exit.setBackground(mainFrame.blackBG);
                } else {
                    exit.setBackground(mainFrame.whiteBG);
                }
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure Logging Out?",
                    "Log Out",JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.OK_OPTION) {
                    mainFrame.showPage("WelcomePage");
                }
            }
        });
        add(exit);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setting.setIcon(sO);
        exit.setIcon(eO);

        if (ThemeManager.isDarkMode()) {
            setting.setBackground(mainFrame.blackBG);
            exit.setBackground(mainFrame.blackBG);
            g2d.setColor(mainFrame.blackBG);
            g2d.fillRect(0,0,720,62);
            g2d.setColor(mainFrame.blackL);
            g2d.fillRect(0,62,720,2);
        } else {
            setting.setBackground(mainFrame.whiteBG);
            exit.setBackground(mainFrame.whiteBG);
            g2d.setColor(mainFrame.whiteBG);
            g2d.fillRect(0,0,720,62);
            g2d.setColor(mainFrame.whiteL);
            g2d.fillRect(0,62,720,2);
        }
    }
}
