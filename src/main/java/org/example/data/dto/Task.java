package org.example.data.dto;

import org.example.data.dto.flyweight.FlyweightFactory;
import org.example.data.dto.flyweight.Priority;
import org.example.data.dto.flyweight.Status;

public class Task {
    private String title;
    private Status status;
    private Priority priority;
    public Task(TaskBuilder builder) {
        this.title = builder.title;
        this.status=builder.status;
        this.priority=builder.priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(String status) {
        this.status = new Status(status);
    }

    public Status getStatus() {
        return status;
    }

    public void setPriority(String priority) {
        this.priority = new Priority(priority);
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return title + " (" + status + ", Priority: " + priority.getPriority() + ")";
    }


    public static class TaskBuilder {
        private final String title;
        private Status status ;
        Priority priority;
        public TaskBuilder(String title) {
            this.title = title;
            this.status = new Status("ToDo");  // Default
            this.priority = new Priority("Medium"); // Default
        }
        public TaskBuilder setStatus(String status) {
            this.status = new Status(status);
            return this;
        }
        public TaskBuilder setPriority(String priority){
            this.priority = FlyweightFactory.getPriority(priority);
            return this;
        }
        public Task build(){
            return new Task(this);
        }

    }
}