package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Task {

  private int id;
  private String name;
  private int duration;
  private boolean isFinished;
  private int importance;

  public Task() {
  }

  public Task(int id, String name, int duration, boolean isFinished, int importance) {
    setId(id);
    setName(name);
    setDuration(duration);
    setFinished(isFinished);
    setImportance(importance);
  }

  public Task(String name, int duration, boolean isFinished, int importance) {
    setName(name);
    setDuration(duration);
    setFinished(isFinished);
    setImportance(importance);
  }

  public Task(String name, int duration, int importance) {
    setName(name);
    setDuration(duration);
    setFinished(false);
    setImportance(importance);
  }

  public static Vector<Task> findAll(Connection connection) throws Exception {
    String query = "SELECT * FROM task";
    Vector<Task> response = new Vector<Task>();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      Task task = new Task(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getInt("duration"),
          resultSet.getBoolean("isFinished"),
          resultSet.getInt("importance"));
      response.add(task);
    }
    resultSet.close();
    statement.close();

    return response;
  }

  public static Task findById(Connection connection, String id) throws Exception {
    String query = "SELECT * FROM task WHERE id = '" + id + "'";
    Task response = new Task();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      response.setId(resultSet.getInt("id"));
      response.setName(resultSet.getString("name"));
      response.setDuration(resultSet.getInt("duration"));
      response.setImportance(resultSet.getInt("importance"));
      response.setFinished(resultSet.getBoolean("isFinished"));
    }
    statement.close();
    resultSet.close();

    return response;
  }

  public void insert(Connection connection) throws Exception {
    String query = "INSERT INTO task (name, duration, isFinished, importance) VALUES ('" + getName() + "', '"
        + getDuration() + "', '" + false + "', '" + getImportance() + "')";
    Statement statement = connection.createStatement();
    statement.execute(query);
    statement.close();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public boolean isFinished() {
    return isFinished;
  }

  public void setFinished(boolean isFinished) {
    this.isFinished = isFinished;
  }

  public int getImportance() {
    return importance;
  }

  public void setImportance(int importance) {
    this.importance = importance;
  }
}
