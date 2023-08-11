package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import tools.Connect;
import tools.JSON;

@WebServlet("/task/find-all")
public class TaskListController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setContentType("application/json");
    Connection connection = Connect.pgsql();
    try {
      Vector<Task> tasks = Task.findAll(connection);
      response.getWriter().println(JSON.stringify(tasks));
    } catch (Exception e) {
      response.getWriter().println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        response.getWriter().println(e.getMessage());
      }
    }
  }
  
}
