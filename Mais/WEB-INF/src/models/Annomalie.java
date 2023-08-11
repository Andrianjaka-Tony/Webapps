package models;

import java.sql.Date;
import java.util.Vector;

public class Annomalie {
  Vector<Integer> typeAnnomalies;
  Vector<String> descriptions;
  Date date;

  public Annomalie() {
    typeAnnomalies = new Vector<Integer>();
    descriptions = new Vector<String>();
  }

  public void ajouter(Integer a) {
    getTypeAnnomalies().add(a);
  }

  public void finaliser() {
    for (Integer typeAnnomalie : getTypeAnnomalies()) {
      getDescriptions().add(nomAnnomalie(typeAnnomalie));
    }
    if (getDescriptions().size() == 0) {
      getDescriptions().add("Aucune annomalie detectee");
    }
  }

  public String nomAnnomalie(Integer typeAnnomalie) {
    String reponse = "";
    if (typeAnnomalie == 1) {
      reponse = "La hauteur de tiges a diminue";
    } else if (typeAnnomalie == 2) {
      reponse = "Le nombre moyen de bourgeons par tige a diminue";
    } else if (typeAnnomalie == 3) {
      reponse = "La couleur a jaunit";
    } else if (typeAnnomalie == 4) {
      reponse = "La couleur n'a pas change";
    } else if (typeAnnomalie == 5) {
      reponse = "Le nombre de tiges plantees a diminue";
    } else if (typeAnnomalie == 6) {
      reponse = "La croissance des tiges est anormale";
    }
    return reponse;
  }

  public Vector<Integer> getTypeAnnomalies() {
    return typeAnnomalies;
  }

  public void setTypeAnnomalie(Vector<Integer> typeAnnomalies) {
    this.typeAnnomalies = typeAnnomalies;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Vector<String> getDescriptions() {
    return descriptions;
  }
}
