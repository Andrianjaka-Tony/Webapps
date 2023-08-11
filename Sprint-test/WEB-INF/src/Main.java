import models.Employe;
import models.TrajetVoiture;
import models.Voiture;

public class Main {
  public static void main(String[] args) {
    TrajetVoiture v = new TrajetVoiture();
    System.out.println(v.findByVoiture(3));
  }
}
