package models;

import exceptions.AgeException;

public class Paysan {
  private String id;
  private String nom;
  private int age;

  public Paysan() {
  }

  public Paysan(String id, String nom, int age) throws AgeException {
    setId(id);
    setNom(nom);
    setAge(age);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) throws AgeException {
    if (age < AgeException.min || age > AgeException.max) {
      throw new AgeException();
    }
    this.age = age;
  }
}
