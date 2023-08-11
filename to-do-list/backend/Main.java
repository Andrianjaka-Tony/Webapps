import java.sql.Connection;
import java.util.Vector;

import models.Task;
import tools.Connect;
import tools.JSON;

public class Main {
  public static void main(String[] args) {
    
    Vector<Task> tasks = null;
    Connection connection = Connect.pgsql();
    try {
      tasks = Task.findAll(connection);
      System.out.println(JSON.stringify(tasks));
      System.out.println(Task.findById(connection, "5"));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
