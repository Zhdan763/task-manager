package com.javalab.util;

import com.javalab.exceptions.CreateTaskException;

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
}
