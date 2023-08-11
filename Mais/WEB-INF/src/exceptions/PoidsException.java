package exceptions;

public class PoidsException extends Exception {
  public static final int min = 0;

  public PoidsException() {
    super("Le poids est negatif");
  }
}
