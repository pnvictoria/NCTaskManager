package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    /**
     * Static method for searching sub-collection from list where tasks have dates, which have dates after start and before end
     * @param tasks list with tasks
     * @param start date of start searching
     * @param end date of end searching
     * @return ArrayList with result
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        if (start == null) {
            throw new IllegalArgumentException("From time must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("To time must not be null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time can not be less than end time");
        }
        if (tasks == null) {
            throw new IllegalArgumentException("Tasks must not be null");
        }
        AbstractTaskList subset = new ArrayTaskList();
        Iterator<Task> iter = tasks.iterator();
        while(iter.hasNext()){
            Task temp = iter.next();
            LocalDateTime nextTime = temp.nextTimeAfter(start);
            if(nextTime == null){
                continue;
            }
            if(nextTime.isAfter(start) && nextTime.compareTo(end) <= 0){
                subset.add(temp);
            }
        }
        return subset;
    }

    /**
     * The method realizes SortedMap for Task with key date
     * @param tasks list with tasks
     * @param start date of start
     * @param end date of end
     * @return result of searching task with param's dates
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        if (start == null) {
            throw new IllegalArgumentException("Start time must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("End time must not be null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time can not be less than end time");
        }
        if (tasks == null) {
            throw new IllegalArgumentException("Tasks must not be null");
        }
        SortedMap<LocalDateTime, Set<Task>> result = new TreeMap<LocalDateTime, Set<Task>>();
        Iterable<Task> subset = incoming(tasks,start,end);
        for (Task temp : subset) {
            LocalDateTime happening = temp.nextTimeAfter(start);
            while (happening != null && !happening.isAfter(end)){
                if (happening.isAfter(start) && !happening.isAfter(end)) {
                    if (result.containsKey(happening)) {
                        result.get(happening).add(temp);
                    } else {
                        result.put(happening, new HashSet<Task>());
                        result.get(happening).add(temp);
                    }
                }
                happening = temp.nextTimeAfter(happening);
            }
        }
        return result;
    }
}
