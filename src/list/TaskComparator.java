package list;

import java.util.Comparator;

/**
 * Comparator
 */
@SuppressWarnings("hiding")
public class TaskComparator<Task> implements Comparator<task.Task> {

 public int compare(task.Task t1, task.Task t2) {

  return t1.getDate().compareTo(t2.getDate());

 }

}
