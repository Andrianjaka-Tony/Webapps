package model;

import java.util.HashMap;

import annotation.Auth;
import annotation.RestAPI;
import annotation.Scope;
import annotation.Url;
import etu1765.framework.FileUpload;
import use.JSON;
import use.ModelView;

@Scope(value = "singleton")
public class Emp {
  private String name;
  private Integer age;
  private FileUpload image;
  private HashMap<String, Object> session;

  public Emp() {
  }

  public Emp(String name, Integer age, FileUpload file) {
    this.name = name;
    this.age = age;
    this.image = file;
  }

  @Url(value = "/Emp")
  public ModelView findAll() {
    ModelView reponse = new ModelView();
    reponse.setViewName("/emp-findAll.jsp");
    reponse.setJson(true);

    reponse.addItem("Emp1", new Emp("Hello", 30, null));
    reponse.addItem("Emp2", new Emp("Dazai", 35, null));

    reponse.addSessions("Profil", "Admin");
    return reponse;
  }

  @Url(value = "/Insert")
  @Auth(session = "Profil", value = "Admin")
  public ModelView save() {
    ModelView reponse = new ModelView();
    reponse.setViewName("/emp-findAll.jsp");

    return reponse;
  }

  @Url(value = "/dashboard")
  @Auth(session = "Profil", value = "Dashboard")
  public ModelView admin() {
    ModelView reponse = new ModelView();
    reponse.setViewName("/dashboard.jsp");

    return reponse;
  }

  @Url(value = "/Emp/FindById")
  public ModelView findById(Integer id, String name) {
    ModelView reponse = new ModelView();
    reponse.setViewName("/emp-findById.jsp");

    if (id == 0 && name.compareToIgnoreCase("name") == 0) {
      reponse.addItem("Emp1", new Emp("Hello", 30, null));
    } else if (id == 1 && name.compareToIgnoreCase("name") == 0) {
      reponse.addItem("Emp2", new Emp("Dazai", 35, null));
    }

    return reponse;
  }

  @Url(value = "/Emp/JSON")
  @RestAPI
  public String restAPI(Integer id) {
    String reponse = "";
    if (id == 1) {
      Emp emp = new Emp("Hello", 52, null);
      reponse = JSON.stringify(emp);
    } else if (id == 2) {
      Emp emp = new Emp("Boom", 52, null);
      reponse = JSON.stringify(emp);
    }
    return reponse;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public FileUpload getImage() {
    return image;
  }

  public void setImage(FileUpload image) {
    this.image = image;
  }

  public HashMap<String, Object> getSession() {
    return session;
  }

  public void setSession(HashMap<String, Object> session) {
    this.session = session;
  }

}
