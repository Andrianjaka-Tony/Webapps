package exceptions;

public class AgeException extends Exception {
  public final static int min = 18;
  public final static int max = 55;

  public AgeException() {
    super("L'age doit etre comprise entre " + AgeException.min + " et " + AgeException.max + " ans");
  }
}
