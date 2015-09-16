/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs415.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
public class user {
    
     private static final cs415.Model.DatabaseUtils d = new cs415.Model.DatabaseUtils();
     
     public static String user_id ;
    
     public static int authenticate_user(String username, String password, String user_type)
     {
         if (d.outside_db_connect(user_type))
         {
             System.out.println("Connection Sucessful ");
             
             if (user_type.equals("Staff"))
             {
                 
                 try {
                     PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select role_id from staff where staff_id=? and staff_password= PASSWORD(?)");
                     pst.setString(1, username);
                     pst.setString(2, password);
                     ResultSet rs = pst.executeQuery();
                    
                     if(rs.next())
                     {
                         
                         if (rs.getString(1).equals("1"))
                         {      
                             System.out.println("Login Sucessful Library Admin");
                             return 1;
                         }
                         if (rs.getString(1).equals("2"))
                         {                           
                             System.out.println("Login Sucessful Library Clerk");
                             return 2;
                         }
                         
                        
                     }
                     else
                     {
                          return 0;
                     }
                        
                 } catch (SQLException ex) {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                 }
               
             }
             else
             {
                 
                 try {
                     PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select current_status_id from student where current_status_id =1 and student_id=? and student_password= PASSWORD(?)");
                     pst.setString(1, username);
                     pst.setString(2, password);
                     ResultSet rs = pst.executeQuery();
                    
                     if(rs.next())
                     {                       
                             return 1;                       
                     }
                     else
                     {
                          return 0;
                     }
                        
                 } catch (SQLException ex) {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                 }
               
             }
             
         }
         else
         {
             //System.out.println("Connection UnSucessful ");
             
             return 0;
             
         }
         
         return 1;
         
     };
     
     public static int authenticate_library_user(String username, String user_type)
     {
         user_id = username;
          if (user_type.equals("Staff")) 
          {
               if (d.db_connect_lib())
               {
                   
                   try {
                     PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select user_id from users where user_role_id =2 and username=?");
                     pst.setString(1, username);
                     ResultSet rs = pst.executeQuery();
                    
                     if(rs.next())
                     {                       
                             return 1;                       
                     }
                     else
                     {
                          return 0;
                     }
                        
                 } catch (SQLException ex) {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                 }
                   
               }
              
          }
          else
          {
              if (d.db_connect_lib())
               {
                   
                   try {
                     PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select user_id from users where user_role_id =1 and username=?");
                     pst.setString(1, username);
                     ResultSet rs = pst.executeQuery();
                    
                     if(rs.next())
                     {                       
                             return 1;                       
                     }
                     else
                     {
                          return 0;
                     }
                        
                 } catch (SQLException ex) {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                 }
                   
               }
              
          }
         
          return 1;
         
     };
     
     
     public static ResultSet get_user_details()
     {
          ResultSet rs = null;
         if (d.db_connect_student())
         {

                        try {
                          // data = FXCollections.observableArrayList();
                          // System.out.println("Row before query" ); 

                           PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select student_id,student_fname,student_lname,student_address,student_major_1,student_major_2,student_program from student  where student_id=?");
                          // PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select * from book_title");
                           pst.setString(1, user_id);
                           System.out.println("Row after query-----" );  
                             rs = pst.executeQuery();
                            
                           rs.next();
                           // return rs;

                          

                       }   catch (SQLException ex) {
                            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                        }
         }
    
         return rs;
     }
     
     
      public static ResultSet get_staff_details()
     {
          ResultSet rs = null;
         if (d.db_connect_staff())
         {

                        try {
                          // data = FXCollections.observableArrayList();
                          // System.out.println("Row before query" ); 

                           PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select staff_username,staff_fname,staff_lname,staff_address from staff  where staff_username=?");
                          // PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select * from book_title");
                           pst.setString(1, user_id);
                           System.out.println("Row after query-----" );  
                             rs = pst.executeQuery();
                            
                           rs.next();
                           // return rs;

                          

                       }   catch (SQLException ex) {
                            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                        }
         }
    
         return rs;
     }
}
