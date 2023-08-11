package exceptions;

public class BourgeonException extends Exception {
  public final static int min = 0;

  public BourgeonException() {
    super("Nombre de bourgeons negatif");
  }
}
