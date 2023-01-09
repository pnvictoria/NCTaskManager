package ua.edu.sumdu.j2se.ponomarenko.tasks.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class Task is the main class of model
 */
public class Task implements Cloneable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int repeatInterval;
    private boolean active;

    /**
     * The constructor for class task
     * @param title have name for task
     * @param time date for task
     */
    public Task(String title, LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
    }

    /**
     * The constructor  for class Task, if task is repeated
     * @param title have name for task
     * @param start have date of start
     * @param end have date of end
     * @param interval have repeated interval
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if ((start == null) || (end == null) || (interval < 0)) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
    }

    /**
     * The method get value of title
     * @return String value
     */
    public String getTitle() {
        return title;
    }

    /**
     * The method set title for task
     * @param title String value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The method get activity of task
     * @return boolean value
     */
    public boolean isActive() {
        return active;
    }

    /**
     * The method set active for task
     * @param active boolean value
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * The method get date of task if task is repeated
     * return date os start, else return time of task
     * @return startTime or time
     */
    public LocalDateTime getTime() {
        if (isRepeated()) {
            return startTime;
        } else {
            return time;
        }
    }

    /**
     * The method set time into task
     * if task is repeated set repeated false
     * @param time have date for setting
     */
    public void setTime(LocalDateTime time) {
        if (isRepeated()) {
            startTime = null;
            endTime = null;
            repeatInterval = 0;
        }
        this.time = time;
    }

    /**
     * The method get start of task
     * if task is not repeated return time
     * @return date of start
     */
    public LocalDateTime getStartTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return startTime;
        }
    }

    /**
     * The method get end of task
     * @return date of end
     * if task is not repeated return time
     */
    public LocalDateTime getEndTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return endTime;
        }
    }

    /**
     * The method get repeated interval
     * @return integer interval value
     * if task is not repeated return 0
     */
    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        } else {
            return repeatInterval;
        }
    }

    /**
     * The method set time into task
     * if task is not repeated set repeated true
     * @param start have start time
     * @param end have end time
     * @param interval have repeated interval
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (!isRepeated()) {
            time = null;
        }
        startTime = start;
        endTime = end;
        repeatInterval = interval;
    }

    /**
     * The method get repeated of task
     * @return boolean value
     */
    public boolean isRepeated() {
        if (time == null) {
            return true;
        }
        return false;
    }

    /**
     * The method get next repeated time after current date
     * @param current have date for comparison
     * @return next time after current, if task is repeated, else return time
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (!active) {
            return null;
        }
        if (!isRepeated()) {
            if (time.isAfter(current)) {
                return time;
            } else {
                return null;
            }
        } else {
            if (endTime.isBefore(current)) {
                return null;
            } else {
                for (LocalDateTime i = startTime;; i = i.plusSeconds(repeatInterval)) {
                    if ( i.isAfter(current) && (i.isBefore(endTime) || i.isEqual(endTime))) {
                        i = i.withSecond(0).withNano(0);
                        return i;
                    }
                    else if(i.isAfter(endTime)) {
                        return null;
                    }
                }
            }
        }
    }

    /**
     * The method clone task
     * @return clone of task
     * @throws CloneNotSupportedException if method can't clone task
     */
    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
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
        Task task = (Task) o;
        return repeatInterval == task.repeatInterval &&
                active == task.active &&
                Objects.equals(title, task.title) &&
                Objects.equals(time, task.time) &&
                Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime);
    }

    /**
     * The method generate hash code for task
     * @return integer values
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, time, startTime, endTime, repeatInterval, active);
    }

    /**
     * The method parse task to string values
     * @return string values
     */
    public String toString(Task task) {
        if(isRepeated()) {
            return "Task{" +
                    "title='" + title + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", repeatInterval=" + repeatInterval +
                    ", active=" + active +
                    '}';
        } else {
            return "Task{" +
                    "title='" + title + '\'' +
                    ", time=" + time +
                    ", active=" + active +
                    '}';
        }
    }
}


