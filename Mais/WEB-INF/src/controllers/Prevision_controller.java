package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.Connect;
import helpers.JSON;
import models.Parcelle;
import models.Terrain;

public class Prevision_controller extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setHeader("Access-Control-Allow-Origin", "*");
    PrintWriter out = resp.getWriter();
    Connection connection = Connect.connect();
    Connection connection2 = Connect.mysql();
    Connection oracle = Connect.oracle();
    try {
      String idParcelle = req.getParameter("parcelle");
      Parcelle reference = new Parcelle();
      reference.setId(idParcelle);
      Terrain terrain = reference.trouverTerrain(connection);
      terrain.setParcelles(terrain.parcellesActuelles(connection));
      // reference = terrain.parcelleReference(idParcelle);
      // terrain.modifierParcelleEngrais(connection2, reference, poids);
      // double resultat = terrain.prixPrevision(connection, reference, poids);
      terrain.modifierListeEngraisParcelles(connection2);
      terrain.examinerRecolteEngrais(connection);

      Parcelle ref = null;
      for (Parcelle p : terrain.getParcelles()) {
        if (p.getId().compareToIgnoreCase(idParcelle) == 0) {
          ref = p;
        }
      }

      out.println(JSON.stringify(terrain.meilleurEngrais()) + ",,," + JSON.stringify(terrain.meilleurEngrais(oracle))
          + ",,," + ref.poidsRecolteAdditif(connection) + ",,," + JSON.stringify(terrain.calculEngrais()));
    } catch (Exception e) {
      out.println(e.getMessage());
    } finally {
      Connect.fermerConnection(connection, out);
      Connect.fermerConnection(connection2, out);
    }
  }

}
