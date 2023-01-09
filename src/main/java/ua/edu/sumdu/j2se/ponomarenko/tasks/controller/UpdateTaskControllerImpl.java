package ua.edu.sumdu.j2se.ponomarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.FullTaskListView;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.UpdateTaskView;

public class UpdateTaskControllerImpl implements UpdateTaskController {
    final static Logger logger = Logger.getLogger(UpdateTaskControllerImpl.class);

    private AbstractTaskList abstractTaskList;
    private FullTaskListView fullTaskListView;
    private UpdateTaskView updateTaskView;

    public UpdateTaskControllerImpl(AbstractTaskList abstractTaskList) {
        this.abstractTaskList = abstractTaskList;
        fullTaskListView = new FullTaskListView();
        updateTaskView = new UpdateTaskView();
    }

    @Override
    public void updateList(Task task) {
        if (abstractTaskList.size() > 0) {
            updateTaskView.updateTask(task);
            System.out.println("Updated task list: ");
            new FullTaskListView().printTaskTitle(abstractTaskList);
        } else {
            fullTaskListView.emptyTaskList();
        }
    }

    @Override
    public void deleteTask() {
        new FullTaskListControllerImpl(abstractTaskList).printTaskTitle();
        try {
            int choose = fullTaskListView.chooseTask();
            if (choose <= abstractTaskList.size()) {
                Task task = abstractTaskList.getTask(choose - 1);
                abstractTaskList.remove(task);
                logger.info("Task has been deleted.");
                System.out.println("Updated task list: ");
                new FullTaskListView().printTaskTitle(abstractTaskList);
            } else {
                logger.debug("Wrong number of task.");
                deleteTask();
            }
        } catch (IndexOutOfBoundsException e) {
            logger.debug("Wrong number of task.");
            deleteTask();
        }
    }

    @Override
    public void updateTask() {
        new FullTaskListControllerImpl(abstractTaskList).printTaskTitle();
        try {
            int choose = fullTaskListView.chooseTask();
            if (choose <= abstractTaskList.size()) {
                Task task = abstractTaskList.getTask(choose - 1);
                updateList(task);
            } else {
                logger.debug("Wrong number of task.");
                updateTask();
            }
        } catch (Exception e) {
            logger.debug("Wrong number of task.");
            updateTask();
        }
    }
}
