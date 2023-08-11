package models;

import anno.Column;
import anno.PK;
import anno.Table;
import annotation.RestAPI;
import annotation.Url;
import build.DAO;
import use.JSON;

@Table("b")
public class TrajetVoiture extends DAO {

  @Column
  private String modele;

  @Column
  private String depart;

  @Column
  private String arrivee;

  @Column
  private String date_depart;

  @Column
  private String heure_depart;

  @Column
  private Integer id;

  @Column
  @PK
  private Integer voiture;

  @Column
  private Integer chauffeur;

  @Column
  private Integer etat;

  @Url("/trajet/voiture")
  @RestAPI
  public String findByVoiture(Integer voiture) {
    this.setVoiture(voiture);
    return JSON.stringify(this.findByIdMultiple());
  }

  public String getModele() {
    return modele;
  }

  public void setModele(String modele) {
    this.modele = modele;
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Integer getEtat() {
    return etat;
  }

  public void setEtat(Integer etat) {
    this.etat = etat;
  }

}
