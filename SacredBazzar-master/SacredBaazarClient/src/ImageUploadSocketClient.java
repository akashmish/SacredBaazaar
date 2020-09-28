
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
 public class ImageUploadSocketClient {
   public static void main(String[] args) throws Exception {
       try (Socket socket = new Socket("192.168.43.149",7000)) {
           OutputStream outputStream = socket.getOutputStream();
           BufferedImage image = ImageIO.read(new File("C:\\Users\\user\\Downloads\\colorful_papers_hd-1366x768.jpg"));
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           ImageIO.write(image, "jpg", byteArrayOutputStream);
           byte[] size = ByteBuffer.allocate(10000).putInt(byteArrayOutputStream.size()).array(); 
           outputStream.write(size);
           outputStream.write(byteArrayOutputStream.toByteArray());
           outputStream.flush();
       }
  }
}
