package ua.edu.sumdu.j2se.ponomarenko.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.*;

public class CalendarForWeekView {

    private AddNewTaskView addNewTaskView;

    public CalendarForWeekView() {
        addNewTaskView = new AddNewTaskView();
    }

    final static Logger logger = Logger.getLogger(CalendarForWeekView.class);

    public LocalDateTime getStartTime() {
        System.out.println("Enter start of interval");
        return new AddNewTaskView().setLocalDateTime();
    }

    public LocalDateTime getEndTime() {
        System.out.println("Enter end of interval");
        return new AddNewTaskView().setLocalDateTime();
    }

    public void printCalendar(SortedMap<LocalDateTime, Set<Task>> map) {
        if (map.size() > 0) {
            for (Map.Entry<LocalDateTime, Set<Task>> tasks : map.entrySet()) {
                Iterator iterator = tasks.getValue().iterator();
                while (iterator.hasNext()) {
                    Task task = (Task) iterator.next();
                    System.out.println("Title: " + task.getTitle());
                }
                logger.info("Calendar was printed.");
                break;
            }
        } else {
            logger.info("Calendar is empty.");
        }
    }
}
