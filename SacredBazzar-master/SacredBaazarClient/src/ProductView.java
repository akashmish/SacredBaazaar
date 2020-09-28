
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class ProductView extends javax.swing.JFrame {

    private Socket sc;
    private DataOutputStream out=null;
    private DataInputStream in=null;
    private String pid;
    private InputStream inputStream = null;
    AfterLogin obj=null;
    public ProductView() {
        initComponents();
    }

    public ProductView(AfterLogin af,Socket soc,String pid) throws IOException
    {
        initComponents();
        this.sc=soc;
        this.pid=pid;
        this.obj=af;
        try {
            
            out = new DataOutputStream(soc.getOutputStream());
            in=new DataInputStream(soc.getInputStream());
        
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error in ProductView Constructor");
        }
        out.writeUTF("req@sendimage");
        out.writeUTF(pid);
        String status=in.readUTF();//Server's Response
        if(status.equals("success"))
        {
           inputStream = this.sc.getInputStream();
           System.out.println("Reading: " + System.currentTimeMillis());
           byte[] sizeAr = new byte[1000];
           inputStream.read(sizeAr);
           int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
           byte[] imageAr = new byte[size];
           inputStream.read(imageAr);
           BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
           System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
           ImageIO.write(image, "jpg", new File("C:\\Users\\Lenovo\\Desktop\\SacredBaazar\\"+pid+".jpg"));
           //inputStream.close();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Error in Status"); 
        }
        
        label.setIcon(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\SacredBaazar\\"+pid+".jpg"));
        
        String v_Product=in.readUTF();
        System.out.println(v_Product);
        String v_Category=in.readUTF();
        
        String v_Subcategory=in.readUTF();
        String v_Price=in.readUTF();
        System.out.println(v_Price);
        String v_Brand=in.readUTF();
       // System.out.println(v_Brand);
        String v_Size=in.readUTF();
        String v_Offer=in.readUTF();
        String v_Colour=in.readUTF();
        String v_Feature=in.readUTF();
        String v_Warranty=in.readUTF();
        
       // System.out.println(v_Product);
        
        Productname.setText("Product Name :"+ v_Product);
        Category.setText("Category :"+v_Category);
        Subcat.setText("Sub Category :"+v_Subcategory);
        Price.setText("Price :"+v_Price);
        Brand.setText("Brand :"+v_Brand);
        Size.setText("Size :"+v_Size);
        Offer.setText("Offer (%):"+v_Offer);
        Colour.setText("Colour :"+v_Colour);
        Feature.setText("Extra Features :"+v_Feature);
        Warranty.setText("Warranty :"+v_Warranty);
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Productname = new javax.swing.JLabel();
        Category = new javax.swing.JLabel();
        Subcat = new javax.swing.JLabel();
        Brand = new javax.swing.JLabel();
        Size = new javax.swing.JLabel();
        Offer = new javax.swing.JLabel();
        Colour = new javax.swing.JLabel();
        Feature = new javax.swing.JLabel();
        Warranty = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Quantity = new javax.swing.JTextField();
        Price = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, 153));

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 23, -1, -1));

        Productname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Productname.setForeground(new java.awt.Color(255, 255, 255));
        Productname.setText("Productname");
        getContentPane().add(Productname, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 49, 280, 26));

        Category.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Category.setForeground(new java.awt.Color(255, 255, 255));
        Category.setText("Category");
        getContentPane().add(Category, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 93, 280, 24));

        Subcat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Subcat.setForeground(new java.awt.Color(255, 255, 255));
        Subcat.setText("Sub Category");
        getContentPane().add(Subcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 135, 280, 26));

        Brand.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Brand.setForeground(new java.awt.Color(255, 255, 255));
        Brand.setText("Brand");
        getContentPane().add(Brand, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 222, 280, 24));

        Size.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Size.setForeground(new java.awt.Color(255, 255, 255));
        Size.setText("Size");
        getContentPane().add(Size, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 271, 280, 25));

        Offer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Offer.setForeground(new java.awt.Color(255, 255, 255));
        Offer.setText("Offer");
        getContentPane().add(Offer, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 309, 280, 26));

        Colour.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Colour.setForeground(new java.awt.Color(255, 255, 255));
        Colour.setText("Colour");
        getContentPane().add(Colour, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 353, 280, 27));

        Feature.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Feature.setForeground(new java.awt.Color(255, 255, 255));
        Feature.setText("Extra Feature");
        getContentPane().add(Feature, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 398, 280, 48));

        Warranty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Warranty.setForeground(new java.awt.Color(255, 255, 255));
        Warranty.setText("Warranty");
        getContentPane().add(Warranty, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 453, 280, 28));

        jButton2.setText("Buy Now");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 455, -1, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enter Quantity");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 398, -1, 30));

        Quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuantityActionPerformed(evt);
            }
        });
        getContentPane().add(Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 402, 80, -1));

        Price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Price.setForeground(new java.awt.Color(255, 255, 255));
        Price.setText("Price ");
        getContentPane().add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 179, 280, 25));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sacredbaazar/ProductShow (2).jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       //fterLogin obj=new AfterLogin(sc);
       // obj.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void QuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuantityActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String quantity=Quantity.getText();
        try{
        Bill bl=new Bill(obj,sc,quantity,pid);
        bl.setVisible(true);
        this.setVisible(false);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in Opening Bill Form");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Brand;
    private javax.swing.JLabel Category;
    private javax.swing.JLabel Colour;
    private javax.swing.JLabel Feature;
    private javax.swing.JLabel Offer;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel Productname;
    private javax.swing.JTextField Quantity;
    private javax.swing.JLabel Size;
    private javax.swing.JLabel Subcat;
    private javax.swing.JLabel Warranty;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
