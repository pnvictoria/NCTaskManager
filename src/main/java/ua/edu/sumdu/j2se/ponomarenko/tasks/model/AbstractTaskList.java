package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class TaskList is for work with collection of Task
 */
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    AbstractTaskList taskList = null;

    /**
     * The method must return size of Task collection
     * @return integer size
     */
    abstract public int size();

    /**
     * The method must add object of class Task into collection
     * @param task for adding
     */
    abstract public void add(Task task);

    /**
     * The method must remove object of class Task from collection
     * @param task for removing
     */
    abstract public boolean remove(Task task);

    /**
     * The method must get element of collections dependents on param
     * @param index number element in collection
     * @return task with this index
     */
    abstract public Task getTask(int index);

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        if (this instanceof ArrayTaskList) {
            taskList = TaskListFactory.createTaskList(ListTypes.type.ARRAY);
        } else {
            taskList = TaskListFactory.createTaskList(ListTypes.type.LINKED);
        }
        this.getStream()
                .filter(x -> x.nextTimeAfter(from) != null
                        && x.nextTimeAfter(from).isBefore(to))
                .forEach(x -> taskList.add(x));
        return taskList;
    }

    public Stream<Task> getStream() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            taskArrayList.add(this.getTask(i));
        }
        Stream<Task> stream = taskArrayList.stream();
        return stream;
    }

    protected abstract Stream<Task> stream();

    /**
     * The method clone object of ArrayTaskList
     * @return clone of this
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return (AbstractTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Iterator iterator() {
        return new TaskIterator();
    }

    public class TaskIterator implements Iterator {
        private int iterator = -1;
        private int lastRet;

        @Override
        public boolean hasNext() {
            return AbstractTaskList.this.getTask(iterator + 1) != null;
        }

        @Override
        public Task next() {
            iterator++;
            return AbstractTaskList.this.getTask(lastRet++);
        }

        @Override
        public void remove() {
            if (iterator == -1) {
                throw new IllegalStateException();
            }
            AbstractTaskList.this.remove(AbstractTaskList.this.getTask(iterator));
            lastRet--;
            iterator--;
        }
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
        AbstractTaskList tasks = (AbstractTaskList) o;
        return Objects.equals(taskList, tasks.taskList);
    }

    /**
     * The method generate hash code for object of this class
     * @return integer values
     */
    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }

    /**
     * The method parse ArrayTaskList to string values
     * @return string values
     */
    @Override
    public String toString() {
        return "AbstractTaskList{" +
                "taskList=" + taskList +
                '}';
    }
}


