import enums.Algorithm;
import enums.BlockCipher;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {
  private final static SecureRandom random = new SecureRandom();
  private final static byte[] key = "Bar12345Bar12345".getBytes(StandardCharsets.UTF_8); // 128 bit key
  private final static byte[] initVector16 = random.generateSeed(16); // 16 bytes IV
  private final static byte[] initVector8 = random.generateSeed(8); // 8 bytes IV
  private final static byte[] value = new byte[50000000]; // 50mb

  private final static String algorithm = Algorithm.AES.toString();
  private final static IvParameterSpec iv = new IvParameterSpec(initVector16);
  private final static SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);

  private final static int quantity = 100;
  private static int count = quantity;

  public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    /*AlgorithmProcess*/
    long startEncrypt, endEncrypt;
    long startDecrypt, endDecrypt;

    AlgorithmProcess.changeCipher(Algorithm.AES, BlockCipher.CBC); //change params

    AlgorithmProcess.encrypt(iv, skeySpec, value); // warmup phase

    startEncrypt = System.nanoTime();
    while (count-- > 0) {
      AlgorithmProcess.encrypt(iv, skeySpec, value);
    }
    endEncrypt = System.nanoTime();


    count = quantity;
    byte[] encrypted = AlgorithmProcess.getEncrypt(iv, skeySpec, value);
    AlgorithmProcess.decrypt(iv, skeySpec, encrypted); // warmup phase

    startDecrypt = System.nanoTime();
    while (count-- > 0) {
      AlgorithmProcess.decrypt(iv, skeySpec, encrypted);
    }
    endDecrypt = System.nanoTime();

    System.out.println("Average of encrypt: " + (endEncrypt - startEncrypt) / quantity);
    System.out.println("Average of decrypt: " + (endDecrypt - startDecrypt) / quantity);
  }

}
