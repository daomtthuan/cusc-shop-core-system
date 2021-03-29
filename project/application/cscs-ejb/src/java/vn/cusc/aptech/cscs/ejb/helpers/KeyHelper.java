/*
 * The MIT License
 *
 * Copyright 2021 Daomtthuan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package vn.cusc.aptech.cscs.ejb.helpers;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Daomtthuan
 */
public class KeyHelper {

  private static KeyHelper instance;

  private final SecretKeySpec secretKey;
  private final Cipher cipher;

  private KeyHelper() throws Exception {
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
    secretKey = new SecretKeySpec(Arrays.copyOf(messageDigest.digest("Team 4 EProject".getBytes("UTF-8")), 16), "AES");
    cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
  }

  public String encrypt(String key) throws Exception {
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    return Base64.getEncoder().encodeToString(cipher.doFinal(key.getBytes("UTF-8")));
  }

  public String decrypt(String hashKey) throws Exception {
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    return new String(cipher.doFinal(Base64.getDecoder().decode(hashKey)));
  }

  public static KeyHelper getInstance() throws Exception {
    if (instance == null) {
      instance = new KeyHelper();
    }
    return instance;
  }

}
