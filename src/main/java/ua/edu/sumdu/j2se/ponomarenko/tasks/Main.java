package ua.edu.sumdu.j2se.ponomarenko.tasks;

import ua.edu.sumdu.j2se.ponomarenko.tasks.controller.MainController;

import java.io.IOException;
import org.apache.log4j.Logger;

public class Main {
    public static String jsonFile = "testNew.json";
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("Program has been activated");
        MainController mainController = new MainController();
        mainController.startWork();
    }
}
