package timemanager.gui;

import timemanager.business.bao.BAOFactory;
import timemanager.data.dto.Account;
import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddingProject {

    Project p = null;

    AddingProject(BAOFactory baoFactory, Account account) {
        implementNewProject(baoFactory, account);
    }

    private void implementNewProject(BAOFactory baoFactory, Account account) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Project Name:");
        JTextField nameField = new JTextField(15);

        JLabel desLabel = new JLabel("Project Description");
        JTextArea desArea = new JTextArea(3,20);
        desArea.setLineWrap(true);
        desArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(desArea);

        JLabel numTasksLabel = new JLabel("Number of Tasks:");
        JTextField numTasksField = new JTextField(5);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(desLabel);
        panel.add(scrollPane);
        panel.add(numTasksLabel);
        panel.add(numTasksField);

        int ProjectOption = JOptionPane.showConfirmDialog(null, panel,
                "Enter Project Details", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (ProjectOption == JOptionPane.OK_OPTION) {
            if ( nameLabel != null && isIntegerBiggerThanZero(numTasksField.getText())) {
                String projectName = nameField.getText();
                String projectDescription = desArea.getText();
                int numTasks = Integer.parseInt(numTasksField.getText());

                Project project = new Project(projectName, projectDescription);

                implementNewTasks(baoFactory, account, project, numTasks);

                account.addProject(project);
                p = project;
            } else {
                JOptionPane.showConfirmDialog(null, "Please, Enter name for Project & valid integer in Number of Tasks.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                implementNewProject(baoFactory,account);
            }
        }
    }

    private void implementNewTasks(BAOFactory baoFactory, Account account, Project project, int numTasks) {
        JPanel tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel,BoxLayout.Y_AXIS));
        ArrayList<JTextField> taskNames = new ArrayList<>();

        for (int i = 0; i < numTasks; i++) {
            JPanel taskPanel = new JPanel(new GridLayout(1,4));
            taskPanel.add(new JLabel("Task " + (i+1) + " Name:"));
            JTextField taskNameField = new JTextField(10);
            taskNames.add(taskNameField);
            taskPanel.add(taskNameField);
            tasksPanel.add(taskPanel);
        }

        int taskOption = JOptionPane.showConfirmDialog(null, tasksPanel, "Enter Task Details",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (taskOption == JOptionPane.OK_OPTION) {
            if (!isAnyTaskEmpty(taskNames)) {
                for (int i = 0; i < numTasks; i++) {
                    project.addTask(new Task(taskNames.get(i).getText()), null);
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Please, Enter name for every task.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                implementNewTasks(baoFactory, account, project, numTasks);
            }
        }
    }

    public Project getProject() {
        return p;
    }

    private static boolean isIntegerBiggerThanZero(String s) {
        try {
            int n = Integer.parseInt(s);
            if (n <= 0)
                return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isAnyTaskEmpty(ArrayList<JTextField> arrayList) {
        for (JTextField taskname : arrayList) {
            if (taskname.getText().trim().isEmpty())
                return true;
        }
        return false;
    }
}
