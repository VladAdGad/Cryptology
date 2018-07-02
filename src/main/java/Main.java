import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

  public static void main(String[] args) throws IOException {
    String key = "Bar12345Bar12345"; // 128 bit key
    String initVector = "RandomInitVector"; // 16 bytes IV

    String textToEncrypt = File.readFile("/home/vlad/IdeaProjects/Cryptology/src/main/resources/file.txt", Charset.defaultCharset());
    double start, end;
    final int quantity = 100;
    int count = quantity;

    System.out.println(AES.decrypt(key, initVector, AES.encrypt(key, initVector, "AION")));

    start = System.currentTimeMillis();
    while (count-- > 0) {
      AES.encrypt(key, initVector, textToEncrypt);
    }
    end = System.currentTimeMillis();

    System.out.println("Average of encrypt: " + (end - start) / quantity);


    count = quantity;
    String encrypted = AES.encrypt(key, initVector, textToEncrypt);

    start = System.currentTimeMillis();
    while (count-- > 0) {
      AES.decrypt(key, initVector, encrypted);
    }
    end = System.currentTimeMillis();

    System.out.println("Average of decrypt: " + (end - start) / quantity);
  }

}
