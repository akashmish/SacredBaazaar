
//package sacredbaazar;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;




public class ImageUploadSocketRunnable implements Runnable{         
  //  public static final String dir="C:\\Users\\Lenovo\\Downloads\\images";
    Socket soc=null;
    DataInputStream  in  = null;
    DataOutputStream out=null;
    
   public ImageUploadSocketRunnable(Socket s,String email,String PID)
    {
        soc=s;
        try{
        in = new DataInputStream(soc.getInputStream());
        out=new DataOutputStream(soc.getOutputStream());
        
           System.out.println("Reading: " + System.currentTimeMillis());
           byte[] sizeAr = new byte[1000];
           in.read(sizeAr);
           int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
           byte[] imageAr = new byte[size];
           in.read(imageAr);
           BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
           System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
           ImageIO.write(image, "jpg", new File("C:\\Users\\user\\Documents\\NetBeansProjects\\SacredBaazarServer\\Retailer\\"+email+"\\"+PID+".jpg"));
        }catch(Exception e)
        {
            System.out.println("Error in image recieving");
        }
    }
    
    
    
   ImageUploadSocketRunnable(Socket soc){
     this.soc=soc;
   }
    @Override
    public void run() {
    InputStream inputStream = null;
       try {
           inputStream = this.soc.getInputStream();
           System.out.println("Reading: " + System.currentTimeMillis());
           byte[] sizeAr = new byte[1000];
           inputStream.read(sizeAr);
           int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
           byte[] imageAr = new byte[size];
           inputStream.read(imageAr);
           BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
           System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
           ImageIO.write(image, "jpg", new File("C:\\Users\\user\\Documents\\NetBeansProjects\\SacredBaazarServer\\Retailer\\aa13.jpg"));
           inputStream.close();
       } catch (IOException ex) {
           
           Logger.getLogger(ImageUploadSocketRunnable.class.getName()).log(Level.SEVERE, null, ex);
       
       }

    }
    
    

    public static void main(String[] args) throws Exception {
       ServerSocket serverSocket = new ServerSocket(7000);
        while(true){
        Socket socket = serverSocket.accept();
        ImageUploadSocketRunnable imgUploadServer=new ImageUploadSocketRunnable(socket);
        Thread thread=new Thread(imgUploadServer);
        thread.start();
        }
    }

}

