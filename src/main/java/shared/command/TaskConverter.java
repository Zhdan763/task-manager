package shared.command;

import shared.model.Status;
import shared.model.Task;
import shared.util.IdGenerator;
import shared.util.TaskFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class TaskConverter {
    private Map<String, Object> map;
    private static TaskConverter instance;

    public TaskConverter() {
        this.map = new LinkedHashMap<String, Object>();
    }

    public static TaskConverter getInstance() {
        if (instance == null) {
            instance = new TaskConverter();
        }
        return instance;
    }

    public Task convert(Map<String, Object> taskMap, Boolean changeId) throws ParseException {
        this.map = taskMap;
        String taskName = map.get("taskName").toString();
        String description = map.get("description").toString();
        String dateString = map.get("date").toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        Date date = dateFormat.parse(dateString);
        String taskStatus = map.get("taskStatus").toString();
        Status status = chooseStatus(taskStatus);
        if (changeId) {
            int idTask = IdGenerator.getIdGenerator().getNextId();
            Task task = TaskFactory.createTask(taskName, description, date, idTask, status);
            return task;
        } else {
            int idTask = (Integer) map.get("id");
            Task task = TaskFactory.createTask(taskName, description, date, idTask, status);
            return task;
        }

    }

    private Status chooseStatus(String statusString) {
        Status status = null;
        switch (statusString) {
            case ("COMPLETED"):
                status = Status.COMPLETED;
                break;
            case ("OVERDUE"):
                status = Status.OVERDUE;
                break;
            case ("PENDING"):
                status = Status.PENDING;
                break;
            case ("CANCELED"):
                status = Status.CANCELED;
                break;
        }
        return status;
    }


}
