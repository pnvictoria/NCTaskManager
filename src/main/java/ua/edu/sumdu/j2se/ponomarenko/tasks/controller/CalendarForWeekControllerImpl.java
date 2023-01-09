package ua.edu.sumdu.j2se.ponomarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.CalendarForWeekView;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.FullTaskListView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class CalendarForWeekControllerImpl implements CalendarForWeekController {
    final static Logger logger = Logger.getLogger(CalendarForWeekControllerImpl.class);
    private CalendarForWeekView calendarForWeekView;
    private AbstractTaskList abstractTaskList;
    private FullTaskListView fullTaskListView;

    public CalendarForWeekControllerImpl(AbstractTaskList abstractTaskList) {
        this.abstractTaskList = abstractTaskList;
        calendarForWeekView = new CalendarForWeekView();
        fullTaskListView = new FullTaskListView();
    }

    @Override
    public void listSetTime() {
        if (abstractTaskList.size() == 0) {
            fullTaskListView.emptyTaskList();
        } else {
            LocalDateTime startWeek = calendarForWeekView.getStartTime();
            logger.info("Got start time for calendar");
            LocalDateTime endWeek = calendarForWeekView.getEndTime();
            logger.info("Got end time for calendar");
            SortedMap<LocalDateTime, Set<Task>> newSortedMap = Tasks.calendar(abstractTaskList, startWeek, endWeek);
            calendarForWeekView.printCalendar(newSortedMap);
        }
    }

    @Override
    public void listForWeek() {
        if (abstractTaskList.size() == 0) {
            fullTaskListView.emptyTaskList();
        } else {
            LocalDateTime startWeek = LocalDateTime.now();
            logger.info("Got start time for calendar");
            LocalDateTime endWeek = startWeek.plusWeeks(1);
            logger.info("Got end time for calendar");
            SortedMap<LocalDateTime, Set<Task>> newSortedMap = Tasks.calendar(abstractTaskList, startWeek, endWeek);
            calendarForWeekView.printCalendar(newSortedMap);
        }
    }
}
