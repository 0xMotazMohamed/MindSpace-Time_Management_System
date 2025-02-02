package org.example.gui;

import org.example.data.dto.Project;

import javax.swing.*;
import java.awt.*;

public class EditingProject {

    EditingProject(Project project) {
        editProject(project);
    }

    private void editProject(Project project) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Project Title:"), gbc);

        gbc.gridx = 1;
        JTextField nameField = new JTextField(project.getName(), 15);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Project Description:"), gbc);

        gbc.gridx = 1;
        JTextArea desArea = new JTextArea(project.getDescription(), 3,20);
        desArea.setLineWrap(true);
        desArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(desArea);
        panel.add(desArea, gbc);
        panel.add(scrollPane);

        int taskOption = JOptionPane.showConfirmDialog(null, panel,
                "Edit Project Details", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (taskOption == JOptionPane.OK_OPTION) {
            if (!nameField.getText().isEmpty()) {
                String projectName = nameField.getText();
                String projectDescription = desArea.getText();

                project.setName(projectName);
                project.setDescription(projectDescription);
            } else {
                JOptionPane.showConfirmDialog(null, "Please, Enter name for the project.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                editProject(project);
            }
        }
    }
}
