package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class ArrayTaskList realizes collection array for class Task.
 */
public class ArrayTaskList extends AbstractTaskList {
    private static final int DEFAULT = 10;
    private Task[] tasks;
    private int size;

    /**
     * The constructor for class ArrayTasList
     * generate array for 10 elements
     */
    public ArrayTaskList() {
        tasks = new Task[DEFAULT];
        size = 0;
    }

    /**
     * The method add new task in array
     * @param task for adding
     */
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException();
        }
        if (size == tasks.length - 1) {
            Task[] newTasks = Arrays.copyOf(tasks, (size() + 1) * 2);
            tasks = newTasks;
        }
        tasks[size++] = task;
    }

    /**
     * The method remove task from array
     * @param task for removing
     * @return true if task was removed, else false
     */
    @Override
    public boolean remove(Task task) {
        for (int i = 0; i < size; i++) {
            if (task.equals(tasks[i])) {

                for (int j = i; j < size - 1; j++) {
                    tasks[j] = tasks[j + 1];
                }
                tasks[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * The method get size of array
     * @return integer values
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The method get task on index
     * @param index have index for getting task
     * @return element of array dependents on index
     */
    public Task getTask(int index) {
        if (size < index) {
            throw new IndexOutOfBoundsException();
        }
        return tasks[index];
    }

    @Override
    protected Stream<Task> stream() {
        return null;
    }

    /**
     * The method clone object of ArrayTaskList
     * @return clone of this
     */
    @Override
    public Object clone() {
        ArrayTaskList clone;
        try {
            clone = (ArrayTaskList) super.clone();
            clone.tasks = new Task[size()];
            for (int i = 0; i < size(); i++) {
                if (clone != null) {
                    clone.tasks[i] = this.tasks[i].clone();
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    /**
     * The method compare any object with this
     * @param o have any object for comparison
     * @return true if o equals this, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return size == that.size &&
                Arrays.equals(tasks, that.tasks);
    }

    /**
     * The method generate hash code for object of this class
     * @return integer values
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(tasks);
        return result;
    }

    /**
     * The method parse ArrayTaskList to string values
     * @return string values
     */
    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + Arrays.toString(tasks) +
                ", size=" + size +
                '}';
    }
}
