import enums.BlockCipher;
import enums.Padding;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


public class BlowFish {
  private static String algorithm = "BlowFish";
  private static Base64 base64 = new Base64(true);

  private static Cipher cipher;

  static {
    try {
      cipher = Cipher.getInstance(String.format("%s/%s/%s", algorithm, BlockCipher.PCBC, Padding.PKCS5PADDING));
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      e.printStackTrace();
    }
  }

  static String encrypt(String key, byte[] initVector, String toEncrypt) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(initVector);
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    return base64.encodeToString(cipher.doFinal(toEncrypt.getBytes(StandardCharsets.UTF_8)));
  }

  static String decrypt(String key, byte[] initVector, String toDecrypt) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(initVector);
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    byte[] encryptedData = base64.decodeBase64(toDecrypt);
    byte[] decrypted = cipher.doFinal(encryptedData);
    return new String(decrypted);
  }

  public static String encrypt(String key, String Data)throws Exception{
    SecretKeySpec skeyKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, skeyKey);

    return base64.encodeToString(cipher.doFinal(Data.getBytes(StandardCharsets.UTF_8)));

  }

  //decrypt using blow fish algorithm
  public static String decrypt(String key, String encrypted)throws Exception{
    byte[] encryptedData = base64.decodeBase64(encrypted);
    SecretKeySpec skeyKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.DECRYPT_MODE, skeyKey);
    byte[] decrypted = cipher.doFinal(encryptedData);
    return new String(decrypted);

  }

}
