package exceptions;

public class HauteurException extends Exception {
  public final static int min = 0;

  public HauteurException() {
    super("Hauteur negative");
  }
}
