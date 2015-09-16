/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs415.Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import static java.time.LocalDate.now;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeDate.now;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */


public class BookLoan {
    
     private static final cs415.Model.DatabaseUtils d = new cs415.Model.DatabaseUtils();
     
     
      public static boolean insert_book_loan(int bookisbn,String username,int book_copy_num){
          
         String staff_username= user.user_id;
         Date current_date;//,due_date;
         
         current_date=getCurrentDate();
         
         Calendar cal = Calendar.getInstance();
         cal.setTime(current_date);
         cal.add(Calendar.DATE, 14);
         
         Date due_date = new Date(cal.getTime().getTime());
         //due_date = (Date) current_date.

           if (d.db_connect_lib())
            {
               try {
                   PreparedStatement pst = DatabaseUtils.conn.prepareStatement("insert into book_loan(username,book_isbn,staff_username,book_copy_num,book_loan_date,book_due_date,book_loan_status)values(?,?,?,?,?,?,?)");
                   
                   pst.setString(1, username);
                   pst.setInt(2, bookisbn);
                   pst.setString(3, staff_username);
                   pst.setInt(4,book_copy_num);
                   pst.setDate(5, getCurrentDate() );
                   pst.setDate(6, due_date );
                   pst.setInt(7,1);
                   
                   pst.executeUpdate();
                   
                   PreparedStatement pst2 = DatabaseUtils.conn.prepareStatement("update book set book_status = 1 where book_isbn=? and book_copy_num=?");
                   pst2.setInt(1, bookisbn);
                   pst2.setInt(2,book_copy_num);
                   pst2.executeUpdate();
                  
                    return true;
                   
               } catch (SQLException ex) {
                   Logger.getLogger(BookLoan.class.getName()).log(Level.SEVERE, null, ex);
                   
          
                    return false;
               }
                
         }
      return true;
      }
      
    private static java.sql.Date getCurrentDate() 
      {
             java.util.Date today = new java.util.Date();
               return new java.sql.Date(today.getTime());
      }
    
    
    public static boolean reservebook(String userid, int catalognum){
    
           if (d.db_connect_lib())
            {
               try {
                   PreparedStatement pst = DatabaseUtils.conn.prepareStatement(" insert into reservation (student_id,book_catalog_num)values(?,?)");
                   
                   pst.setString(1, userid);
                   pst.setInt(2, catalognum);
                   
                   pst.executeUpdate();
                    return true;
                   
               } catch (SQLException ex) {
                   Logger.getLogger(BookLoan.class.getName()).log(Level.SEVERE, null, ex);
                   
          
                    return false;
               }
                
            }
            return true;
    }
    
      public static boolean book_check_in(int bookisbn,String username,int book_copy_num){
    
           if (d.db_connect_lib())
            {
               try {
                   PreparedStatement pst = DatabaseUtils.conn.prepareStatement("update book_loan set book_loan_status=2 where username=? and book_isbn=? and book_loan_status=1");
                   
                   pst.setString(1, username);
                   pst.setInt(2, bookisbn);
                  
                   pst.executeUpdate();
                   
                   PreparedStatement pst2 = DatabaseUtils.conn.prepareStatement("update book set book_status = 2 where book_isbn=? and book_copy_num=?");
                   pst2.setInt(1, bookisbn);
                   pst2.setInt(2,book_copy_num);
                   pst2.executeUpdate();
                  
                    return true;
                   
               } catch (SQLException ex) {
                   Logger.getLogger(BookLoan.class.getName()).log(Level.SEVERE, null, ex);
                   
          
                    return false;
               }
                
         }
      return true;
      }
      

      
      
      public static boolean check_fine(){
    
           if (d.db_connect_lib())
            {
               try {
                   PreparedStatement pst = DatabaseUtils.conn.prepareStatement("CALL update_student_fine()");
                   
                   //pst.setString(1, username);
                   //pst.setInt(2, bookisbn);
                  
                   pst.executeUpdate();
                   
                   
                System.out.println("Student Fine Updated Successfully from model" );
                   
                  // PreparedStatement pst2 = DatabaseUtils.conn.prepareStatement("update book set book_status = 2 where book_isbn=? and book_copy_num=?");
                  // pst2.setInt(1, bookisbn);
                  // pst2.setInt(2,book_copy_num);
                  // pst2.executeUpdate();
                   
                   
                  
                    return true;
                   
               } catch (SQLException ex) {
                   Logger.getLogger(BookLoan.class.getName()).log(Level.SEVERE, null, ex);
                   
          
                    return false;
               }
                
         }
      return true;
      }
      
      
      
      
      public static boolean receive_fine(int bookisbn,String username,int book_copy_num, double fine_received){
    
           if (d.db_connect_lib())
            {
               try {
                   PreparedStatement pst = DatabaseUtils.conn.prepareStatement("CALL receive_student_fine(?,?,?,?)");
                   
                   pst.setString(1, username);
                   pst.setInt(2, bookisbn);
                   pst.setInt(3, book_copy_num);
                   pst.setDouble(4, fine_received);
                  
                   pst.executeUpdate();
                   
                   
                System.out.println("Student Fine Received Successfully" );
                   
                  // PreparedStatement pst2 = DatabaseUtils.conn.prepareStatement("update book set book_status = 2 where book_isbn=? and book_copy_num=?");
                  // pst2.setInt(1, bookisbn);
                  // pst2.setInt(2,book_copy_num);
                  // pst2.executeUpdate();
                   
                   
                  
                    return true;
                   
               } catch (SQLException ex) {
                   Logger.getLogger(BookLoan.class.getName()).log(Level.SEVERE, null, ex);
                   
          
                    return false;
               }
                
         }
      return true;
      }
      

      
      
      
      public static boolean check_student_fine_status(int bookisbn,String username,int book_copy_num){
    
           if (d.db_connect_lib())
            {
               try {
                   PreparedStatement pst = DatabaseUtils.conn.prepareStatement("select f.book_isbn,f.book_copy_num,f.fine_amount from fine f where f.user_id=? and f.book_isbn=? and f.book_copy_num=? and f.fine_status_id=2");
                   
                   pst.setString(1, username);
                   pst.setInt(2, bookisbn);
                   pst.setInt(3, book_copy_num);
                   ResultSet rs = pst.executeQuery();
                   
                   
                        System.out.println("Error --------------------------------->" + username + " " + bookisbn + " " + book_copy_num);
                    
                     if(rs.next())
                     {                       
                             
                         
                         
                        System.out.println("Error --------------------------------->" );
                         return false;                       
                     }
                     else
                     {
                         
                          System.out.println("Error ----trruuuuuuuuu----------------------------->" );
                          return true;
                     }
                   
               // System.out.println("Student Fine Received Successfully" );
                  
                   
               } catch (SQLException ex) {
                   Logger.getLogger(BookLoan.class.getName()).log(Level.SEVERE, null, ex);
                   
          
                    return false;
               }
                
         }
      return true;
      }
      

        
}
