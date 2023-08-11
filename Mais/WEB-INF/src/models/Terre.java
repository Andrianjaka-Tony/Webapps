package models;

import exceptions.SurfaceException;

public class Terre {
  private String id;
  private String titre;
  private double surface;

  public Terre() {
  }

  public Terre(String id, String titre, double surface) throws SurfaceException {
    setSurface(surface);
    setId(id);
    setTitre(titre);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public double getSurface() {
    return surface;
  }

  public void setSurface(double surface) throws SurfaceException {
    if (surface < SurfaceException.min) {
      throw new SurfaceException();
    }
    this.surface = surface;
  }
}
