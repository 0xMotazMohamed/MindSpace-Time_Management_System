package timemanager.data.dto.features;

import timemanager.data.dto.flyweight.FlyweightFactory;
import timemanager.data.dto.flyweight.Priority;
import timemanager.data.dto.flyweight.Status;

public class Task {
    private String title;
    private Status status;
    private Priority priority;

    // Constructors
    public Task(String title, String p) {
        this.title = title;
        this.status = new Status("ToDo"); // Default status
        Priority priority = FlyweightFactory.getPriority(p);
        this.priority = priority;
    }

    public Task(String title) {
        this.title = title;
        this.status = new Status("ToDo");
        this.priority = new Priority("Medium");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return title + " (" + status + ", Priority: " + priority.getPriority() + ")";
    }

}

