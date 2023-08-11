package models;

import anno.Column;
import anno.PK;
import anno.Table;
import build.DAO;

@Table("voiture")
public class Voiture extends DAO {

  @Column
  @PK
  private Integer id;

  @Column
  private String modele;

  public Voiture() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getModele() {
    return modele;
  }

  public void setModele(String modele) {
    this.modele = modele;
  }
}
