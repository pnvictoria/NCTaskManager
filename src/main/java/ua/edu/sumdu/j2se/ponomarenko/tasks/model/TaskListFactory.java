package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

/**
 * Class TaskListFactory realizes creating the task of some type
 */
public class TaskListFactory {
    /**
     * The method to create a new task list of
     * some choosing type
     * @param type type of list
     */
    public static AbstractTaskList createTaskList(ListTypes.type type) {
        AbstractTaskList list = null;
        switch (type) {
            case ARRAY:
                list = new ArrayTaskList();
                break;
            case LINKED:
                list = new LinkedTaskList();
                break;
            default:
                throw new IllegalArgumentException("Wrong list type: " + type);
        }
        return list;
    }
}
