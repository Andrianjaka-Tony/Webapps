package exceptions;

public class NombreTigeException extends Exception {
  public static final int min = 0;

  public NombreTigeException() {
    super("Nombre de tiges negatif");
  }
}
