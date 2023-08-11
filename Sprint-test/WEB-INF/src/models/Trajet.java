package models;

import java.lang.reflect.Field;
import java.util.Vector;

import anno.Column;
import anno.Insert;
import anno.PK;
import anno.Table;
import annotation.Url;
import build.DAO;
import use.ModelView;

@Table("trajet")
public class Trajet extends DAO {

  @Column
  @PK
  private Integer id;

  @Column
  @Insert
  private String depart;

  @Column
  @Insert
  private String arrivee;

  @Column
  @Insert
  private Integer voiture;

  @Column
  @Insert
  private Integer chauffeur;

  @Column
  @Insert
  private String date_depart;

  @Column
  @Insert
  private String heure_depart;

  @Column
  @Insert
  private Integer etat;

  public Trajet() {
  }

  public Vector<Object> trajetNonValides() {
    Vector<Object> reponse = new Vector<Object>();
    Vector<Object> find = this.findALl();
    for (Object f : find) {
      try {
        Field champ = f.getClass().getDeclaredField("etat");
        champ.setAccessible(true);
        String etat = String.valueOf(champ.get(f));
        if (etat.equals("1")) {
          reponse.add(f);
        }
      } catch (Exception e) {
      }
    }

    return reponse;
  }

  @Url("/trajet/list")
  public ModelView listInvalid() {
    ModelView response = new ModelView();
    response.addItem("trajet", this.trajetNonValides());
    response.setViewName("/trajet-list.jsp");

    return response;
  }

  @Url("/trajet/form")
  public ModelView form() {
    ModelView response = new ModelView();
    response.setViewName("/trajet-insert-form.jsp");

    Chauffeur chauffeur = new Chauffeur();
    Voiture voiture = new Voiture();

    response.addItem("chauffeur", chauffeur.findALl());
    response.addItem("voiture", voiture.findALl());

    return response;
  }

  @Url("/trajet/save")
  public ModelView save() {
    return this.form();
  }

  @Url("/trajet/findAll")
  public ModelView findAll() {
    ModelView response = new ModelView();
    response.setViewName("/trajet-find-all.jsp");

    response.addItem("trajet", this.findALl());

    return response;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDepart() {
    return depart;
  }

  public void setDepart(String depart) {
    this.depart = depart;
  }

  public String getArrivee() {
    return arrivee;
  }

  public void setArrivee(String arrivee) {
    this.arrivee = arrivee;
  }

  public Integer getVoiture() {
    return voiture;
  }

  public void setVoiture(Integer voiture) {
    this.voiture = voiture;
  }

  public Integer getChauffeur() {
    return chauffeur;
  }

  public void setChauffeur(Integer chauffeur) {
    this.chauffeur = chauffeur;
  }

  public String getDate_depart() {
    return date_depart;
  }

  public void setDate_depart(String date_depart) {
    this.date_depart = date_depart;
  }

  public String getHeure_depart() {
    return heure_depart;
  }

  public void setHeure_depart(String heure_depart) {
    this.heure_depart = heure_depart;
  }

  public Integer getEtat() {
    return etat;
  }

  public void setEtat(Integer etat) {
    this.etat = etat;
  }
}
