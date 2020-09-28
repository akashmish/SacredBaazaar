/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author user
 */
   public class SignupCon {
    
        private Statement st=null;
        private int status=0;
        private String str;
    
    
    
   public static void main(String args[])
   {
        SignupCon object=new SignupCon();
        //Object Of Connection Class
   }
   
   
    void InsertIntoRetailer(String name,String email,String password,String contact)
   {
         //inserts data into retailer table
        try{
          
        SignupCon pll=new SignupCon();
        String sql="insert into retailer (name,email,password,contact) values('"+name+"','"+email+"','"+password+"','"+contact+"')";
        st.executeUpdate(sql);
           }catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
   }
    
   
   int getStatus(String email,String pass) 
   {
        //check login credentails of user
        str=null;
     try{
            
                String sql="SELECT * FROM userinfo where email='"+email+"' ";
                ResultSet rs=st.executeQuery(sql);
           
            
        while(rs.next())
            {
                str=rs.getString(3);
                System.out.println(str);
                String str2=rs.getString(1);
                System.out.println(str2);
                str2+=" YOU HAVE SUCCESSFULLY LOGGED IN";
                
                if(str.equals(pass))
                {
                    JOptionPane.showMessageDialog(null,str2);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"YOU HAVE ENTERED THE WRONGE CREDENTIALS");
                    return 0;
                }
            }
        }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                             }
      
                if(str==null)
                {
                    return 0;}
                else
                {
                    return 1;
                }
   }
   
   int getstatusofretailer(String email,String pass)
   {//This functions get the details of retailer and check when retailer tries to log in
            str=null;
        try{
            
            String sql="SELECT * FROM retailer where email='"+email+"' ";
            ResultSet rs=st.executeQuery(sql);
           
             while(rs.next())
            {
                
                
                str=rs.getString(3);
                String str2=rs.getString(1);
                str2+=" YOU HAVE SUCCESSFULLY LOGGED IN";
                
                if(str.equals(pass))
                {
                    JOptionPane.showMessageDialog(null,str2);
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"YOU HAVE ENTERED THE WRONGE CREDENTIALS");
                    return 0;
                }
            }
            
            
                
            
        } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
        }
      
                if(str==null)
               {
                    return 0;
               }
               else
               {
                    return 1;
               }
   }
   
   void deleteProduct(String pid) throws SQLException
   {
            String sql="delete from product where pid='"+pid+"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"deleted");
       
   }
   
   
   
  void acc(String name,String email,String password,String contact,String pincode,String address)
   {//Inserts value to user table or client table
      try {
            SignupCon pll=new SignupCon();
            String sql="insert into userinfo (name,email,password,contact,pincode,address) values('"+name+"','"+email+"','"+password+"','"+contact+"','"+pincode+"','"+address+"')";
            st.executeUpdate(sql);
          }  catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
                                 }
   }
  
  
  
  String getName(String email) throws SQLException
  {
            //Returns vlaue of name of user
            String sql="SELECT * FROM userinfo where email='"+email+"' ";
            ResultSet rs=st.executeQuery(sql);
            String s=rs.getString(1);
            return s;
  }
  
  
   void getProductUpdate(String email,String pid,String pname,String type1,String type2,String price,String fpath,String brand,String size,String date,String offerdisc,String author,String colour,String features,String warranty) throws SQLException
   {
            //insert product to the product table
            String sql="insert into product (email,pid,pname,type1,type2,price,image,brand,size,dateofmfg,offerdis,author,colour,extrafeature,warranty) values('"+email+"','"+pid+"','"+pname+"','"+type1+"','"+type2+"','"+price+"','"+fpath+"','"+brand+"','"+size+"','"+date+"','"+offerdisc+"','"+author+"','"+colour+"','"+features+"','"+warranty+"') ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"PRODUCT ADD HO GYA TERA LAVDEYAA");
        
   }
   
   
   
   public Connection still_connecting(){ 
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sacred","root","");
            System.out.println(con);
            return con;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
   
   
   
  
   void getImage() throws SQLException
   {
            System.out.println("Image: 2");
            String sql="Select * From PRODUCT";
            System.out.println("Image: 3");
            ResultSet rs=st.executeQuery(sql);
            System.out.println("Image: 4");
            rs.next();
            String s=rs.getString(7);
            System.out.println("Image: 5"+s);
       
            System.out.println("Image: 6");
   }
    public SignupCon(){
       
            String databaseURL="jdbc:mysql://localhost:3306/sacred";
            String user="root";
            Connection conn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL,"root",""); 
            st=conn.createStatement();
            
            if(conn!=null)
            {
                System.out.println("connected");
            }
        }
        catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
        
    }
   
    
}
