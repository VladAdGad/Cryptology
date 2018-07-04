import enums.BlockCipher;
import enums.Padding;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

class AES {
  private static String algorithm = "AES";
  private static Cipher cipher;

  static {
    try {
      cipher = Cipher.getInstance(String.format("%s/%s/%s", algorithm, BlockCipher.CBC, Padding.PKCS5PADDING));
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      e.printStackTrace();
    }
  }

  static byte[] encrypt(IvParameterSpec iv, SecretKeySpec skeySpec, byte[] value) {
    try {
//      cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

      return cipher.doFinal(value);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

  static byte[] decrypt(IvParameterSpec iv, SecretKeySpec skeySpec, byte[] value) {
    try {
//      cipher.init(Cipher.DECRYPT_MODE, skeySpec);
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

      return cipher.doFinal(value);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

}
