package timemanager.gui;

import javax.swing.*;

public class ImageLabelExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Image in JLabel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null); // Use null layout for custom positioning

        // Load the image
//        ImageIcon i = new ImageIcon()
        ImageIcon imageIcon = new ImageIcon("MindSpace/res/icons/home_outline.png"); // Replace with your image path

        // Create a JLabel with the image
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 50, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        // Add the label to the frame
        frame.add(imageLabel);

        // Make the frame visible
        frame.setVisible(true);
    }
}

