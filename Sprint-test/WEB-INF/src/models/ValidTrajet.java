package models;

import anno.Column;
import anno.Insert;
import anno.Table;
import annotation.Auth;
import annotation.Url;
import build.DAO;
import use.ModelView;

@Table("validTrajet")
public class ValidTrajet extends DAO {

  @Column
  @Insert
  private Integer trajet;

  @Column
  @Insert
  private String date_arrivee;

  @Column
  @Insert
  private String heure_arrivee;

  public ValidTrajet() {
  }

  @Url("/profil")
  public ModelView connect() {
    ModelView response = new ModelView();
    response.setViewName("/index.jsp");
    response.addSessions("profil", "admin");

    return response;
  }

  @Url("/trajet/validate")
  @Auth(session = "profil", value = "admin")
  public ModelView validate(Integer trajet, String date_arrivee, String heure_depart) {
    Trajet t = new Trajet();
    t.setId(trajet);
    t.update("etat", "2");
    return t.listInvalid();
  }

  public Integer getTrajet() {
    return trajet;
  }

  public void setTrajet(Integer trajet) {
    this.trajet = trajet;
  }

  public String getDate_arrivee() {
    return date_arrivee;
  }

  public void setDate_arrivee(String date_arrivee) {
    this.date_arrivee = date_arrivee;
  }

  public String getHeure_arrivee() {
    return heure_arrivee;
  }

  public void setHeure_arrivee(String heure_arrivee) {
    this.heure_arrivee = heure_arrivee;
  }
}
