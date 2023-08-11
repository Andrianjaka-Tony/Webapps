package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import exceptions.NombreTigeException;
import exceptions.SurfaceException;
import helpers.Connect;

public class Parcelle extends Terre {
  private Tige tige;
  private Paysan paysan;
  private Date etude;
  private Vector<Engrais> engrais;

  public Parcelle() {
  }

  public Parcelle(String id, String titre, double surface) throws SurfaceException {
    super(id, titre, surface);
  }

  public Parcelle(String id, String titre, double surface, Tige tige, Paysan paysan)
      throws SurfaceException, NombreTigeException {
    super(id, titre, surface);
    setTige(tige);
    setPaysan(paysan);
  }

  public int nombreTotalBourgeons() {
    return getTige().getNombre() * getTige().getBourgeons();
  }

  public Tige trouverTige(Connection connection, Date date) throws Exception {
    Tige reponse = null;
    String query = "SELECT hauteur, couleur, bourgeons, nombre FROM etude WHERE date = '" + date + "' AND parcelle = '"
        + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse = new Tige(result.getDouble("hauteur"), result.getInt("couleur"), result.getInt("bourgeons"),
          result.getInt("nombre"));
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public Paysan trouverPaysan(Connection connection) throws Exception {
    Paysan reponse = null;
    String query = "SELECT * FROM parcellePaysan WHERE parcelle = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse = new Paysan(result.getString("id"), result.getString("nom"), result.getInt("age"));
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public Vector<Parcelle> historique(Connection connection) throws Exception {
    Vector<Parcelle> reponse = new Vector<Parcelle>();
    String query = "SELECT * FROM etude WHERE parcelle = '" + getId() + "' ORDER BY date ASC";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      Parcelle parcelle = new Parcelle(result.getString("parcelle"), getTitre(),
          getSurface());
      Date etude = result.getDate("date");
      parcelle.setEtude(etude);
      Tige tige = parcelle.trouverTige(connection, etude);
      parcelle.setTige(tige);
      reponse.add(parcelle);
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public Terrain trouverTerrain(Connection connection) throws Exception {
    Terrain reponse = null;
    String query = "SELECT terrain, titreTerrain, surfaceTerrain FROM parcelleTerrain WHERE id = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      reponse = new Terrain(resultSet.getString("terrain"), resultSet.getString("titreTerrain"),
          resultSet.getDouble("surfaceTerrain"));
    }
    Connect.fermerRessources(statement, resultSet);

    return reponse;
  }

  public Vector<Annomalie> annomalies(Connection connection) throws Exception {
    Vector<Annomalie> reponse = new Vector<Annomalie>();
    Vector<Parcelle> historique = historique(connection);
    Terrain terrain = trouverTerrain(connection);
    for (int i = 1; i < historique.size(); i++) {
      Parcelle actuelle = historique.get(i);
      Parcelle anterieure = historique.get(i - 1);
      double croissanceMoyenne = terrain.croissanceMoyenne(connection, actuelle.getEtude(), anterieure.getEtude());
      Annomalie annomalie = actuelle.etudeAnnomalie(anterieure, croissanceMoyenne);
      reponse.add(annomalie);
    }

    return reponse;
  }

  public void examinerEngrais(Vector<Engrais> engrais) {
    double total = 0;
    for (Engrais engrais2 : engrais) {
      total += engrais2.getQuantite();
    }
    for (Engrais engrais2 : engrais) {
      engrais2.setTaux(engrais2.getQuantite() / total);
    }
  }

  public void examinerEngrais(Connection connection) throws Exception {
    double recolte = poidsRecolte(connection);
    for (Engrais engrais2 : getEngrais()) {
      engrais2.setProduction(engrais2.getTaux() * recolte);
    }
  }

  public Vector<Engrais> trouverEngrais(Connection connection) throws Exception {
    Vector<Engrais> reponse = new Vector<Engrais>();
    String query = "SELECT * FROM poidsTotalEngrais WHERE parcelle = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse.add(new Engrais(connection, result.getInt("engrais"), result.getDouble(2)));
    }
    Connect.fermerRessources(statement, result);
    examinerEngrais(reponse);

    return reponse;
  }

  public double poidsRecolte(double poidsBourgeon) {
    return nombreTotalBourgeons() * poidsBourgeon;
  }

  public double poidsRecolte(Connection connection) throws Exception {
    double reponse = 0;
    String query = "SELECT poids FROM recolte WHERE parcelle = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse = result.getDouble("poids");
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public double poidsRecolteAdditif(Connection connection) throws Exception {
    double poids = poidsRecolte(connection);
    double reponse = 0;
    for (Engrais engrais : getEngrais()) {
      double taux = engrais.getTaux();
      double production = (poids * taux) + (poids * taux * engrais.trouverEffet(connection)) / 100;
      engrais.setProduction(production);
      reponse += production;
    }

    return reponse;
  }

  public double poidsBourgeon(Connection connection) throws Exception {
    double reponse = 0;
    double poidsRecolte = poidsRecolte(connection);
    double nombre = nombreTotalBourgeons();
    reponse = poidsRecolte / nombre;

    return reponse;
  }

  public void productionEngrais(Connection connection, double poidsBourgeon) throws Exception {
    setEngrais(trouverEngrais(connection));
    double poids = poidsRecolte(poidsBourgeon);
    for (Engrais engrais2 : engrais) {
      engrais2.setProduction(engrais2.getTaux() * poids);
    }
  }

  public boolean hauteurDiminueeAnnomalie(Parcelle anterieure) {
    return (getTige().getHauteur() < anterieure.getTige().getHauteur());
  }

  public boolean bourgeonsAnnomalie(Parcelle anterieure) {
    return (getTige().getBourgeons() < anterieure.getTige().getBourgeons());
  }

  public boolean couleutJaunitAnnomalie(Parcelle anterieure) {
    return (getTige().getCouleur() < anterieure.getTige().getCouleur());
  }

  public boolean couleurNonChangeeAnnomalie(Parcelle anterieure) {
    return (getTige().getCouleur() == anterieure.getTige().getCouleur());
  }

  public boolean tigesAnnomalie(Parcelle anterieure) {
    return (getTige().getNombre() < anterieure.getTige().getNombre());
  }

  public boolean croissanceAnnomalie(Parcelle anterieure, double croissanceMoyenne) {
    double hauteurDemandee = (anterieure.getTige().getHauteur() * croissanceMoyenne) / 100;
    if (getTige().getHauteur() < hauteurDemandee) {
      return true;
    }
    return false;
  }

  public Annomalie etudeAnnomalie(Parcelle anterieure, double croissanceMoyenne) {
    Annomalie reponse = new Annomalie();
    reponse.setDate(getEtude());

    if (hauteurDiminueeAnnomalie(anterieure)) {
      reponse.ajouter(1);
    }
    if (bourgeonsAnnomalie(anterieure)) {
      reponse.ajouter(2);
    }
    if (couleutJaunitAnnomalie(anterieure)) {
      reponse.ajouter(3);
    }
    if (couleurNonChangeeAnnomalie(anterieure)) {
      reponse.ajouter(4);
    }
    if (tigesAnnomalie(anterieure)) {
      reponse.ajouter(5);
    }
    if (croissanceAnnomalie(anterieure, croissanceMoyenne)) {
      reponse.ajouter(6);
    }
    reponse.finaliser();
    return reponse;
  }

  public Tige getTige() {
    return tige;
  }

  public void setTige(Tige tige) {
    this.tige = tige;
  }

  public Paysan getPaysan() {
    return paysan;
  }

  public void setPaysan(Paysan paysan) {
    this.paysan = paysan;
  }

  public Date getEtude() {
    return etude;
  }

  public void setEtude(Date etude) {
    this.etude = etude;
  }

  public Vector<Engrais> getEngrais() {
    return engrais;
  }

  public void setEngrais(Vector<Engrais> engrais) {
    this.engrais = engrais;
  }
}
