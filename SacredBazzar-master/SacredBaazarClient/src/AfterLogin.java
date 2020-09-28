
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class AfterLogin extends javax.swing.JFrame {
    
    private Socket sc;
    private DataOutputStream out=null;
    private DataInputStream in=null;
    
    
    public AfterLogin() {
        initComponents();     
    }
    
    public AfterLogin(Socket soc)
    {
        initComponents();
        this.sc=soc;
        try {
            
            out = new DataOutputStream(soc.getOutputStream());
            in=new DataInputStream(soc.getInputStream());
        
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error in AfterLogin Constructor");
        }
        
        table1.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent me)
        {
             JTable jt=(JTable)me.getSource();
             int row=jt.getSelectedRow();
             int col1=1;
             //int col2=0;
             String pid=(String)jt.getValueAt(row,col1);
             if(me.getClickCount()==2)
             {
                try{    
                     showPro(pid);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error in showPro function");
                }
                  
             }
        }
        });
        
    }
    
    void showPro(String pid) throws IOException
    {
        ProductView obj=new ProductView(this,sc,pid);
        obj.setVisible(true);
    }        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        pname = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        month = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome To SacredBaazar");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 48));

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 53, 181, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Email ID", "PID", "Product", "Type 1", "Type 2", "Price", "Quantity", "Brand", "Size", "Date", "Offer", "Author", "colour", "Features", "Warranty"
            }
        ));
        jScrollPane1.setViewportView(table1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 1020, 350));

        jButton2.setText("Show Product");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 53, 170, -1));

        jButton3.setText("Sort price low to high");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 105, 170, -1));

        jButton4.setText("Sort price high to low");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 105, 181, -1));

        jButton5.setText("View Shopping History");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 158, 170, -1));

        jButton6.setText("Total Amount Spent");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 158, 181, -1));

        jButton7.setText("View Offer for Product");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 212, 170, -1));

        pname.setText("Product Name");
        pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnameActionPerformed(evt);
            }
        });
        getContentPane().add(pname, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 213, 181, -1));

        jButton8.setText("My Offers");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 255, 170, -1));

        jButton9.setText("My intreaction with others");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 255, -1, -1));

        jButton10.setText("Am I among top 5 ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 312, 170, -1));

        month.setText("Month");
        getContentPane().add(month, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 313, 181, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sacredbaazar/AfterLogin.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            //TO return to Welcome page
            out.writeUTF("req@logout");
            Welcome obj=new Welcome();
            this.setVisible(false);
            obj.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(AfterLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try{
       
            out.writeUTF("req@allpshow");
            String n=in.readUTF();
            int num=Integer.parseInt(n);
            for(int i=0;i<num;i++)
            {
                for(int j=0;j<15;j++)
                {
                    String str=in.readUTF();
                    table1.setValueAt(str,i,j);
                }
            } 
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error ");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // To Sort price high to low
        try{
       
            out.writeUTF("req@allphightolow");
            String n=in.readUTF();
            int num=Integer.parseInt(n);
            for(int i=0;i<num;i++)
            {
                for(int j=0;j<15;j++)
                {
                    String str=in.readUTF();
                    table1.setValueAt(str,i,j);
                }
            } 
    
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in sorting(high to low)");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // To Sort price low to high
        try{
       
            out.writeUTF("req@allplowtohigh");
            String n=in.readUTF();
            int num=Integer.parseInt(n);
            for(int i=0;i<num;i++)
            {
                for(int j=0;j<15;j++)
                {
                    String str=in.readUTF();
                    table1.setValueAt(str,i,j);
                }
            } 
    
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in sorting(low to high)");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ShoppingHistory obj=new ShoppingHistory(this,sc);
        obj.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try{
            out.writeUTF("req@amountspent");
            String status=in.readUTF();
            if(status.equals("null"))
                JOptionPane.showMessageDialog(null,"No transaction yet");
            else
            {   
                //String amount=in.readUTF();
                JOptionPane.showMessageDialog(null,"Total amount spent :"+status);
            }
        }
        catch(Exception e)
        {
           JOptionPane.showMessageDialog(null,"Error in amount spent button");

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void pnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnameActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
       
        try{
            out.writeUTF("req@offeronprod");
            String str=pname.getText();
            out.writeUTF(str);
            String status=in.readUTF();
            if(status.equals("yes"))
            {
                JOptionPane.showMessageDialog(null,"Offer Avialable");
                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No Offer Avialable");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Offer check button");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try
        {
            out.writeUTF("req@myoffers");
            String state=in.readUTF();
            
            if(state.equals("notnull"))
            {
                String status=in.readUTF();
                String cost=in.readUTF();
                if(status.equals("yes"))
                {
                    
                    JOptionPane.showMessageDialog(null,"Additional 15% discount as You have spent: "+cost);
                }
                else
                {
                    int scost=Integer.parseInt(cost);
                    scost=100000-scost;
                    JOptionPane.showMessageDialog(null,"No additional discount!! To get additional discount Spend : "+scost);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"To get discount you have to buy products first");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in Additional Offer button");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        try{
            System.out.println("button clicked");
            out.writeUTF("req@retailgraph");
            System.out.println("Request sent");
            String n=in.readUTF();
            System.out.println(n);

            int num=Integer.parseInt(n);
            System.out.println("Number received:"+num);
            String[] str=new String[num];
            
            for(int i=0;i<num;i++)
            {
                String type=in.readUTF();
                str[i]=type;
            }
            String newn=in.readUTF();
            int newnum=Integer.parseInt(newn);
            int[] arr=new int[num];
            for(int i=0;i<newnum;i++)
            {
                String s=in.readUTF();
                if(s.equals(str[0]))
                {
                    arr[0]++;
                }
                else if(s.equals(str[1]))
                {
                    arr[1]++;
                }
                else if(s.equals(str[2]))
                {
                    arr[2]++;
                }
                else if(s.equals(str[3]))
                {
                    arr[3]++;
                }
            }
            
            for(int i=0;i<num;i++)
            {
                dataset.setValue(arr[i],"Product Bought",str[i]);
            }
            JFreeChart chart=ChartFactory.createBarChart("Product Bought","Number of Product bought","Product bought from",dataset);
            CategoryPlot p=chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
            ChartFrame frame=new ChartFrame("Barchart for product bought",chart);
            frame.setVisible(true);
            frame.setSize(600, 450);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in retailer graph ");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try{
            
            String mon=month.getText();
            if(mon.equals(null))
            {
                JOptionPane.showMessageDialog(null,"Enter Month ");
            }
            else{
                out.writeUTF("req@topfive");
                out.writeUTF(mon);
                String status=in.readUTF();
                if(status.equals("yes"))
                {
                    JOptionPane.showMessageDialog(null,"Yes you are in top 5 customer you'll get additional 5% discount");

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You are not in top 5 customer SORRY!!!");                
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in top 5 button");

        }
    }//GEN-LAST:event_jButton10ActionPerformed


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
            java.util.logging.Logger.getLogger(AfterLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AfterLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AfterLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AfterLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AfterLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField month;
    private javax.swing.JTextField pname;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
