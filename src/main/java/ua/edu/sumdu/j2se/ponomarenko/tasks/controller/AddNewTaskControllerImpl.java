package ua.edu.sumdu.j2se.ponomarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.AddNewTaskView;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.FullTaskListView;

public class AddNewTaskControllerImpl implements AddNewTaskController {
    final static Logger logger = Logger.getLogger(AddNewTaskControllerImpl.class);
    private AddNewTaskView addNewTaskView;
    private AbstractTaskList abstractTaskList;

    public AddNewTaskControllerImpl(AbstractTaskList abstractTaskList) {
        this.abstractTaskList = abstractTaskList;
        addNewTaskView = new AddNewTaskView();
    }

    @Override
    public void addNewTask() {
        abstractTaskList.add(addNewTaskView.addNewTask());
        logger.info("Task has been added.");
        System.out.println("Updated task list: ");
        new FullTaskListView().printTaskTitle(abstractTaskList);
    }
}
