package exceptions;

public class SurfaceException extends Exception {
  public final static int min = 0;

  public SurfaceException() {
    super("Surface negative");
  }
}
