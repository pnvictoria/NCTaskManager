package ua.edu.sumdu.j2se.ponomarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.FullTaskListView;

public class FullTaskListControllerImpl implements FullTaskListController{
    final static Logger logger = Logger.getLogger(FullTaskListControllerImpl.class);
    private AbstractTaskList abstractTaskList;
    private FullTaskListView fullTaskListView;

    public FullTaskListControllerImpl(AbstractTaskList abstractTaskList) {
        this.abstractTaskList = abstractTaskList;
        fullTaskListView = new FullTaskListView();
    }

    @Override
    public void printFullTaskList() {
        if (abstractTaskList.size() > 0) {
            fullTaskListView.printFullTaskList(abstractTaskList);
            logger.info("List has been printed.");
        } else {
            fullTaskListView.emptyTaskList();
        }
    }

    @Override
    public void printTaskTitle() {
        if (abstractTaskList.size() > 0) {
            fullTaskListView.printTaskTitle(abstractTaskList);
            logger.info("Tasks title has been printed.");
        } else {
            fullTaskListView.emptyTaskList();
        }
    }

    @Override
    public void fullTaskInf() {
        printTaskTitle();
        int choose = fullTaskListView.chooseTask();
        if (choose <= abstractTaskList.size()) {
            fullTaskListView.printFullInf(abstractTaskList.getTask(choose - 1));
            logger.info("Task information printed.");
        } else {
            logger.debug("Wrong number of task.");
            fullTaskInf();
        }
    }
}
