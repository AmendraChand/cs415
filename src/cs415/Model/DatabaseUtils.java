/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs415.Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
 
   
public class DatabaseUtils {
    
   private static final int DEFAULT_NUMBER_OF_CONNECTIONS = 100;
   private static final String DRIVER = "com.mysql.jdbc.Driver";
   private static final String URL = "jdbc:mysql://localhost:3306/usplibrary";
   private static final String URL2 = "jdbc:mysql://localhost:3306/student";
   private static final String URL3 = "jdbc:mysql://localhost:3306/staff";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "";
   
   public static Connection conn ;
   
  
   
   public static boolean outside_db_connect(String user_type)
   {
       
       try {
           
           if (user_type.equals("Staff"))
           {
                 return connect_db(DRIVER,URL3,USERNAME,PASSWORD,user_type);
               
           }
           else
                  return connect_db(DRIVER,URL2,USERNAME,PASSWORD,user_type);
           
           
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
       
   };
   
    public static boolean db_connect_lib()
   {
       
       try {
           
                  return connect_db_lib(DRIVER,URL,USERNAME,PASSWORD);
           
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
       
   };
    
    
    public static boolean db_connect_student()
   {
       
       try {
           
                  return connect_db_student(DRIVER,URL2,USERNAME,PASSWORD);
           
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
       
   };
    
     public static boolean db_connect_staff()
   {
       
       try {
           
                  return connect_db_staff(DRIVER,URL3,USERNAME,PASSWORD);
           
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
       
   };
   
   
   
    public static boolean connect_db(String driver, String url, String username, String password,String user_type) throws SQLException
    {
       
       try {
           Class.forName(driver);  // MySQL database connection
           
            conn = DriverManager.getConnection(url + "?" + "user=" + username + "&password=" + password);
            // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?" + "user=root&password=");  
           return true;
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            return false;
       }
      
        
    };
    
     public static boolean connect_db_lib(String driver, String url, String username, String password) throws SQLException
    {
       
       try {
           Class.forName(driver);  // MySQL database connection
           
            conn = DriverManager.getConnection(url + "?" + "user=" + username + "&password=" + password);
            // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?" + "user=root&password=");  
           return true;
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            return false;
       }
      
        
    }
     
      public static boolean connect_db_student(String driver, String url, String username, String password) throws SQLException
    {
       
       try {
           Class.forName(driver);  // MySQL database connection
           
            conn = DriverManager.getConnection(url + "?" + "user=" + username + "&password=" + password);
            // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?" + "user=root&password=");  
           return true;
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            return false;
       }
      
        
    }
      
        public static boolean connect_db_staff(String driver, String url, String username, String password) throws SQLException
    {
       
       try {
           Class.forName(driver);  // MySQL database connection
           
            conn = DriverManager.getConnection(url + "?" + "user=" + username + "&password=" + password);
            // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?" + "user=root&password=");  
           return true;
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            return false;
       }
      
        
    }
     
      public static void disconnect_db()
    {
       
        try {
                conn.close();
                System.out.println("connection closed");
                
            } catch (Exception ex) { 
                System.err.println("couldn't close connection");
                  
            }
      
    }
    
}
