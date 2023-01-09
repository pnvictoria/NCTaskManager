package ua.edu.sumdu.j2se.ponomarenko.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UpdateTaskView {

    final static Logger logger = Logger.getLogger(UpdateTaskView.class);
    private AddNewTaskView addNewTaskView;

    public UpdateTaskView() {
        addNewTaskView = new AddNewTaskView();
    }

    public String questionChange(Task task) {
        System.out.println("What do you want to change?");
        if (task.isRepeated()) {
            System.out.println("(title/start/end/interval)");
        } else {
            System.out.println("(title/time)");
        }
        System.out.print("Answer: ");
        Scanner scanner = new Scanner(System.in);
        String chooseTask = scanner.nextLine();
        return chooseTask;
    }

    public void updateTask(Task task) {
        String chooseTask = questionChange(task);
        logger.info("Was written - " + chooseTask + ".");
        boolean change = false;
        if (chooseTask.equals("title")) {
            String st = addNewTaskView.setTitle();
            task.setTitle(st);
            change = true;
        }
        if (task.isRepeated()) {
            if (chooseTask.equals("start")) {
                LocalDateTime newTime = addNewTaskView.setLocalDateTime();
                task.setTime(newTime, task.getEndTime(), task.getRepeatInterval());
                change = true;
            }
            if (chooseTask.equals("end")) {
                LocalDateTime newTime = addNewTaskView.setLocalDateTime();
                task.setTime(task.getStartTime(), newTime, task.getRepeatInterval());
                change = true;
            }
            if (chooseTask.equals("interval")) {
                int newInteral = addNewTaskView.setRepeatInterval();
                task.setTime(task.getStartTime(), task.getEndTime(), newInteral);
                change = true;
            }
        } else {
            if (chooseTask.equals("time")) {
                LocalDateTime newTime = addNewTaskView.setLocalDateTime();
                task.setTime(newTime);
                change = true;
            }
        }
        if (change == true) {
            logger.info("Your task " + chooseTask + " was changed!");
        } else {
            logger.info("Nothing changed.");
        }

    }
}
