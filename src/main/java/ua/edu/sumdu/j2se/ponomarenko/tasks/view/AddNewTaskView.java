package ua.edu.sumdu.j2se.ponomarenko.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddNewTaskView {

    final static Logger logger = Logger.getLogger(AddNewTaskView.class);

    public Task addNewTask() {
        String title = setTitle();
        Task task;
        LocalDateTime start;
        LocalDateTime end;
        if (repeatInterval()) {
            do {
                start = setStartTime();
                end = setEndTime();
                if (start.isAfter(end)) {
                    System.out.println("Start time is after end time;");
                }
            } while (start.isAfter(end));
            int interval = setRepeatInterval();
            task = new Task(title, start, end, interval);
            task.setActive(true);
        } else {
            LocalDateTime time = setStartTime();
            task = new Task(title, time);
            task.setActive(true);
        }
        logger.info("Created new task - " + task.getTitle() + "\n");
        return task;
    }

    public String setTitle() {
        System.out.print("Title: ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();
        return title;
    }

    public LocalDateTime setStartTime() {
        System.out.println("Set start time ");
        LocalDateTime startTime;
        startTime = setLocalDateTime();
        return startTime;
    }

    public LocalDateTime setEndTime() {
        System.out.println("Set end time ");
        LocalDateTime endTime;
        endTime = setLocalDateTime();
        return endTime;
    }

    public boolean repeatInterval() {
        System.out.print("Is task repeats (yes/no)? -  ");
        Scanner scanner = new Scanner(System.in);
        String ansRepeat = scanner.nextLine();
        if (ansRepeat.equals("yes")) {
            return true;
        } else if (ansRepeat.equals("no")) {
            return false;
        } else {
            logger.debug("Expected other answer.");
            System.out.println("Expected answer 'yes' or 'no'.");
            return repeatInterval();
        }
    }

    public int setRepeatInterval() {
        int repeatInterval;
        System.out.print("Interval(in minutes): ");
        try {
            Scanner scanner = new Scanner(System.in);
            repeatInterval = scanner.nextInt();
            repeatInterval = repeatInterval * 60;
            if (repeatInterval <= 0) {
                logger.debug("Expected integer more than null.");
                System.out.println("Expected integer more than null, try again!");
                return setRepeatInterval();
            }
            return repeatInterval;
        } catch (Exception e) {
            logger.debug("Expected integer.");
            System.out.println("Expected integer, try again!");
            return setRepeatInterval();
        }
    }

    public LocalDateTime setLocalDateTime() {
        Scanner scanner = new Scanner(System.in);
        int year, month, day, hour, minutes;
        try {
            System.out.print("Year: ");
            year = scanner.nextInt();
            System.out.print("Month: ");
            month = scanner.nextInt();
            System.out.print("Day: ");
            day = scanner.nextInt();
            System.out.print("Hour: ");
            hour = scanner.nextInt();
            System.out.print("Minute: ");
            minutes = scanner.nextInt();
            return LocalDateTime.of(year, month, day, hour, minutes);
        } catch (InputMismatchException e) {
            logger.debug("Expected integer.");
            System.out.println("Expected integer, try again!");
            return setLocalDateTime();
        } catch (DateTimeException e) {
            logger.debug("Cannot create this date.");
            System.out.println("Program cannot create this date, try again!");
            return setLocalDateTime();
        }
    }
}
