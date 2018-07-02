import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import utility.File;

public class Main {

  public static void main(String[] args) throws Exception {
    String key = "Bar12345Bar12345"; // 128 bit key
    String initVector16 = "RandomInitVector"; // 16 bytes IV
    SecureRandom random = new SecureRandom();
    byte[] initVector8 = random.generateSeed(8);
//    String initVector8 = iv.toString(); // 8 bytes IV

    String textToEncrypt = File.readFile("/home/vlad/IdeaProjects/Cryptology/src/main/resources/file.txt", Charset.defaultCharset());
    double start, end;
    final int quantity = 100;
    int count = quantity;

//    System.out.println(AES.decrypt(key, initVector16, AES.encrypt(key, initVector16, "AION")));
//
//    start = System.currentTimeMillis();
//    while (count-- > 0) {
//      AES.encrypt(key, initVector16, textToEncrypt);
//    }
//    end = System.currentTimeMillis();
//
//    System.out.println("Average of encrypt: " + (end - start) / quantity);
//
//
//    count = quantity;
//    String encrypted = AES.encrypt(key, initVector16, textToEncrypt);
//
//    start = System.currentTimeMillis();
//    while (count-- > 0) {
//      AES.decrypt(key, initVector16, encrypted);
//    }
//    end = System.currentTimeMillis();
//
//    System.out.println("Average of decrypt: " + (end - start) / quantity);


    /*BLOWFISH*/
    System.out.println(BlowFish.decrypt(key, initVector8, BlowFish.encrypt(key, initVector8, "AION")));

    start = System.currentTimeMillis();
    while (count-- > 0) {
      BlowFish.encrypt(key, initVector8, textToEncrypt);
    }
    end = System.currentTimeMillis();

    System.out.println("Average of encrypt: " + (end - start) / quantity);


    count = quantity;
    String encrypted = BlowFish.encrypt(key, initVector8, textToEncrypt);

    start = System.currentTimeMillis();
    while (count-- > 0) {
      BlowFish.decrypt(key, initVector8, encrypted);
    }
    end = System.currentTimeMillis();

    System.out.println("Average of decrypt: " + (end - start) / quantity);


  }

}
