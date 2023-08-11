package tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

  public static Connection pgsql() {
    Connection con = null;
    try {
      Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todolist", "postgres", "root");
      con.setAutoCommit(false);
    } catch (Exception e) {
      System.out.println(e);
    }
    return con;
  }
}
