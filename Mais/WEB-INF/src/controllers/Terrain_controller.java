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
import models.Parcelle;
import models.Terrain;

public class Terrain_controller extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setHeader("Access-Control-Allow-Origin", "*");
    PrintWriter out = resp.getWriter();
    Connection connection = Connect.connect();
    try {
      String idTerrain = req.getParameter("terrain");
      Terrain terrain = Terrain.trouverParId(connection, idTerrain);
      Vector<Parcelle> parcelles = terrain.parcellesActuelles(connection);
      String json = JSON.stringify(parcelles);
      out.println(json);
    } catch (Exception e) {
      out.println(e.getMessage());
    } finally {
      Connect.fermerConnection(connection, out);
    }
  }

}
