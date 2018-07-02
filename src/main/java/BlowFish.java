import enums.BlockCipher;
import enums.Padding;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BlowFish {
  private static String algorithm = "BlowFish";

  private static Cipher cipher;

  static {
    try {
      cipher = Cipher.getInstance(String.format("%s/%s/%s", algorithm, BlockCipher.CTR, Padding.PKCS5PADDING));
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      e.printStackTrace();
    }
  }

  static String encrypt(String key, byte[] initVector, String toEncrypt) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(initVector);
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
//    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    byte[] hasil = cipher.doFinal(toEncrypt.getBytes("UTF-8"));
    return new BASE64Encoder().encode(hasil);
  }

  static String decrypt(String key, byte[] initVector, String toDecrypt) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(initVector);
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
//    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    byte[] hasil = cipher.doFinal(new BASE64Decoder().decodeBuffer(toDecrypt));
    return new String(hasil);
  }

}
