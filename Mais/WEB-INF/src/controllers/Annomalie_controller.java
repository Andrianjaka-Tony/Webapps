package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.Connect;
import helpers.JSON;
import models.Annomalie;
import models.Parcelle;

public class Annomalie_controller extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setHeader("Access-Control-Allow-Origin", "*");
    PrintWriter out = resp.getWriter();
    Connection connection = Connect.connect();
    try {
      String idParcelle = req.getParameter("parcelle");
      Parcelle parcelle = new Parcelle();
      parcelle.setId(idParcelle);
      Vector<Annomalie> annomalies = parcelle.annomalies(connection);
      String json = JSON.stringify(annomalies);
      out.println(json);
    } catch (Exception e) {
      out.println(e.getMessage());
    } finally {
      Connect.fermerConnection(connection, out);
    }
  }

}
