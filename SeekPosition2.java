import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;


public class SeekPosition2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int position;
    int numChars;

    System.out.println("Enter a position as an int: ");
    position = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter how many characters you want to display: ");
    numChars = scanner.nextInt();
    scanner.nextLine();

    // save the file path to filePath variable
    Path filePath = Paths.get("words.txt");


    // try with resources so that file
    // is closed automatically
    try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(filePath, READ)){
      // create a ByteBuffer of x numbers bytes since we want to read x characters of 1 byte each
      ByteBuffer byteBuffer = ByteBuffer.allocate(numChars);

      // set the position in the file to the position entered by the user
      fileChannel.position(position);

      // read the bytes from the file into the ByteBuffer
      // from the position entered by the user
      // and assign the number of bytes read to bytesRead
      int bytesRead = fileChannel.read(byteBuffer);

      // rewind the position of ByteBuffer to the beginning
      // so that we read bytes from the beginning
      byteBuffer.rewind();

      // loop through the bytes buffer
      for (int i = 0; i < bytesRead; i++) {
        // typecast the byte to a char and print it
        System.out.print((char) byteBuffer.get());
      }
    System.out.println("");
    }
    catch (Exception e) {
        System.out.println("Message: " + e);
    }
  }
}
