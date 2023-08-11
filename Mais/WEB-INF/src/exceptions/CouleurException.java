package exceptions;

public class CouleurException extends Exception {
  public final static int min = 0;
  public final static int max = 100;

  public CouleurException() {
    super("La valeur de la couleur doit etre comprise entre " + CouleurException.min + " et " + CouleurException.max);
  }
}
