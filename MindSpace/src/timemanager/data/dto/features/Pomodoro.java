package timemanager.data.dto.features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pomodoro extends JFrame implements ActionListener {

    // GUI components
    private JButton startButton;
    private JButton resetButton;
    private JLabel timeLabel;
    private JLabel titleLabel;

    // Timer settings
    private int sessionTime; // Session time in milliseconds
    private int elapsedTime;

    // Timer format variables
    private boolean isTimerRunning = false;

    // Swing Timer to handle countdown
    private final Timer timer;

    public Pomodoro() {
        // Prompt user for session time
        promptForSessionTime();

        // Initialize the frame
        initializeFrame();

        // Initialize GUI components
        initializeComponents();

        // Initialize the timer
        timer = new Timer(1000, e -> updateTimer());
    }

    // Prompt the user to input session time
    private void promptForSessionTime() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Enter session time in minutes:", "Set Timer", JOptionPane.QUESTION_MESSAGE);
                if (input == null) {
                    System.exit(0); // Exit if the user cancels
                }
                sessionTime = Integer.parseInt(input) * 60 * 1000; // Convert minutes to milliseconds
                elapsedTime = sessionTime; // Initialize elapsed time
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Initialize JFrame properties
    private void initializeFrame() {
        this.setSize(420, 420);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Initialize and add components to the frame
    private void initializeComponents() {
        // Title Label
        titleLabel = new JLabel("Pomodoro Timer");
        titleLabel.setBounds(120, 50, 250, 100);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
        this.add(titleLabel);

        // Start Button
        startButton = new JButton("Start");
        startButton.setBounds(100, 200, 100, 50);
        startButton.addActionListener(this);
        this.add(startButton);

        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.setBounds(200, 200, 100, 50);
        resetButton.addActionListener(this);
        this.add(resetButton);

        // Timer Label
        timeLabel = new JLabel(formatTime(elapsedTime));
        timeLabel.setBounds(175, 100, 100, 100);
        this.add(timeLabel);
    }

    // Update the timer each second
    private void updateTimer() {
        if (elapsedTime > 0) {
            elapsedTime -= 1000; // Decrease elapsed time by 1 second
            timeLabel.setText(formatTime(elapsedTime));
        } else {
            handleTimerEnd();
        }
    }

    // Handle button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            toggleTimer();
        } else if (e.getSource() == resetButton) {
            resetTimer();
        }
    }

    // Start or stop the timer based on its current state
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

    // Reset the timer to its initial state
    private void resetTimer() {
        timer.stop();
        promptForSessionTime(); // Ask for new session time when resetting
        timeLabel.setText(formatTime(elapsedTime));
        startButton.setText("Start");
        isTimerRunning = false;
    }

    // Handle the end of the timer
    private void handleTimerEnd() {
        timer.stop();
        isTimerRunning = false;
        startButton.setText("Start");

        // Show session end dialog
        int choice = JOptionPane.showConfirmDialog(null, "Nice Session", "Session End", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Keep Going!");
        } else {
            JOptionPane.showMessageDialog(null, "Don't Give Up! Good luck in the next session.");
        }

        resetTimer(); // Reset timer after session ends
    }

    // Format the elapsed time as HH:MM:SS
    private String formatTime(int elapsedTime) {
        int hours = (elapsedTime / 3600000);
        int minutes = (elapsedTime / 60000) % 60;
        int seconds = (elapsedTime / 1000) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new Pomodoro();
    }
}

