package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Class TaskIO realizes methods for writing/reading tasks list to/from file of stream
 */
public class TaskIO {

    /**
     * The method write tasks from list into OutputStream in binary format
     * @param tasks list for writing
     * @param out stream for writing
     * @throws IOException if method can't write task into stream
     */
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        try {
            outputStream.writeInt(tasks.size());
            Iterator<Task> iterator = tasks.iterator();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = iterator.next();
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeChars(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());

                if (task.isRepeated()) {
                    outputStream.writeInt(task.getStartTime().getNano());
                    outputStream.writeInt(task.getEndTime().getNano());
                } else {
                    outputStream.writeInt(task.getTime().getNano());
                }
                iterator.hasNext();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            outputStream.close();
        }
    }

    /**
     * The method read tasks from InputStream in binary format
     * @param tasks list for reading
     * @param in stream for reading
     * @throws IOException if can't read tasks from stream
     */
    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        try {
            String title;
            char lengthTitle;
            int time = 0;
            int startTime = 0;
            int endTime = 0;
            int repeatInterval = 0;
            boolean active;
            int length = inputStream.readInt();

            Iterator<Task> iterator = tasks.iterator();
            for (int i = 0; i < length; i++) {
                int lengthOfTitle = inputStream.readInt();
                byte[] forTitle = new byte[lengthOfTitle * 2];
                for (int j = 0; j < lengthOfTitle * 2; j++) {
                    forTitle[j] = inputStream.readByte();
                }
                title = new String(forTitle, "UTF-16");
                active = inputStream.readBoolean();
                repeatInterval = inputStream.readInt();
                if (repeatInterval != 0) {
                    startTime = inputStream.readInt();
                    endTime = inputStream.readInt();
                } else {
                    time = inputStream.readInt();
                }
                Task task;
                if (time != 0) {
                    task = new Task(title, LocalDateTime.now().withNano(time));
                    task.setActive(active);
                } else {
                    task = new Task(title, LocalDateTime.now().withNano(startTime),
                            LocalDateTime.now().withNano(endTime), repeatInterval);
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            inputStream.close();
        }

    }

    /**
     * The method write tasks from list into File in binary format
     * @param tasks list for writing
     * @param file for writing
     * @throws IOException if method can't write task into file
     */
    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        try {
            TaskIO.write(tasks, outputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            outputStream.close();
        }
    }

    /**
     * The method read tasks from File in binary format
     * @param tasks list for reading
     * @param file file for reading
     * @throws IOException if can't read tasks from file
     */
    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        try {
            TaskIO.read(tasks, inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            inputStream.close();
        }
    }

    /**
     * The method write tasks from list into Writer in text format
     * @param tasks list for writing
     * @param out writer for writing
     * @throws IOException if method can't write task into stream
     */
    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Iterator<Task> iterator = tasks.iterator();
        Task[] tasksNew = new Task[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            tasksNew[i] = iterator.next();
            iterator.hasNext();
        }
        gson.toJson(tasksNew, out);
        out.close();
    }

    /**
     * The method read tasks from Reader in text format
     * @param tasks list for reading
     * @param in reader for reading
     * @throws IOException if can't read tasks from stream
     */
    public static void read(AbstractTaskList tasks, Reader in) {
        Gson gson = new  GsonBuilder().setPrettyPrinting().create();
        Task[] tasksNew = gson.fromJson(in, Task[].class);
        for (Task task: tasksNew) {
            tasks.add(task);
        }
    }

    /**
     * The method write tasks list into file in text format
     * @param tasks list for reading
     * @param file for writing
     * @throws IOException
     */
    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        try {
            TaskIO.write(tasks, fileWriter);
        } finally {
            fileWriter.close();
        }
    }

    /**
     * The method read tasks list from file in text format
     * @param tasks list for writing
     * @param file for reading
     * @throws IOException
     */
    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        try {
            TaskIO.read(tasks, fileReader);
        } finally {
            fileReader.close();
        }
    }
}