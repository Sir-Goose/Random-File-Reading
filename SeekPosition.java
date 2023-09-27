import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class SeekPosition {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter a position as an int: ");
    int position = scanner.nextInt();
    scanner.close();

    // save the file path to filePath object
    Path filePath = Paths.get("words.txt");

    // try with resources so that file is closed automatically
    try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(filePath, READ)) {
      // create a ByteBuffer of 10 bytes since we want to read 10 characters of 1 byte each
      ByteBuffer byteBuffer = ByteBuffer.allocate(10);

      // set position in the file to the position entered by the user
      fileChannel.position(position);

      // read the bytes from the file into the byteBuffer 
      // from the position entered by the user
      // and assign the number of bytes read to bytesRead
      int bytesRead = fileChannel.read(byteBuffer);

      // rewind the position of ByteBuffer to the beginning
      // so that we read the bytes from the beginning 
      byteBuffer.rewind();

      // loop through the byteBuffer
      for (int i = 0; i < bytesRead; i++) {
        // typecast the byte to a char and print it
        System.out.print((char) byteBuffer.get());      
      }
    System.out.println("");

    }
    catch (Exception exception) {
      System.out.println("Message: " + exception);
    }

  }
}

