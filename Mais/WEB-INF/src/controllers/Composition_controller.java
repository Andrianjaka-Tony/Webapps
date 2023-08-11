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
import models.Engrais;
import models.Parcelle;
import models.Terrain;

public class Composition_controller extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setHeader("Access-Control-Allow-Origin", "*");
    PrintWriter out = resp.getWriter();
    Connection connection = Connect.connect();
    Connection connection2 = Connect.mysql();
    try {
      String idTerrain = req.getParameter("terrain");
      Terrain terrain = Terrain.trouverParId(connection, idTerrain);
      terrain.setParcelles(terrain.parcellesActuelles(connection));
      Parcelle meilleureParcelle = terrain.meilleureComposition(connection, connection2);
      Vector<Engrais> engrais = meilleureParcelle.getEngrais();
      out.println(JSON.stringify(engrais));
    } catch (Exception e) {
      out.println(e.getMessage());
    } finally {
      Connect.fermerConnection(connection, out);
    }
  }

}
