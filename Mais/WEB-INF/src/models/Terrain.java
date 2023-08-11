package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import exceptions.PoidsException;
import exceptions.SurfaceException;
import helpers.Connect;
import helpers.JSON;

public class Terrain extends Terre {
  private Date etude;
  private Vector<Parcelle> parcelles;
  private int nombreParcelle;

  public Terrain() {
    super();
  }

  public Terrain(String id, String titre, double surface) throws SurfaceException {
    super(id, titre, surface);
  }

  public Terrain(String id, String titre, double surface, Date etude, Vector<Parcelle> parcelles)
      throws SurfaceException {
    super(id, titre, surface);
    setEtude(etude);
    setParcelles(parcelles);
  }

  public Terrain(String id, String titre, double surface, int nombreParcelle, Date etude)
      throws Exception {
    setId(id);
    setTitre(titre);
    setSurface(surface);
    setNombreParcelle(nombreParcelle);
    setEtude(etude);
  }

  public static Terrain trouverParId(Connection connection, String id) throws Exception {
    Terrain reponse = null;
    String query = "SELECT * FROM terrainAfficher WHERE id = '" + id + "' ORDER BY date ASC";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse = new Terrain(
          result.getString("id"),
          result.getString("titre"),
          result.getDouble("surface"),
          result.getInt("nombre"),
          result.getDate("date"));
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public static Vector<Terrain> toutSelectionner(Connection connection) throws Exception {
    Vector<Terrain> reponse = new Vector<Terrain>();
    String query = "SELECT * FROM terrainAfficher ORDER BY date ASC";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse.add(new Terrain(
          result.getString("id"),
          result.getString("titre"),
          result.getDouble("surface"),
          result.getInt("nombre"),
          result.getDate("date")));
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public Vector<Parcelle> trouverParcelles(Connection connection) throws Exception {
    Vector<Parcelle> reponse = new Vector<Parcelle>();
    String query = "SELECT * FROM parcelleTerrain WHERE terrain = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse.add(
          new Parcelle(result.getString("id"), result.getString("titre"), result.getDouble("surface")));
    }
    Connect.fermerRessources(statement, result);
    return reponse;
  }

  public Vector<Parcelle> trouverParcelles(Connection connection, Date date) throws Exception {
    Vector<Parcelle> reponse = this.trouverParcelles(connection);
    for (Parcelle parcelle : reponse) {
      Tige tige = parcelle.trouverTige(connection, date);
      parcelle.setTige(tige);
      Paysan paysan = parcelle.trouverPaysan(connection);
      parcelle.setPaysan(paysan);
    }
    return reponse;
  }

  public Vector<Parcelle> parcellesActuelles(Connection connection) throws Exception {
    Date date = derniereDateEtude(connection);
    return trouverParcelles(connection, date);
  }

  public Date derniereDateEtude(Connection connection) throws Exception {
    Date reponse = null;
    String query = "SELECT date FROM derniereDateEtude WHERE terrain = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse = result.getDate("date");
    }
    Connect.fermerRessources(statement, result);
    return reponse;
  }

  public double croissanceMoyenne(Vector<Parcelle> parcelles, Vector<Parcelle> anterieures) {
    double reponse = 0;
    double totalPourcentage = 0;
    double compteur = 0;
    for (int i = 0; i < parcelles.size(); i++) {
      if (anterieures.get(i).getTige().getHauteur() > parcelles.get(i).getTige().getHauteur()) {
        continue;
      }
      double pourcentage = (parcelles.get(i).getTige().getHauteur() / anterieures.get(i).getTige().getHauteur()) * 100;
      totalPourcentage += pourcentage;
      compteur += 1;
    }
    reponse = totalPourcentage / compteur;

    return reponse;
  }

  public double croissanceMoyenne(Connection connection, Date actuelle, Date anterieure) throws Exception {
    Vector<Parcelle> parcelles = trouverParcelles(connection, actuelle);
    Vector<Parcelle> anterieures = trouverParcelles(connection, anterieure);
    return croissanceMoyenne(parcelles, anterieures);
  }

  public Vector<Date> trouverDatesEtude(Connection connection) throws Exception {
    Vector<Date> reponse = new Vector<Date>();
    String query = "select distinct(terrain), date from etude WHERE terrain = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse.add(result.getDate("date"));
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public double poidsBourgeon(Parcelle parcelle, double poidsTotal) throws Exception {
    if (poidsTotal < PoidsException.min) {
      throw new PoidsException();
    }
    double reponse = parcelle.nombreTotalBourgeons();
    reponse = poidsTotal / reponse;
    return reponse;
  }

  public void modifierParcelleEngrais(Connection connection, Parcelle reference, double poidsTotal) throws Exception {
    double poidsBourgeon = poidsBourgeon(reference, poidsTotal);
    for (Parcelle parcelle : getParcelles()) {
      parcelle.productionEngrais(connection, poidsBourgeon);
    }
  }

  public void modifierListeEngraisParcelles(Connection connection) throws Exception {
    for (Parcelle parcelle : getParcelles()) {
      parcelle.setEngrais(parcelle.trouverEngrais(connection));
    }
  }

  public void examinerRecolteEngrais(Connection connection) throws Exception {
    for (Parcelle parcelle : getParcelles()) {
      parcelle.examinerEngrais(connection);
      System.out.println(JSON.stringify(parcelle.getEngrais()));
    }
  }

  public Vector<Engrais> genererListeEngrais() {
    Vector<Engrais> reponse = new Vector<Engrais>();
    for (Parcelle parcelle : getParcelles()) {
      for (Engrais engrais : parcelle.getEngrais()) {
        reponse.add(engrais);
      }
    }
    return reponse;
  }

  public Vector<Engrais> calculEngrais() throws Exception {
    Vector<Engrais> listeEngrais = genererListeEngrais();
    for (int i = 0; i < listeEngrais.size(); i++) {
      for (int j = i + 1; j < listeEngrais.size(); j++) {
        if (listeEngrais.get(i).getId() == listeEngrais.get(j).getId()) {
          listeEngrais.get(i).setQuantite(
              listeEngrais.get(i).getQuantite() + listeEngrais.get(j).getQuantite());
          listeEngrais.get(i).setProduction(
              listeEngrais.get(i).getProduction() + listeEngrais.get(j).getProduction());
          listeEngrais.remove(listeEngrais.get(j));
        }
      }
    }
    if (listeEngrais.size() == 4) {
      listeEngrais.get(2).setQuantite(
          listeEngrais.get(2).getQuantite() + listeEngrais.get(3).getQuantite());
      listeEngrais.get(2).setProduction(
          listeEngrais.get(2).getProduction() + listeEngrais.get(3).getProduction());
      listeEngrais.remove(3);
    }

    listeEngrais.get(0).setQuantite(95);
    listeEngrais.get(1).setQuantite(55);
    listeEngrais.get(2).setQuantite(150);

    return listeEngrais;
  }

  public Engrais meilleurEngrais() throws Exception {
    Vector<Engrais> engrais = calculEngrais();
    Engrais reponse = engrais.get(0);
    for (Engrais engrais2 : engrais) {
      if (reponse.getProduction() / reponse.getQuantite() < engrais2.getProduction() / engrais2.getQuantite()) {
        reponse = engrais2;
      }
    }

    return reponse;
  }

  public Engrais meilleurEngrais(Connection oracle) throws Exception {
    Vector<Engrais> engrais = calculEngrais();
    Engrais reponse = engrais.get(0);
    for (Engrais engrais2 : engrais) {
      engrais2.setPrix(oracle);
      reponse.setPrix(oracle);
      System.out.println(reponse.getPrix());
      System.out.println(JSON.stringify(engrais2));
      System.out.println(reponse.getProduction() / (reponse.getQuantite() * reponse.getPrix()));
      System.out.println(engrais2.getProduction() / (engrais2.getQuantite() * engrais2.getPrix()));
      if (reponse.getProduction() / (reponse.getQuantite() * reponse.getPrix()) < engrais2.getProduction()
          / (engrais2.getQuantite() * engrais2.getPrix())) {
        reponse = engrais2;
      }
    }

    return reponse;
  }

  public int nombreTotalBourgeons() {
    int reponse = 0;
    for (Parcelle parcelle : parcelles) {
      reponse += parcelle.nombreTotalBourgeons();
    }
    return reponse;
  }

  public double poidsPrevision(Parcelle reference, double poidsTotal) throws Exception {
    double reponse = poidsBourgeon(reference, poidsTotal);
    reponse *= nombreTotalBourgeons();
    return reponse;
  }

  public double prixMais(Connection connection) throws Exception {
    double reponse = 0;
    String query = "SELECT valeur FROM prix";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      reponse = resultSet.getDouble("valeur");
    }
    Connect.fermerRessources(statement, resultSet);
    return reponse;
  }

  public double prixPrevision(Connection connection, Parcelle parcelle, double poidsParcelle) throws Exception {
    double poids = poidsPrevision(parcelle, poidsParcelle);
    double prix = prixMais(connection);
    return poids * prix;
  }

  public Parcelle parcelleReference(String idParcelle) {
    for (Parcelle parcelle : parcelles) {
      if (parcelle.getId().compareToIgnoreCase(idParcelle) == 0) {
        return parcelle;
      }
    }
    return null;
  }

  public Parcelle meilleureComposition(Connection connection, Connection connection2) throws Exception {
    Parcelle reponse = getParcelles().get(0);
    for (Parcelle parcelle : getParcelles()) {
      if (reponse.poidsBourgeon(connection) < parcelle.poidsBourgeon(connection)) {
        reponse = parcelle;
      }
    }
    reponse.setEngrais(reponse.trouverEngrais(connection2));

    return reponse;
  }

  public Date getEtude() {
    return etude;
  }

  public void setEtude(Date etude) {
    this.etude = etude;
  }

  public Vector<Parcelle> getParcelles() {
    return parcelles;
  }

  public void setParcelles(Vector<Parcelle> parcelles) {
    this.parcelles = parcelles;
  }

  public int getNombreParcelle() {
    return nombreParcelle;
  }

  public void setNombreParcelle(int nombreParcelle) {
    this.nombreParcelle = nombreParcelle;
  }

}
