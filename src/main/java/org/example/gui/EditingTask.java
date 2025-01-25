package org.example.gui;

import org.example.data.dto.Project;
import org.example.data.dto.Task;
import org.example.data.dto.flyweight.PriorityType;
import org.example.data.dto.flyweight.StatusType;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EditingTask {

    EditingTask(Project project, Task task) {
        editTask(project, task);
    }

    private void editTask(Project project, Task task) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Task Title"), gbc);

        gbc.gridx = 1;
        JTextField titleField = new JTextField(task.getTitle(), 15);
        panel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Task Status:"), gbc);

        gbc.gridx = 1;
        String[] statusOptions = {"To Do","In Progress", "Completed"};
        JComboBox<String> statusDropdown = new JComboBox<>(statusOptions);
        if (task.getStatus().getStatus().equals(StatusType.TODO)) {
            statusDropdown.setSelectedItem("To Do");
        } else if (task.getStatus().getStatus().equals(StatusType.INPROGRESS)) {
            statusDropdown.setSelectedItem("In Progress");
        } else {
            statusDropdown.setSelectedItem("Completed");
        }
        panel.add(statusDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Task Priority:"), gbc);

        gbc.gridx = 1;
        String[] priorityOptions = {"High","Medium", "Low"};
        JComboBox<String> priorityDropdown = new JComboBox<>(priorityOptions);
        if (task.getPriority().getPriority().equals(PriorityType.HIGH)) {
            priorityDropdown.setSelectedItem("High");
        } else if (task.getPriority().getPriority().equals(PriorityType.MEDIUM)) {
            priorityDropdown.setSelectedItem("Medium");
        } else {
            priorityDropdown.setSelectedItem("Low");
        }
        panel.add(priorityDropdown, gbc);

        int taskOption = JOptionPane.showConfirmDialog(null, panel,
                "Edit Task Details", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (taskOption == JOptionPane.OK_OPTION) {
            if (!titleField.getText().isEmpty()) {

                if (Objects.requireNonNull(statusDropdown.getSelectedItem()).equals("To Do")) {
                    project.transferTask(task, project.getTasksByStatus(task.getStatus().getStatus()),
                            project.getToDoTasks());
                } else if (Objects.requireNonNull(statusDropdown.getSelectedItem()).equals("In Progress")) {
                    project.transferTask(task, project.getTasksByStatus(task.getStatus().getStatus()),
                            project.getInProgressTasks());
                } else if (Objects.requireNonNull(statusDropdown.getSelectedItem()).equals("Completed")){
                    project.transferTask(task, project.getTasksByStatus(task.getStatus().getStatus()),
                            project.getCompletedTasks());
                }


                if (Objects.requireNonNull(priorityDropdown.getSelectedItem()).equals("High")) {
                    task.setPriority("High");
                } else if (Objects.requireNonNull(priorityDropdown.getSelectedItem()).equals("Medium")) {
                    task.setPriority("Medium");
                } else {
                    task.setPriority("Low");
                }
                task.setTitle(titleField.getText().trim());
            } else {
                JOptionPane.showConfirmDialog(null, "Please, Enter title for the task.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                editTask(project, task);
            }
        }
    }
}
