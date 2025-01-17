package org.example.data.dto.flyweight;

import org.example.data.dto.flyweight.PriorityType;

public class Priority {
    PriorityType priorityType;

    public Priority(String priority) {
        if (priority.equalsIgnoreCase("High"))
            this.priorityType = PriorityType.HIGH;
        else if (priority.equalsIgnoreCase("Medium"))
            this.priorityType = PriorityType.MEDIUM;
        else if (priority.equalsIgnoreCase("Low"))
            this.priorityType = PriorityType.LOW;
    }

    public PriorityType getPriority() {
        return this.priorityType;
    }
}