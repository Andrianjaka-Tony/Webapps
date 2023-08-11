package helpers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
  public static String connectionString = "jdbc:postgresql://localhost:5432/mais";
  public static String utilisateur = "postgres";
  public static String mdp = "root";

  public static Connection connect() {
    Connection con = null;
    try {
      Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection(Connect.connectionString, Connect.utilisateur, Connect.mdp);
      con.setAutoCommit(false);
    } catch (Exception e) {
      System.out.println(e);
    }
    return con;
  }

  public static Connection mysql() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mais",
          "root", "root");
      connection.setAutoCommit(false);
    } catch (Exception e) {
      System.out.println(e);
    }
    return connection;
  }

  public static Connection oracle() {
    Connection connection = null;
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
          "dazai", "root");
      connection.setAutoCommit(false);
    } catch (Exception e) {
      System.out.println(e);
    }

    return connection;
  }

  public static void fermerConnection(Connection connection, PrintWriter out) {
    try {
      connection.close();
    } catch (Exception e) {
      out.print(e.getMessage());
    }
  }

  public static void fermerRessources(Statement statement, ResultSet resultSet) throws Exception {
    resultSet.close();
    statement.close();
  }
}
