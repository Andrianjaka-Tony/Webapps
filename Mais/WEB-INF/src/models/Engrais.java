package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import exceptions.QuantiteException;
import helpers.Connect;

public class Engrais {
  private int id;
  private String nom;
  private double quantite;
  private double taux;
  private double production;
  private double prix;

  public Engrais() {
  }

  public Engrais(int id, String nom, double quantite, double production) throws Exception {
    setId(id);
    setNom(nom);
    setQuantite(quantite);
    setProduction(production);
  }

  public Engrais(Connection connection, int id, double quantite) throws Exception {
    setId(id);
    setQuantite(quantite);
    setNom(connection);
  }

  public void setNom(Connection connection) throws Exception {
    String query = "SELECT nom FROM engrais WHERE id = '" + getId() + "'";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      this.nom = result.getString("nom");
    }
    Connect.fermerRessources(statement, result);
  }

  public void setPrix(Connection oracle) throws Exception {
    String query = "SELECT prix FROM prixEngrais WHERE engrais = '" + getId() + "'";
    Statement statement = oracle.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      setPrix(result.getDouble("prix"));
    }
    Connect.fermerRessources(statement, result);
  }

  public int trouverEffet(Connection postgres) throws Exception {
    int reponse = 0;
    String taux = String.valueOf(getTaux() * 100);
    taux = taux.replace(".0", "");
    String query = "SELECT effet FROM effetAdditif WHERE engrais = '" + getId() + "' AND min <= '" + (taux)
        + "' AND max >= '" + taux + "'";
    Statement statement = postgres.createStatement();
    ResultSet result = statement.executeQuery(query);
    while (result.next()) {
      reponse = result.getInt("effet");
    }
    Connect.fermerRessources(statement, result);

    return reponse;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public double getQuantite() {
    return quantite;
  }

  public void setQuantite(double quantite) throws Exception {
    if (quantite < QuantiteException.min) {
      throw new QuantiteException();
    }
    this.quantite = quantite;
  }

  public double getTaux() {
    return taux;
  }

  public void setTaux(double taux) {
    this.taux = taux;
  }

  public double getProduction() {
    return production;
  }

  public void setProduction(double production) {
    this.production = production;
  }

  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }
}
