package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import tools.Connect;

@WebServlet("/task/add")
public class TaskAddController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    Connection connection = Connect.pgsql();
    try {
      String name = request.getParameter("name");
      String duration = request.getParameter("duration");
      String importance = request.getParameter("importance");
      Task task = new Task(name, Integer.parseInt(duration), Integer.parseInt(importance));
      task.insert(connection);
      connection.commit();
      response.getWriter().println("0");
    } catch (Exception e) {
      try {
        connection.rollback();
      } catch (Exception f) {
        response.getWriter().println(f.getMessage());
      }
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
