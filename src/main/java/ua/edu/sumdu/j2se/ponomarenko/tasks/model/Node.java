package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * class Node realizes element of LinkedList collections for class Task
 */
public class Node extends LinkedTaskList {
    public Task task;
    public Node prev;
    public Node next;

    /**
     * The empty constructor for Node of likedList
     */
    public Node() { }

    /**
     * The constructor with value of task
     * @param task
     */
    public Node(Task task){
        this.task = task;
    }

    /**
     * The method get task from Node
     * @return task
     */
    public Task getTask(){
        return task;
    }

    /**
     * The method get next element
     * @return next element after task
     */
    public Node getNext(){
        return next;
    }
    /**
     * The method get previous element
     * @return previous element before task
     */
    public Node getPrev(){
        return prev;
    }

    /**
     * The method set next element
     * @return next element after task
     */
    public void setNext(Node node){
        next = node;
    }
    /**
     * The method set previous element
     * @return previous element before task
     */
    public void setPrev(Node node){
        prev = node;
    }

    /**
     * The method clone task from likedList
     * @return clone of task
     * @throws CloneNotSupportedException if method can't clone task
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Node clonedObj = (Node) super.clone();
        if (this.next == null) {
            clonedObj.next = null;
        } else {
            clonedObj.next = (Node) this.next.clone();
        }
        clonedObj.task = (Task) this.task.clone();
        return clonedObj;
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
        if (!super.equals(o)) return false;
        Node tasks = (Node) o;
        return Objects.equals(task, tasks.task) &&
                Objects.equals(prev, tasks.prev) &&
                Objects.equals(next, tasks.next);
    }

    /**
     * The method generate hashcode for task from likedList
     * @return integer hash code of task
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), task, prev, next);
    }

    /**
     * The method generate string value about task
     * @return string
     */
    @Override
    public String toString() {
        return "Node{" +
                "task=" + task +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}