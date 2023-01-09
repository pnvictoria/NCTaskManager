package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Class LinkedTaskList realizes LinkedList collections for class Task
 */
public class LinkedTaskList extends AbstractTaskList implements Cloneable {
    private Node head;
    private Node tail;
    private int sizeList;

    /**
     * The method clone object of the class
     *@return clone of this
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        LinkedTaskList clonedObj = (LinkedTaskList) super.clone();
        try {
            clonedObj.head = (Node) head.clone();
            try {
                clonedObj.tail = (Node) tail.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clonedObj;
    }

    /**
     * The method get size of liked list
     * @return integer values
     */
    @Override
    public int size() {
        return sizeList;
    }

    /**
     * The method add new task into linked list collection
     * @param task for adding
     */
    @Override
    public void add(Task task) {
        if (task == null) {
            System.out.println("error");
            return;
        }
        Node tempElement = new Node();
        tempElement.task = task;
        if (tail == null) {
            tempElement.next = null;
            tempElement.prev = null;
            head = tempElement;
            tail = tempElement;
        } else {
            tail.next = tempElement;
            tempElement.prev = tail;
            tail = tempElement;
        }
        sizeList++;
    }

    /**
     * The method remove element of linked list
     *
     * @param task have task for removing
     * @return true if task was removing, else return false
     */
    @Override
    public boolean remove(Task task) {
        if (head.task == task) {
            if (head.next != null) {
                head = head.next;
                head.prev = null;
                sizeList--;
                return true;
            } else {
                head = null;
                tail = null;
                sizeList = 0;
                return true;
            }

        } else if (tail.task == task) {
            tail = tail.prev;
            tail.next = null;
            sizeList--;
            return true;
        }
        Node temp = head;
        while (temp.task != task) {
            if (temp == tail) break;
            else temp = temp.next;
        }
        if (temp.task == task) {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            temp = null;
            sizeList--;
            return true;
        }
        return false;
    }

    /**
     * The method get elements of linked list dependents on its index
     *
     * @param index of element in linked list
     * @return task with index
     */
    @Override
    public Task getTask(int index) {
        if (index < 0) {
            System.out.println("error");
            Task out = null;
            return out;
        }
        if (index >= sizeList) {
            System.out.println("error");
            Task out = null;
            return out;
        }
        if (index == 0) {
            return head.task;
        }
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        return temp.task;
    }

    @Override
    protected Stream<Task> stream() {
        return null;
    }

    /**
     * Iterator for LinkedTaskListIterator
     * realises standard methods of iterator
     */
    public Iterator iterator() {

        Iterator iterator = new Iterator() {

            private Node current = head;
            private Node lastReturned = null;
            private boolean isNext = false;

            /**
             * The method verifiable if linked list has next element
             * @return return true if has, else return false
             */
            public boolean hasNext() {
                return current != null;
            }

            /**
             * The method get next object from collections
             * @return next element of linked list
             */
            public Task next() {
                if (hasNext()) {
                    lastReturned = current;
                    current = current.next;
                    isNext = true;
                    return lastReturned.task;
                }
                isNext = true;
                return current.task;
            }

            /**
             * The method for removing element of linked list
             */
            public void remove() {
                if (!isNext) throw new IllegalStateException();
                else {
                    if (lastReturned.prev != null) {
                        current.prev = lastReturned.prev;
                        lastReturned.prev.next = current;
                    } else {
                        current.prev = null;
                        head = current;
                    }
                }
            }

        };
        return iterator;
    }

    /**
     * The method compare any object with this
     * @param o have any object for comparison
     * @return true if o equals this, else false
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        Iterator it = iterator();
        Iterator _it = ((LinkedTaskList) o).iterator();
        boolean result = false;
        while (it.hasNext() && !result) {
            if (it.next().equals(_it.next())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * The method generate hash code for object of this class
     * @return integer values
     */
    public int hashCode() {
        int result = 0;
        Iterator it = iterator();
        while (it.hasNext()) {
            result += it.next().hashCode();
        }
        return result;
    }

    /**
     * The method parse LinkedTaskList to string values
     * @return string values
     */
    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "head=" + head +
                ", tail=" + tail +
                ", sizeList=" + sizeList +
                '}';
    }
}
