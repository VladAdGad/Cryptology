import enums.BlockCipher;
import enums.Padding;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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

  static void encrypt(IvParameterSpec iv, SecretKeySpec skeySpec, byte[] value) throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
//      cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
      cipher.doFinal(value);
  }

  static void decrypt(IvParameterSpec iv, SecretKeySpec skeySpec, byte[] value) throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//      cipher.init(Cipher.DECRYPT_MODE, skeySpec);
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
      cipher.doFinal(value);
  }

  static byte[] getEncrypt(IvParameterSpec iv, SecretKeySpec skeySpec, byte[] value) throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
//      cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

    return cipher.doFinal(value);
  }

  static byte[] getDecrypt(IvParameterSpec iv, SecretKeySpec skeySpec, byte[] value) throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//      cipher.init(Cipher.DECRYPT_MODE, skeySpec);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

    return cipher.doFinal(value);
  }

}
