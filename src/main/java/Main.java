import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

  public static void main(String[] args) throws IOException {
    String key = "Bar12345Bar12345"; // 128 bit key
    String initVector = "RandomInitVector"; // 16 bytes IV

    String textToEncrypt = File.readFile("/home/vlad/IdeaProjects/Cryptology/src/main/resources/file.txt", Charset.defaultCharset());
    double start, end;
    final int quantity = 1;
    int count = quantity;

    System.out.println(Cryptology.decrypt(key, initVector, textToEncrypt));

    start = System.currentTimeMillis();
    while (count-- > 0) {
      Cryptology.encrypt(key, initVector, textToEncrypt);
    }
    end = System.currentTimeMillis();

    System.out.println("Average of encrypt: " + (end - start) / quantity);


    count = quantity;
    String encrypted = Cryptology.encrypt(key, initVector, textToEncrypt);

    start = System.currentTimeMillis();
    while (count-- > 0) {
      Cryptology.decrypt(key, initVector, encrypted);
    }
    end = System.currentTimeMillis();

    System.out.println("Average of decrypt: " + (end - start) / quantity);
  }

}
