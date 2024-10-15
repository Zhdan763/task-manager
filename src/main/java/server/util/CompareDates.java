package server.util;

import server.exceptions.CreateTaskException;
import shared.model.Status;
import shared.model.Task;

import java.util.Date;

public class CompareDates {
    public static boolean checkDate(Date date) throws CreateTaskException {
        Date dateNow = new Date(System.currentTimeMillis());
        int compare = date.compareTo(dateNow);
        if (compare == 0 || compare == -1) {
            throw new CreateTaskException("date cannot be less than current");
        } else
            return true;
    }

    public static void setStatus(Task task) {
        Date dateNow = new Date(System.currentTimeMillis());
        int compare = task.getDate().compareTo(dateNow);
        if (compare == 0 || compare == -1) {
            task.setTaskStatus(Status.OVERDUE);
        }
    }
}