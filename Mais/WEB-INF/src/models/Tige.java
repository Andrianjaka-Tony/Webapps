package models;

import exceptions.BourgeonException;
import exceptions.CouleurException;
import exceptions.HauteurException;
import exceptions.NombreTigeException;

public class Tige {
  private double hauteur;
  private int couleur;
  private int bourgeons;
  private int nombre;

  public Tige() {
  }

  public Tige(double hauteur, int couleur, int bourgeons, int nombre)
      throws HauteurException, CouleurException, BourgeonException, NombreTigeException {
    setHauteur(hauteur);
    setCouleur(couleur);
    setBourgeons(bourgeons);
    setNombre(nombre);
  }

  public double getHauteur() {
    return hauteur;
  }

  public void setHauteur(double hauteur) throws HauteurException {
    if (hauteur < HauteurException.min) {
      throw new HauteurException();
    }
    this.hauteur = hauteur;
  }

  public int getCouleur() {
    return couleur;
  }

  public void setCouleur(int couleur) throws CouleurException {
    if (couleur < CouleurException.min || couleur > CouleurException.max) {
      throw new CouleurException();
    }
    this.couleur = couleur;
  }

  public int getBourgeons() {
    return bourgeons;
  }

  public void setBourgeons(int bourgeons) throws BourgeonException {
    if (bourgeons < BourgeonException.min) {
      throw new BourgeonException();
    }
    this.bourgeons = bourgeons;
  }

  public int getNombre() {
    return nombre;
  }

  public void setNombre(int nombre) throws NombreTigeException {
    if (nombre < 0) {
      throw new NombreTigeException();
    }
    this.nombre = nombre;
  }
}
