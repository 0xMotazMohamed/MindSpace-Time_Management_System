package timemanager.gui;

import timemanager.business.bao.BAOFactory;
import timemanager.data.dto.Account;
import timemanager.data.dto.Project;
import timemanager.data.dto.features.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        JSpinner numTasksSpinner = new JSpinner(new SpinnerNumberModel(1,1,100,1));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(desLabel);
        panel.add(scrollPane);
        panel.add(numTasksLabel);
        panel.add(numTasksField);
        panel.add(numTasksSpinner);

        int ProjectOption = JOptionPane.showConfirmDialog(null, panel,
                "Enter Project Details", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (ProjectOption == JOptionPane.OK_OPTION) {
            if ( !nameField.getText().isEmpty()) {
                String projectName = nameField.getText();
                String projectDescription = desArea.getText();
                int numTasks = (Integer) numTasksSpinner.getValue();

                implementNewTasks(baoFactory, account, projectName, projectDescription, numTasks);

            } else {
                JOptionPane.showConfirmDialog(null, "Please, Enter name for Project.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                implementNewProject(baoFactory,account);
            }
        }
    }

    private void implementNewTasks(BAOFactory baoFactory, Account account, String projectName, String projectDescription, int numTasks) {
        JPanel tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel,BoxLayout.Y_AXIS));
        ArrayList<JTextField> taskNames = new ArrayList<>();

        for (int i = 0; i < numTasks; i++) {
            JPanel taskPanel = new JPanel(new GridLayout(1,4));
            taskPanel.add(new JLabel("Task " + (i+1) + " Name:"));
            JTextField taskNameField = new JTextField(10);
            taskNameField.setBackground(Color.LIGHT_GRAY);
            taskNameField.setForeground(Color.GRAY);
            final int taskId = (i + 1);
            taskNameField.setText("Task " + taskId);
            taskNameField.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (taskNameField.getForeground().equals(Color.GRAY)) {
                        taskNameField.setText("");
                        taskNameField.setBackground(null);
                        taskNameField.setForeground(null);
                        taskNameField.grabFocus();
                    }
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (taskNameField.getText().trim().equals("")) {
                        taskNameField.setText("Task " + taskId);
                        taskNameField.setBackground(Color.LIGHT_GRAY);
                        taskNameField.setForeground(Color.GRAY);
                    }
                }
            });

            taskNames.add(taskNameField);
            taskPanel.add(taskNameField);
            tasksPanel.add(taskPanel);
        }

        int taskOption = JOptionPane.showConfirmDialog(null, tasksPanel, "Enter Task Details",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (taskOption == JOptionPane.OK_OPTION) {
            if (!isAnyTaskEmpty(taskNames)) {
                p = new Project(projectName, projectDescription);
                account.addProject(p);
                for (int i = 0; i < numTasks; i++) {
                    p.addTask(new Task(taskNames.get(i).getText()), null);
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Please, Enter name for every task.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                implementNewTasks(baoFactory, account, projectName, projectDescription, numTasks);
            }
        }
    }

    public Project getProject() {
        return p;
    }

    private static boolean isAnyTaskEmpty(ArrayList<JTextField> arrayList) {
        for (JTextField taskname : arrayList) {
            if (taskname.getText().trim().isEmpty())
                return true;
        }
        return false;
    }
}
