package models;

import anno.Column;
import anno.PK;
import anno.Table;
import build.DAO;

@Table("chauffeur")
public class Chauffeur extends DAO {

  @Column
  @PK
  private Integer id;

  @Column
  private String nom;

  @Column
  private String prenom;

  public Chauffeur() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
}
