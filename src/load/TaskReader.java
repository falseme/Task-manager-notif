package load;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import task.Task;

/**
 * TaskReader
 */
public class TaskReader {

 private static File folder = new File("tasks");
 private static File file = null;

 /**
  * Save the given tasks into a folder inside the build path
  *
  * @param tasks The Task objects list to save
  * @param code  The intern code to save the tasks with. Represents the order in
  *              the week, beeing '0' 'sunday' and '6' 'saturday'
  **/
 public static void save(LinkedList<Task> tasks, int code) {

  try {

   folder.mkdir();
   file = new File(folder.getPath() + File.separator + code);

   if (tasks.isEmpty()) {

    if (file.exists())
     file.delete();

    return;

   }

   file.createNewFile();

   System.out.println(file.getPath());
   TaskList taskList = new TaskList(tasks);
   taskList.serialize(file);

  } catch (IOException ex) {
   ex.printStackTrace();
  }

 }

 public static Task[] read(int code) {

  try {

   folder.mkdir();
   file = new File(folder.getPath() + File.separator + code);

   if (!file.exists())
    return null;

   ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
   TaskList taskList = (TaskList) in.readObject();
   in.close();

   return taskList.getTasks();

  } catch (EOFException ex1) {

   System.out.println("Week " + code + " is empty. The end of the file was reached (EOFException)");

   return null;

  } catch (Exception ex) {
   ex.printStackTrace();
   return null;
  }

 }

 private static class TaskList implements Serializable {
  private static final long serialVersionUID = 42l;

  private Task[] tasks;

  public TaskList(LinkedList<Task> tasks) {

   this.tasks = new Task[tasks.size()];
   this.tasks = tasks.toArray(this.tasks);

  }

  public void serialize(File file) {

   try {

    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
    out.writeObject(this);
    out.close();

   } catch (Exception ex) {

    ex.printStackTrace();

   }

  }

  public Task[] getTasks() {
   return tasks;
  }

 }

}
