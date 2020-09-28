
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

// apun ka code lavdeya
class Lis extends Thread
{
    Socket socket=null;
    DataInputStream  in  = null;
    DataOutputStream out=null;
    Tracker tr;
    Connection obj;
    Statement st=null;
    String email="";
    String uemail;
    String remail;
    int additional_disc=0;
    
    void listenM(Tracker t,Socket sc )
    {
        try {
                socket=sc;
                tr=t;
                in = new DataInputStream(socket.getInputStream());
                out=new DataOutputStream(socket.getOutputStream());
                obj=t.getConObj();
                this.start();
        }catch (Exception ex) {
                System.out.println("Error in contructor");
                              }
  
    }
    
    public void run()
    {
        while(true)
        {
            
            try {
                st=obj.createStatement();
                String line;
                line = in.readUTF();
                System.out.println(socket +"herer" +" "+line);
                
                if(line.equals("req@login"))
                {
                   
                    try{
                    
                    email=in.readUTF();
                    uemail=email;
                    String pwd=in.readUTF();
                 
                    String sql="SELECT password from userinfo WHERE email= '"+email+"'";
                    ResultSet rs=st.executeQuery(sql);
                    boolean res=rs.next();
                    
                    if(res)
                    {
                       
                        String s=rs.getString(1);
                        
                        if(s.equals(pwd))
                            out.writeUTF("success");
                        else
                            out.writeUTF("wrong password");
                    }
                    
                    else
                    {    
                        System.out.println("login ka else");
                    }
                    }
                    catch(Exception e)
                    {
                        System.out.println("error mysql");
                    }
                }
                
                else if(line.equals("req@signup"))
                    {
                        
                        String name=in.readUTF();
                        String email=in.readUTF(); 
                        String password=in.readUTF();
                        String contact=in.readUTF();
                        String pincode=in.readUTF();
                        String address=in.readUTF();
                    
                        String head="C:\\Users\\user\\Desktop\\abc";
                       
                        try{
                            ProcessBuilder pb1=new ProcessBuilder("cmd.exe","/C","mkdir \""+email+"\"");
                            pb1.directory(new File(head));
                            Process p1=pb1.start();
                            
                            while(p1.isAlive()); 
                        
                          try
                          {
                                String sql="insert into userinfo (name,email,password,contact,pincode,address) values('"+name+"','"+email+"','"+password+"','"+contact+"','"+pincode+"','"+address+"')";
                                st.executeUpdate(sql);
                                out.writeUTF("success");
                          }
                          catch(Exception e)
                          {  
                              System.out.println("insert nahi ho paya table me data");
                          }
                     
                       }
                    catch(Exception ex)
                        {
                            System.out.println("ERROR IN SIGNUP OR EMAIL ID ALREADY EXIST");
                        }
                    
                }
                
                
                 else if(line.equals("req@rlogin"))
                {
                        String email=in.readUTF();
                        String pass=in.readUTF();
                    System.out.println(email+" "+pass+" of retailer");
                    
                    try{
                         String sql="SELECT password from retailer WHERE email= '"+email+"'";
                         System.out.println("1");
                         ResultSet rs=st.executeQuery(sql);
                         System.out.println("2");
                         boolean res=rs.next();
                         System.out.println(res);

                    if(res)
                    {
                         System.out.println("3");
                         String s=rs.getString(1);
                         System.out.println("4");
                         System.out.println("password ye hai retailer ka --"+s);
                         System.out.println("5");
                        if(s.equals(pass))
                        {
                             System.out.println("6");
                             out.writeUTF("success");
                             System.out.println("7");
                             System.out.println("successul ho gaya ");
                        }
                        
                        else
                        { out.writeUTF("wrong password");}
                    }
                    
                    else
                    {
                    
                        System.out.println("login ka else");
                    }
                    }
                    catch(Exception e)
                    {
                        System.out.println("error mysql");
                    }
                }
                 
                 
                 
                else if(line.equals("req@rsignup"))
                {
                    
                        String head="C:\\Users\\user\\Desktop\\Retailer";
                        String name=in.readUTF();
                        String email=in.readUTF();
                        String password=in.readUTF();
                        String contact=in.readUTF();
                try
                {
                        ProcessBuilder pb1=new ProcessBuilder("cmd.exe","/C","mkdir \""+email+"\"");
                        pb1.directory(new File(head));
                        Process p1=pb1.start();
                            
                        while(p1.isAlive()); 
                        try{
                            String sql="insert into retailer (name,email,password,contact) values('"+name+"','"+email+"','"+password+"','"+contact+"')";
                            st.executeUpdate(sql);
                            out.writeUTF("success");
                            }
                        catch(Exception ex)
                            {
                            System.out.println("ERROR IN SIGNUP OR EMAIL ID ALREADY EXIST OF RETAILER");
                            }
                }
                catch(Exception e)
                {
                    System.out.println("signup of retailer error");
                }
                    
            }
                
                
                
                else if(line.equals("req@product"))
                {
                        System.out.println("1");
                        String emailid=in.readUTF();
                      //  String pid=in.readUTF();
                        String pname=in.readUTF();
                        String type1=in.readUTF();
                        String type2=in.readUTF();
                        String price=in.readUTF();
                        String qty=in.readUTF();
                        String image=in.readUTF();
                        String brand=in.readUTF();
                        String size=in.readUTF();
                        String dateofmfg=in.readUTF();
                        String offerdis=in.readUTF();
                        String author=in.readUTF();
                        String colour=in.readUTF();
                        String extrafeature=in.readUTF();
                        String warranty=in.readUTF();
                        
                    
                   try{
                      
                      String sql="insert into product (emailid,pname,type1,type2,price,quantity,image,brand,size,dateofmfg,offerdis,author,colour,extrafeature,warranty) values('"+emailid+"','"+pname+"','"+type1+"','"+type2+"','"+price+"','"+qty+"','"+image+"','"+brand+"','"+size+"','"+dateofmfg+"','"+offerdis+"','"+author+"','"+colour+"','"+extrafeature+"','"+warranty+"') ";
                      st.executeUpdate(sql);
                      
                      System.out.println("success");
                      }
                    catch(Exception ex)
                      {
                        System.out.println("ERROR IN PRODUCT UPDATE OR PID ALREADY EXIST");
                      }
                   
                    System.out.println("11");
                    
                    String sql2="Select pid from product where emailid='"+emailid+"' and pname='"+pname+"'";
                    ResultSet rs2=st.executeQuery(sql2);
                    rs2.next();
                    String PID=rs2.getString(1);
                    System.out.println(PID);
                    
                    
                      ImageUploadSocketRunnable obj=new ImageUploadSocketRunnable(socket,emailid,PID);
                    
                    
                    System.out.println("22");
                    out.writeUTF("success");
                   
                   
                   
                    
                }
                
                
                else if(line.equals("req@deletepro"))
                {
                    
                        String pid=in.readUTF();
                        String sql="delete from product where pid='"+pid+"'";
                        st.executeUpdate(sql);
                        out.writeUTF("success");
                       
                }
                
                else if(line.equals("req@pshow"))
                {
                   // System.out.println("1");
                    String emailid=in.readUTF();
                    //System.out.println("2");
                    String value;
                    //System.out.println("3");
                    String sql2="Select count(pid) from product where emailid='"+emailid+"'";
                    //System.out.println("4");
                    ResultSet rs2=st.executeQuery(sql2);
                    //System.out.println("5");
                    rs2.next();
                    //System.out.println("6");
                    String count=rs2.getString(1);
                    //System.out.println("7");
                    out.writeUTF(count);
                    //System.out.println("8");
                    
                    
                    
                    String sql="select * from product where emailid='"+emailid+"'";
                    System.out.println("9");
                    ResultSet rs=st.executeQuery(sql);
                    rs.next();
                    String emailzz=rs.getString(1);
                     
                      
                    //System.out.println(count);
                    int num=Integer.parseInt(count);
                    //System.out.println("7");
                    try
                    {//System.out.println("8");
                   
                    for(int j=0;j<num;j++)
                    {
                    for(int i=1;i<=16;i++)
                    {
                        
                        if(i!=8)
                        {
                        value=rs.getString(i);
                        
                        out.writeUTF(value);
                       // System.out.println(value+" "+i);
                        }
                    }
                    rs.next();
                    }
                    
                    }catch(Exception e){
                            
                        System.out.println("invalid email");
                            
                            }
                }
                
                
                
                else if(line.equals("req@showtrans"))
                {
                    
                    String email=in.readUTF();
                    String value;
                   
                    String sql2="count * from transaction where email='"+email+"'";
                    ResultSet rs2=st.executeQuery(sql2);
                    rs2.next();
                    
                    String count=rs2.getString(1);
                   // System.out.println("count is"+count);
                    out.writeUTF(count);
                    
                     String sql="select * from transaction where email='"+email+"'";
                     ResultSet rs=st.executeQuery(sql);
                     rs.next();
                    try
                    {
                    
                    for(int i=1;i<=8;i++)
                    {
                        
                        value=rs.getString(i);
                        out.writeUTF(value);
                       //  System.out.println("value at "+i+" "+value);
                        
                        
                    }
                    
                    }catch(Exception e){
                            
                        System.out.println("invalid email");
                            
                            }
                }
                
                
                else if(line.equals("req@allpshow"))
                {
                    
                    String value;
                    String sql2="Select count(pid) from product";
                    ResultSet rs2=st.executeQuery(sql2);
                   
                    rs2.next();
                    //System.out.println("6");
                    String count=rs2.getString(1);
                    //System.out.println("7");
                    out.writeUTF(count);
                    //System.out.println("8");
                    
                    
                    
                    String sql="select * from product";
                    //System.out.println("9");
                    ResultSet rs=st.executeQuery(sql);
                    rs.next();
                     
                      
                    //System.out.println(count);
                    int num=Integer.parseInt(count);
                    //System.out.println("7");
                    try
                    {//System.out.println("8");
                   
                    for(int j=0;j<num;j++)
                    {
                    for(int i=1;i<=16;i++)
                    {
                        
                        if(i!=8)
                        {
                        value=rs.getString(i);
                        
                        out.writeUTF(value);
                       // System.out.println(value+" "+i);
                        }
                    }
                    rs.next();
                    }
                    
                    }catch(Exception e){
                            
                        System.out.println("invalid email");
                            
                        }
                }
                
                
                
                
                
                
                
                
                
                
                else if(line.equals("req@sendimage"))
                {
                    String pid=in.readUTF();
                    System.out.println(pid);
                  
                    String sql2="Select emailid from product where pid='"+pid+"'";
                    ResultSet rs2=st.executeQuery(sql2);
                    rs2.next();
                    String emailid=rs2.getString(1);
                    System.out.println(emailid);
                    out.writeUTF("success");
                    ImageUploadSocketClient obj=new ImageUploadSocketClient(socket,emailid,pid);
                    
                    
                    System.out.println("2 image send ");
                    
                   
                    String sql3="Select * from product where pid='"+pid+"'";
                    ResultSet rs3=st.executeQuery(sql3);
                    rs3.next();
          
                 
                   String namez=rs3.getString(3);
                   // System.out.println(namez);
                   String cat=rs3.getString(4);
                   // System.out.println(cat);
                   String sub=rs3.getString(5);
                   //System.out.println(sub);
                   String catg=rs3.getString(6);
                   //System.out.println(catg);
                   String brand=rs3.getString(9);
                   //System.out.println(brand);
                   String s=rs3.getString(10);
                   //System.out.println(s);
                   String off=rs3.getString(12);
                   //System.out.println(off);
                   String col=rs3.getString(14);
                   //System.out.println(col);
                   String fea=rs3.getString(15);
                   //System.out.println(fea);
                   String war=rs3.getString(16);
                   //System.out.println(war);

                   
                   
                   // System.out.println("success sent");
                   out.writeUTF(namez);
                   out.writeUTF(cat);
                   out.writeUTF(sub);
                   out.writeUTF(catg);
                   out.writeUTF(brand);
                   out.writeUTF(s);
                   out.writeUTF(off);
                   out.writeUTF(col);
                   out.writeUTF(fea);
                   out.writeUTF(war);
                   System.out.println("Block excuted");
                   
                }
                
                
                
                else if(line.equals("req@decqty"))
                {
                    String new_qty=in.readUTF();
                    String sql="Update product set quantity='"+new_qty+"'";
                    st.executeUpdate(sql);
                    out.writeUTF("success");
                }
                
                
                
                else if(line.equals("req@shophistory"))
                        {
                           
                            String email_buyer=uemail;
                            
                            String sql="Select count(tpid) from transaction where emailbuyer='"+email_buyer+"'";
                         
                            ResultSet rs=st.executeQuery(sql);
                          
                            rs.next();
                            String a=rs.getString(1);
                            
                            out.writeUTF(a);
                            int count=Integer.parseInt(a);
                           
                            String sql2="Select * from transaction where emailbuyer='"+email_buyer+"' ";
                            ResultSet rs2=st.executeQuery(sql2);
                            rs2.next();
                            
                           for(int j=0;j<count;j++)
                    {
                    for(int i=1;i<=8;i++)
                    {
                        
                        if(i!=4)
                        {
                       String value=rs2.getString(i);
                        
                        out.writeUTF(value);
                       // System.out.println(value+" "+i);
                        }
                    }
                    rs2.next();
                    }
                                
                        }
                
                
                
                
                 else if(line.equals("req@datehistory"))
                        {
                            System.out.println("frst");
                            String email_buyer=uemail;
                            String date=in.readUTF();
                            System.out.println(date);
                            
                            String sql="Select count(tpid) from transaction where emailbuyer='"+email_buyer+"' and date(dop)='"+date+"'";
                            ResultSet rs=st.executeQuery(sql);
                            System.out.println("2");
                            rs.next();
                            String a=rs.getString(1);
                            System.out.println("3");
                            out.writeUTF(a);
                            int count=Integer.parseInt(a);
                            
                            String sql2="Select * from transaction where emailbuyer='"+email_buyer+"' and date(dop)='"+date+"'";
                            ResultSet rs2=st.executeQuery(sql2);
                            rs2.next();
                            
                   for(int j=0;j<count;j++)
                    {
                    for(int i=1;i<=8;i++)
                    {
                        
                        if(i!=4)
                        {
                            String value=rs2.getString(i);
                        
                            out.writeUTF(value);
                       // System.out.println(value+" "+i);
                        }
                    }
                    rs2.next();
                    }
                                
                        }
                
                else if(line.equals("req@amountspent"))
                        {
                            System.out.println("1");
                            String email_buyer=uemail;
                            String sql2="SELECT sum(price) from TRANSACTION where emailbuyer='"+email+"'";
                            ResultSet rs2=st.executeQuery(sql2);
                            
                            if(rs2==null)
                            {
                                 out.writeUTF("null");
                            }
                            else
                            {
                                rs2.next();
                                String sum=rs2.getString(1);
                                out.writeUTF(sum);
                            }
                            
                           
                        }
                
                
                
                
                else if(line.equals("req@allphightolow"))
                        {
                            
                            //String sort=in.readUTF();
                            String count,value;
                            String sql="Select count(pid) from product";
                            ResultSet rs=st.executeQuery(sql);
                            rs.next();
                            count=rs.getString(1);
                            int k=Integer.parseInt(count);
                            out.writeUTF(count);
                                
                                
                                String sql2="select * from product order by price DESC";
                                ResultSet rs2=st.executeQuery(sql2);
                                rs2.next();
                                
                                for(int i=0;i<k;i++)
                                {
                                    for(int j=1;j<=16;j++)
                                    {
                                        if(j!=8)
                                        {
                                            value=rs2.getString(j);
                                            out.writeUTF(value);
                                            //System.out.println(value+"  "+j);
                                        }
                                        
                                    }
                                    rs2.next();
                                }
                               
                            
                            
                        }
             
                
                else if(line.equals("req@allplowtohigh"))
                        {
                            System.out.println("firist");
                            //String sort=in.readUTF();
                            String count,value;
                            String sql="Select count(pid) from product";
                            ResultSet rs=st.executeQuery(sql);
                            rs.next();
                            count=rs.getString(1);
                            int k=Integer.parseInt(count);
                            out.writeUTF(count);
                            System.out.println(k);
                                
                                
                                String sql2="select * from product order by price ASC";
                                ResultSet rs2=st.executeQuery(sql2);
                                rs2.next();
                                
                                for(int i=0;i<k;i++)
                                {
                                    for(int j=1;j<=16;j++)
                                    {
                                        if(j!=8)
                                        {
                                            value=rs2.getString(j);
                                            out.writeUTF(value);
                                          //  System.out.println(value+"  "+j);
                                        }
                                        
                                    }
                                    rs2.next();
                                }
                               
                            
                 }
                
                
                
                 else if(line.equals("req@showbill"))
                {
                    
                    System.out.println("fisrt");
                    String pid=in.readUTF();
                    String qty=in.readUTF();
                    int extra_disc=0;
                    
                    String sql21="SELECT sum(price) from TRANSACTION where emailbuyer='"+uemail+"'";
                    ResultSet rs21=st.executeQuery(sql21);
                    rs21.next();
                    String sumz=rs21.getString(1);
                    int costz=Integer.parseInt(sumz);
                    System.out.println(sumz);
                    
                    if(rs21==null)
                    {
                         out.writeUTF("null");
                    }
                    else
                    {
                           if(costz>=100000)
                        {
                            extra_disc=15;
                            out.writeUTF("yes");
                        }
                           else 
                        {
                            extra_disc=0;
                            out.writeUTF("no");
                        }
                    }
                        
                    
                    
                        
                    
                    
                    
                    
                    
                    int quantity=Integer.parseInt(qty);
                    System.out.println("quantiy"+quantity);
                    String sql5="select * from product where pid='"+pid+"'";
                    ResultSet rs5=st.executeQuery(sql5);
                    rs5.next();
                    int aak=rs5.getInt(7);
                    System.out.println("aak"+aak);
                    String of=rs5.getString(12);
                    int offer_prod;
                    if(of.equals("N/A"))
                    {
                          offer_prod=0;
                    }
                    else
                    {
                          offer_prod=Integer.parseInt(of);
                    }
                   
                    System.out.println("offerpord"+offer_prod); 
                    
                    int total_offer=extra_disc+offer_prod+additional_disc;
                    System.out.println("totalofer"+total_offer);
                    
                    if(aak-quantity>=0)
                    {
                        out.writeUTF("success");
                        
                            System.out.println(pid+" "+quantity);
                    
                    
                            String sql="select * from product where pid='"+pid+"'";
                            ResultSet rs=st.executeQuery(sql);
                            rs.next();

                            String remail=rs.getString(1);
                            String pname=rs.getString(3);
                            String price_prod=rs.getString(6);
                            String type1=rs.getString(4);
                            int price=Integer.parseInt(price_prod);
                            

                          //  System.out.println("2");
                            float f=(float)(total_offer/100.0);
                            System.out.println(f);
                            float cost=quantity*price-(((float)quantity*price)*f);
                            System.out.println(cost);
                            
                            System.out.println(cost);
                             
                            String sql3="insert into transaction(pname,emailretailer,emailbuyer,price,pid,quantity,type1) values('"+pname+"','"+remail+"','"+uemail+"','"+cost+"','"+pid+"','"+qty+"','"+type1+"')";
                            st.executeUpdate(sql3);
                            System.out.println("sql3");

                            String sql2="Update product set quantity=quantity-'"+quantity+"' where pid='"+pid+"'";
                            st.executeUpdate(sql2);
                            System.out.println("sql2");

                            String sql4="select * from transaction where pid='"+pid+"'";
                            ResultSet rs4=st.executeQuery(sql4);
                            rs4.next();



                            System.out.println("22");

                            String tpid=rs4.getString(1);
                            System.out.println(tpid);

                            String date=rs4.getString(6);
                             System.out.println(date);


                            out.writeUTF(pname);
                            out.writeUTF(tpid);
                            out.writeUTF(date);
                            out.writeUTF(qty);
                            out.writeUTF(cost+"");


                        
                     }
                    else
                    {
                        out.writeUTF("notsuccess");
                    }
                }
                
                else if(line.equals("req@logout"))
                 {
                     System.out.println("aa");
                     socket.close();
                     break;
                 }
                
                
                 else if(line.equals("req@offeronprod"))
                 {
                     String pname=in.readUTF();
                     String sql="SELECT * FROM product where pname LIKE '%"+pname+"%'";
                     ResultSet rs=st.executeQuery(sql);
                     rs.next();
                     String offer=rs.getString(12);
                     
                     if(offer.equals("N/A") )
                     {
                        out.writeUTF("no");
                     }
                     else
                     {
                        out.writeUTF("yes");
                     }
                     
                     
                 }
                
                 else if(line.equals("req@myoffers"))
                 {
                    
                    String sql2="SELECT sum(price) from TRANSACTION where emailbuyer='"+uemail+"'";
                    ResultSet rs2=st.executeQuery(sql2);
                    rs2.next();
                    if(rs2==null)
                    {
                         out.writeUTF("null");
                    }
                    else
                    {
                        out.writeUTF("notnull");
                        
                        String sum=rs2.getString(1);
                        if(sum.equals(""))
                        {
                            sum="0";
                        }
                        int cost=0;
                        System.out.println(sum +"aaas");
                        if(rs2!=null )
                        {
                            System.out.println("1");
                            cost=Integer.parseInt(sum);
                            System.out.println("2");
                            System.out.println(cost+"");
                            System.out.println("3");
                        }
                    
                        if(cost>=100000)
                        {
                            System.out.println("4");
                            out.writeUTF("yes");
                        }
                        else
                        {
                            out.writeUTF("no");
                        }
                        
                    out.writeUTF(cost+"");
                    }
                    
                     
                 }
                
                 else if(line.equals("req@productsold"))
                 {
                  System.out.println("first");
                    String sql="select count(tpid) from transaction";
                    ResultSet rs=st.executeQuery(sql);
                    rs.next();
                    String k=rs.getString(1);
                    int count=Integer.parseInt(k);
                    System.out.println("k"+k);
                    
                    String sql2="select type1 from transaction";
                    ResultSet rs2=st.executeQuery(sql2);
                    rs2.next();
                    int e=0,c=0,f=0,w=0,s=0,o=0;
                    for(int i=0;i<count;i++)
                    {
                        String value=rs2.getString(1);
                        System.out.println(value);
                        
                        if(value.equals("Electronics"))
                        {
                            e++;
                        }
                        else if(value.equals("Clothing"))
                        {
                            c++;
                        }
                        else if(value.equals("Footwear"))
                        {
                            f++;
                        }
                        else if(value.equals("Watches"))
                        {
                            w++;
                        }
                        else if(value.equals("Other"))
                        {
                            o++;
                        }
                        else if(value.equals("Stationary"))
                        {
                            s++;
                        }
                        rs2.next();
                        
                    }

                    out.writeUTF(e+"");
                    out.writeUTF(c+"");
                    out.writeUTF(f+"");
                    out.writeUTF(w+"");
                    out.writeUTF(s+"");
                    out.writeUTF(o+"");
                    
                    
                    
                     
                 }
                
                else if(line.equals("req@retailgraph"))
                 {
                     
                    System.out.println("first");
                    String sql="SELECT COUNT(DISTINCT emailretailer) from TRANSACTION";
                    ResultSet rs=st.executeQuery(sql);
                    rs.next();
                    String total_retailer=rs.getString(1);
                    out.writeUTF(total_retailer);
                    System.out.println(total_retailer+" tot retailer");
                    
                    int count=Integer.parseInt(total_retailer);
                    System.out.println("count"+count);
                    
                    String sql2="SELECT DISTINCT emailretailer from TRANSACTION";
                    ResultSet rs2=st.executeQuery(sql2);
                    rs2.next();
                    
                    for(int i=1;i<=count;i++)
                    {
                        String value=rs2.getString(1);
                        out.writeUTF(value);
                        System.out.println(value+"value at count"+i);
                        rs2.next();
                    }
                    
                    String sql3="select count(emailretailer) from transaction where emailbuyer='"+uemail+"'";
                    ResultSet rs3=st.executeQuery(sql3);
                    rs3.next();
                    String p=rs3.getString(1);
                    out.writeUTF(p);
                    System.out.println(p+"total Retailer");
                    int total_r=Integer.parseInt(p);
                    
                    
                    String sql4="select emailretailer from transaction where emailbuyer='"+uemail+"'";
                    ResultSet rs4=st.executeQuery(sql4);
                    rs4.next();
                  
                    
                    for(int i=1;i<=total_r;i++)
                    {
                        String value2=rs4.getString(1);
                        out.writeUTF(value2);
                        System.out.println(value2+"value of retailer of count"+i);
                        rs4.next();
                    }
                    
          
                     
                 }
                
                else if(line.equals("req@topfive"))
                {
                    String month=in.readUTF();
                    String sql2="select emailbuyer from transaction where month(dop)='"+month+"' GROUP by emailbuyer order by sum(price) desc";
                    ResultSet rs2=st.executeQuery(sql2);
                    rs2.next();
                    String value;
                    int c=0;
                    
                    for(int i=1;i<=5;i++) 
                    {
                        value=rs2.getString(1);
                        System.out.println(value);
                        if(value.equals(uemail))
                        {
                            c++;
                        }
                        rs2.next();
                    }
                     System.out.println(c+"");
                     
                     if(c>0)
                     {
                         out.writeUTF("yes");
                         additional_disc=5;
                         
                     }
                     else
                     {
                         out.writeUTF("no");
                     }
                    
                }
                
                
                
                
                
                
                
                  
                
                
                
                
                    }catch (Exception ex) {
                        System.out.println("Error last wala");
            }
        }
    }
                
}



class MultiServer extends Thread
{
                    Tracker tr=null;
                    ServerSocket sr;
                    DataInputStream  in  = null;
    
    public MultiServer(Tracker tr)
    {
        try {
                    this.tr=tr;
                    sr=new ServerSocket(9006);
                    this.start();
        } catch (IOException ex) {
                    System.out.println("Error in constructor");
        }
    }
    
    public void run()
    {
        while(true)
        {
            try {
                
                    System.out.println("Waiting");
                    Socket sc=sr.accept();
                    JOptionPane.showMessageDialog(null,"connected now");
                    tr.listUpdate(sc);
                    Lis ls=new Lis();
                    ls.listenM(tr,sc);
                
            } catch (Exception ex) 
            {
                    System.out.println("thread  ke run me error");
            }
        }
    }
}



public class Tracker extends javax.swing.JFrame {

                    MultiServer ms=null;
                    int conNum=0;
                    Socket[] socArr=new Socket[100];
                    String[] uName=new String[100];
    
                    Connection con=null;
    
    public Tracker() {
                    initComponents();
                    SignupCon cob=new SignupCon();
                    con=cob.still_connecting();
                    if(con==null)
                        System.out.println("Error in database connection");
    }
    
    Connection getConObj()
    {
        
        return con;
    }
    
    int getPortFromSocket(Socket sc)
    {
                    for(int i=0;i<conNum;++i)
                    if(socArr[i]==sc)
                    return i;
                    return -1;
    }
    
    
    void listUpdate(Socket sc)
    {
                    try{
                    String str=sc+" is connected at port "+conNum;
                    System.out.println(str);
                    socArr[conNum]=sc;
        
                    t1.append(sc+"is connected\n");
                    conNum++;
        }
        catch(Exception e)
        {
                    System.out.println("error");
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        t2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setForeground(new java.awt.Color(204, 255, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Start Server");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, -1, -1));

        t1.setColumns(20);
        t1.setRows(5);
        jScrollPane1.setViewportView(t1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 60, 670, 321));

        t2.setColumns(20);
        t2.setRows(5);
        jScrollPane2.setViewportView(t2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 399, 670, 48));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("IP OF CONNECTED CLIENTS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sacredbaazar/Sacred-Games-on-Netflix-title-credits-by-Aniruddh-Mehta.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1130, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
                    ms=new MultiServer(this);
          
            
        } catch (Exception ex) {
                    System.out.println("error in server starting");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Tracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tracker().setVisible(true);
            }
        }); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea t1;
    private javax.swing.JTextArea t2;
    // End of variables declaration//GEN-END:variables
}
