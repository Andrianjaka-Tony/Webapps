package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import tools.Connect;
import tools.JSON;

@WebServlet("/task/find-by-id")
public class TaskByIdController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    Connection connection = Connect.pgsql();
    try {
      Task task = Task.findById(connection, request.getParameter("id"));
      response.getWriter().println(JSON.stringify(task));
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
