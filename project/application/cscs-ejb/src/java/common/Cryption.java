package common;

import java.util.Base64;

public class Cryption {

  public static String encode(String key) {
    return Base64.getEncoder().encodeToString(key.getBytes());
  }

  public static String decode(String hashKey) {
    return new String(Base64.getDecoder().decode(hashKey));
  }

}
