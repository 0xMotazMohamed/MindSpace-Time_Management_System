import GUI.MainFrame;
import StartUp.Data;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Test");
        Data.setMap();
//        Window.WelcomeDisplay();

        SwingUtilities.invokeLater(MainFrame::new);

        }
}