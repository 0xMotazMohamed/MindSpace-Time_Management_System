package timemanager.data.dto;

import java.util.HashMap;
import java.util.Map;
public class FlyweightFactory {
    private static final Map<String, Priority> priorityTypes = new HashMap<>();
    private static final Map<String, Status> statusTypes = new HashMap<>();

    public static Priority getPriority (String priority) {
        priorityTypes.putIfAbsent(priority, new Priority(priority));
        return priorityTypes.get(priority);
    }

    public static Status getStatus (String status) {
        statusTypes.putIfAbsent(status, new Status(status));
        return statusTypes.get(status);
    }
}
