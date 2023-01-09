package ua.edu.sumdu.j2se.ponomarenko.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FullTaskListView {

    final static Logger logger = Logger.getLogger(FullTaskListView.class);

    public void printFullTaskList(AbstractTaskList abstractTaskList) {
        System.out.println("All list have " + abstractTaskList.size() + " tasks.");
        for (int i = 0; i < abstractTaskList.size(); i++) {
            System.out.println("Task № " + (i + 1));
            printFullInf(abstractTaskList.getTask(i));
        }
    }

    public void printTaskTitle(AbstractTaskList abstractTaskList) {
        System.out.println("All list have " + abstractTaskList.size() + " tasks.");
        for (int i = 0; i < abstractTaskList.size(); i++) {
            System.out.print("Task № " + (i + 1) + " - ");
            System.out.println(abstractTaskList.getTask(i).getTitle());
        }
    }

    public void emptyTaskList() {
        logger.info("Task list is empty!");
    }

    public int chooseTask() {
        System.out.print("What do you want to choose? - № ");
        Scanner scanner = new Scanner(System.in);
        int chooseTask = scanner.nextInt();
        return chooseTask;
    }

    public void printFullInf(Task task) {
        task.toString(task);
        System.out.println("Title: " + task.getTitle()
                + "\nActive: " + task.isActive()
                + "\nRepeat: " + task.isRepeated());
        if (task.isRepeated()) {
            System.out.print("Start: ");
            timeFormat(task.getStartTime());
            System.out.print("End: ");
            timeFormat(task.getEndTime());
            System.out.println("Interval(in minutes): " + (task.getRepeatInterval()/60));
        } else {
            System.out.print("Start: ");
            timeFormat(task.getTime());
        }
        System.out.println("********************************");
    }

    public void timeFormat(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        System.out.println(formatDateTime);
    }
}
