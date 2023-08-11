package helpers;

import org.codehaus.jackson.map.ObjectMapper;

public class JSON {
  public static String stringify(Object object) {
    String json = "";
    try {
      ObjectMapper mapper = new ObjectMapper();
      json = mapper.writeValueAsString(object);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return json;
  }
}
