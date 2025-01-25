package org.example.data.dto.features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pomodoro extends JFrame implements ActionListener {
    //=========================//
    private JButton startButton;
    private JButton resetButton;
    private JLabel timeLabel;
    private JLabel titleLabel;
    //=========================//
    private int sessionTime;
    private int elapsedTime;
    private boolean isTimerRunning = false;
    private Timer timer;

    public Pomodoro() {
        boolean inputSuccessful = promptForSessionTime();
        if (!inputSuccessful) {
            this.dispose();
            return;
        }
        initializeFrame();
        initializeComponents();
        timer = new Timer(1000, e -> updateTimer());
    }
    private boolean promptForSessionTime() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Enter session time in minutes:", "Set Timer", JOptionPane.QUESTION_MESSAGE);
                if (input == null) {
                    return false;
                }
                sessionTime = Integer.parseInt(input) * 60 * 1000;
                elapsedTime = sessionTime;
                return true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void initializeFrame() {
        this.setSize(420, 420);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (timer != null) {
                    timer.stop();
                }
                setVisible(false);
            }
        });
        this.setVisible(true);
    }
    private void initializeComponents() {

        titleLabel = new JLabel("Pomodoro Timer");
        titleLabel.setBounds(120, 50, 250, 100);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
        this.add(titleLabel);
        //======================================================================//
        startButton = new JButton("Start");
        startButton.setBounds(100, 200, 100, 50);
        startButton.addActionListener(this);
        this.add(startButton);
        //=====================================================================//
        resetButton = new JButton("Reset");
        resetButton.setBounds(200, 200, 100, 50);
        resetButton.addActionListener(this);
        this.add(resetButton);
        //=====================================================================//
        timeLabel = new JLabel(formatTime(elapsedTime));
        timeLabel.setBounds(175, 100, 100, 100);
        this.add(timeLabel);
    }
    private void updateTimer() {
        if (elapsedTime > 0) {
            elapsedTime -= 1000;
            timeLabel.setText(formatTime(elapsedTime));
        } else {
            handleTimerEnd();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            toggleTimer();
        } else if (e.getSource() == resetButton) {
            resetTimer();
        }
    }
    private void toggleTimer() {
        if (!isTimerRunning) {
            startButton.setText("Stop");
            isTimerRunning = true;
            timer.start();
        } else {
            startButton.setText("Start");
            isTimerRunning = false;
            timer.stop();
        }
    }
    private void resetTimer() {
        timer.stop();
        timeLabel.setText(formatTime(sessionTime));
        elapsedTime = sessionTime;
        startButton.setText("Start");
        isTimerRunning = false;
    }
    private void handleTimerEnd() {
        timer.stop();
        isTimerRunning = false;
        startButton.setText("Start");

        int choice = JOptionPane.showConfirmDialog(null, "Nice Session", "Session End", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Keep Going!");
        } else {
            JOptionPane.showMessageDialog(null, "Don't Give Up! Good luck in the next session.");
        }

        resetTimer();
    }
    private String formatTime(int elapsedTime) {
        int hours = (elapsedTime / 3600000);
        int minutes = (elapsedTime / 60000) % 60;
        int seconds = (elapsedTime / 1000) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}