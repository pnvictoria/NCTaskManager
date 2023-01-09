package ua.edu.sumdu.j2se.ponomarenko.tasks.view;

import ua.edu.sumdu.j2se.ponomarenko.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class NotificationView {

        public void printNotification(SortedMap<LocalDateTime, Set<Task>> tasks) {
            Iterator<Set<Task>> setIterator = tasks.values().iterator();
            Iterator<LocalDateTime> timeIterator = tasks.keySet().iterator();
            LocalDateTime current = timeIterator.next();
            Iterator<Task> iterator = setIterator.next().iterator();
            do {
                System.out.print("Now is "
                        + current.format(DateTimeFormatter.ofPattern("HH:mm"))
                        + " and you have to do task - ");
                String title = iterator.next().getTitle();
                System.out.println(title);
            } while (iterator.hasNext());
        }

}
