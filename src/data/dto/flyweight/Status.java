package org.example.data.dto.flyweight;

import org.example.data.dto.flyweight.StatusType;

public class Status {
    StatusType statusType;

    public Status(String status) {
        if (status.equalsIgnoreCase("ToDo"))
            this.statusType = StatusType.TODO;
        else if (status.equalsIgnoreCase("InProgress"))
            this.statusType = StatusType.INPROGRESS;
        else if (status.equalsIgnoreCase("Completed"))
            this.statusType = StatusType.COMPLETED;
    }

    public StatusType getStatus() {
        return this.statusType;
    }
}
