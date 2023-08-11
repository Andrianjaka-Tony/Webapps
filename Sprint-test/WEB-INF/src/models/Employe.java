package models;

import java.sql.Date;

import anno.Column;
import anno.Insert;
import anno.PK;
import anno.Table;
import annotation.Auth;
import annotation.RestAPI;
import annotation.Url;
import build.DAO;
import use.JSON;
import use.ModelView;

@Table("employe")
public class Employe extends DAO {

  @Column
  @PK
  private Integer id;

  @Column
  @Insert
  private String name;

  @Column
  @Insert
  private Date birthDate;

  public Employe() {
  }

  @Url("/employe/find-all/json")
  @RestAPI
  public String employeJSON() {
    String response = JSON.stringify(this.findALl());
    return response;
  }

  @Url("/employe/insert/form")
  @Auth(session = "user", value = "employe-manager")
  public ModelView employeForm() {
    ModelView response = new ModelView();
    response.setViewName("/employe-form.jsp");

    return response;
  }

  @Url("/employe/insert")
  public ModelView save() {
    ModelView response = new ModelView();
    response.setViewName("/employe-form.jsp");

    return response;
  }

  @Url("/employe/find-by-id")
  public ModelView employeFind(Integer id) {
    this.setId(id);
    ModelView response = new ModelView();
    response.setJson(true);
    response.addItem("data", this.findById());
    response.addSessions("user", "employe-manager");

    return response;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

}
