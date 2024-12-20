package timemanager.data.dto;
import timemanager.data.dto.features.Task;
import timemanager.data.dto.flyweight.Status;
import timemanager.data.dto.flyweight.StatusType;

import java.util.HashSet;
public class Project {
    private String name;
    private String description;
    private HashSet<Task> toDoTasks = new HashSet<>();
    private HashSet<Task> inProgressTasks = new HashSet<>();
    private HashSet<Task> completedTasks = new HashSet<>();

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<Task> getToDoTasks() {
        return toDoTasks;
    }

    public HashSet<Task> getInProgressTasks() {
        return inProgressTasks;
    }

    public HashSet<Task> getCompletedTasks() {
        return completedTasks;
    }
    public void deleteTask(Task task ,HashSet<Task> source){
        if (source.contains(task)) {
            source.remove(task);          // Remove the task from the source set
        }
        else{
            throw new IllegalArgumentException("Please entre a valid task");
        }
    }
    public HashSet<Task> getTasksByStatus(StatusType statusType) {
        if (statusType == StatusType.COMPLETED)
            return completedTasks;
        else if (statusType == StatusType.INPROGRESS)
            return inProgressTasks;
        else
            return toDoTasks;
    }
    public void addTask(Task task ,HashSet<Task> source){
        if (source == null)
            source = toDoTasks;
        if (task != null) {
            source.add(task);          // Add the task to the source set
        }
        else{
            throw new IllegalArgumentException("Task can't be null");
        }
    }
    public void transferTask(Task task, HashSet<Task> source, HashSet<Task> destination) {
        if (source.contains(task)) {
            source.remove(task);          // Remove the task from the source set
            destination.add(task);       // Add the task to the destination set
        }
        if (task == null) {
            throw new IllegalArgumentException("Task can't be null");
        }
    }
}
