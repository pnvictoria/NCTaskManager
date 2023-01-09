package ua.edu.sumdu.j2se.ponomarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.Main;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.MainView;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {
    final static Logger logger = Logger.getLogger(MainController.class);
    private AbstractTaskList taskList;
    private MainView mainView;
    private FullTaskListControllerImpl fullTaskListControllerImpl;
    private AddNewTaskControllerImpl addNewTaskControllerImpl;
    private CalendarForWeekControllerImpl calendarForWeekControllerImpl;
    private UpdateTaskController updateTaskControllerImpl;

    public MainController() {
        taskList = new ArrayTaskList();
        mainView = new MainView();
        addNewTaskControllerImpl = new AddNewTaskControllerImpl(taskList);
        fullTaskListControllerImpl = new FullTaskListControllerImpl(taskList);
        calendarForWeekControllerImpl = new CalendarForWeekControllerImpl(taskList);
        updateTaskControllerImpl = new UpdateTaskControllerImpl(taskList);
    }

    public void startWork() throws IOException {
        readJson(taskList);
        NotificationController notification = new NotificationController(taskList);
        notification.setDaemon(true);
        notification.start();
        logger.info("Program has been started");
        selectMenu();
    }

    public void selectMenu() throws IOException {
        oper:
        while (true) {
            mainView.startWork();
            Scanner scanner = new Scanner(System.in);
            try {
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        fullTaskListControllerImpl.printFullTaskList();
                        continue oper;
                    case 2:
                        calendarForWeekControllerImpl.listForWeek();
                        continue oper;
                    case 3:
                        calendarForWeekControllerImpl.listSetTime();
                        continue oper;
                    case 4:
                        addNewTaskControllerImpl.addNewTask();
                        writeJson(taskList);
                        continue oper;
                    case 5:
                        updateTaskControllerImpl.updateTask();
                        writeJson(taskList);
                        continue oper;
                    case 6:
                        updateTaskControllerImpl.deleteTask();
                        writeJson(taskList);
                        continue oper;
                    case 7:
                        fullTaskListControllerImpl.fullTaskInf();
                        continue oper;
                    case 0:
                        writeJson(taskList);
                        logger.info("The program was closed.");
                        System.exit(0);
                        continue oper;
                    default:
                        continue oper;
                }
            } catch (InputMismatchException e) {
                logger.debug("Expected integer.");
                selectMenu();
            }
        }
    }

    public void readJson(AbstractTaskList abstractTaskList) throws FileNotFoundException {
        File jsonFile = new File(Main.jsonFile);
        if (jsonFile.exists()) {
            Reader reader = new FileReader(jsonFile);
            TaskIO.read(abstractTaskList, reader);
            logger.info("Json file found.");
        } else {
            logger.debug("Json file has not been found.");
        }
    }

    public void writeJson(AbstractTaskList abstractTaskList) throws IOException {
        File jsonFile = new File(Main.jsonFile);
        Writer writer = new FileWriter(jsonFile);
        if (abstractTaskList.size() == 0) {
            writer.flush();
            logger.info("Information written in Json file.");
        } else {
            TaskIO.write(abstractTaskList, writer);
            logger.debug("Json file has not been found.");
        }
    }
}
