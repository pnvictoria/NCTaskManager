package ua.edu.sumdu.j2se.ponomarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.ponomarenko.tasks.view.NotificationView;

import java.time.LocalDateTime;
import java.util.SortedMap;

public class NotificationController extends Thread {
    final static Logger logger = Logger.getLogger(UpdateTaskControllerImpl.class);

    AbstractTaskList abstractTaskList;
    NotificationView notificationView;

    public NotificationController(AbstractTaskList abstractTaskList) {
        this.abstractTaskList = abstractTaskList;
        notificationView = new NotificationView();
    }

    @Override
    public void run() {
        while (true) {
            SortedMap tasks = Tasks.calendar(abstractTaskList, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1));
            if (!tasks.isEmpty()) {
                try {
                    sleep((60 - LocalDateTime.now().getSecond()) * 1000);
                } catch (InterruptedException e) {
                    logger.debug("Error with notification!");
                }
                System.out.println("");
                logger.info("Notification is found!");
                notificationView.printNotification(tasks);
                tasks = null;
            } else {
                try {
                    sleep(1000 * 50);
                } catch (InterruptedException e) {
                    logger.debug("Error with notification!");
                }
            }
        }
    }
}