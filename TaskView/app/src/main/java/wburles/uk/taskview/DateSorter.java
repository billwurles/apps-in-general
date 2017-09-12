package wburles.uk.taskview;

import java.util.Comparator;

/**
 * Created by Will on 18/03/2016.
 */
public class DateSorter implements Comparator<Task> {
    public int compare(Task task1, Task task2){
        return task1.getCal().getTime().compareTo(task2.getCal().getTime());
    }
}
