import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

/**
 *
 * @author Lenovo
 */


 public class ImageUploadSocketClient {
     
        Socket soc=null;
        DataInputStream  in  = null;
        DataOutputStream out=null;
      public ImageUploadSocketClient(Socket s,String email,String PID)
    {
        
        soc=s;
        
        try{
     
        in = new DataInputStream(soc.getInputStream());
        out=new DataOutputStream(soc.getOutputStream());
        OutputStream outputStream = soc.getOutputStream();
        BufferedImage image = ImageIO.read(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\SacredBaazarServer\\Retailer\\"+email+"\\"+PID+".jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(1000).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        //outputStream.flush();
       
        //out.writeUTF("success");
           
        }catch(Exception e)
        {
            System.out.println("Error in image recieving");
        }
    }
     
     
     
    public static void main(String[] args) throws Exception 
    {/*
        Socket socket = new Socket("192.168.43.149",7000);
        OutputStream outputStream = socket.getOutputStream();
        BufferedImage image = ImageIO.read(new File("C:\\Users\\user\\Pictures\\Saved Pictures\\DSC_0016 (6).jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(1000).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();*/
        
       // soc.close();
  }
} 