package exceptions;

public class QuantiteException extends Exception {
  public static int min = 0;

  public QuantiteException() {
    super("La quantite d'engrais est negative");
  }
}
