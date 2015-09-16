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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
public class BookSearchResult {
    
    private static final cs415.Model.DatabaseUtils d = new cs415.Model.DatabaseUtils();
    
    private static ObservableList<ObservableList> data; 
    
     public static void buildData(TableView tableview,String keyword){ 
         
        try {
            data = FXCollections.observableArrayList();
            System.out.println("Row before query" ); 
            if (d.db_connect_lib())
            {
                
            PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select b.book_title,b.book_title_author from book_title b where b.book_title like ?");
        
            pst.setString(1, keyword);
            System.out.println("Row after query" );  
            ResultSet rs = pst.executeQuery();
            
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                    
                });
               if( tableview.getColumns().size() <= 1)
               {
                   tableview.getColumns().addAll(col);
                   
               }
               
            }
            
            while(rs.next()){  
                     //Iterate Row  
                     ObservableList<String> row = FXCollections.observableArrayList();  
                    for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){  
                      //Iterate Column  
                      row.add(rs.getString(i));  
                    }  
                   
                    data.add(row);  
            }  
             //FINALLY ADDED TO TableView  
            tableview.setItems(data);  
             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookSearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
     public static void FillBookTable(TableView tableview,int bookisbn){ 
         
        try {
            data = FXCollections.observableArrayList();
        
            if (d.db_connect_lib())
            {

                PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select bt.book_title,bt.book_title_author,b.book_copy_num from book_title bt join book b on b.book_isbn = bt.book_isbn where b.book_status=2 and b.book_isbn =?");

                pst.setInt(1, bookisbn);

                ResultSet rs = pst.executeQuery();
            
            
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }

                    });
                   if( tableview.getColumns().size() <= 2)
                   {
                       tableview.getColumns().addAll(col);

                   }
                
                
                  }
            
                while(rs.next()){  
                         //Iterate Row  
                         ObservableList<String> row = FXCollections.observableArrayList();  
                        for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){  
                          //Iterate Column  
                          row.add(rs.getString(i));  
                        }  
                        
                        data.add(row);  
                }  
                 //FINALLY ADDED TO TableView  
                tableview.setItems(data);  

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookSearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
    
     public static void FillStudentBookTable(TableView tableview,String username){ 
         
        try {
            data = FXCollections.observableArrayList();
        
            if (d.db_connect_lib())
            {

                PreparedStatement pst = DatabaseUtils.conn.prepareStatement("SELECT bt.book_title,bl.book_loan_date,bl.book_due_date as due_date FROM book_loan bl join book_title bt on bt.book_isbn=bl.book_isbn WHERE bl.book_loan_status = 1 and bl.username=?");

                pst.setString(1, username);

                ResultSet rs = pst.executeQuery();
            
            
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }

                    });
                   if( tableview.getColumns().size() <= 2)
                   {
                       tableview.getColumns().addAll(col);

                   }
                
                
                  }
            
                while(rs.next()){  
                         //Iterate Row  
                         ObservableList<String> row = FXCollections.observableArrayList();  
                        for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){  
                          //Iterate Column  
                          row.add(rs.getString(i));  
                        }  
                        
                        data.add(row);  
                }  
                 //FINALLY ADDED TO TableView  
                tableview.setItems(data);  

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookSearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
     public static void FillResearveTable(TableView tableview,String title){ 
         
        try {
            data = FXCollections.observableArrayList();
        
            if (d.db_connect_lib())
            {

                PreparedStatement pst = DatabaseUtils.conn.prepareStatement("Select bt.book_title,bt.book_title_author, bls.loan_description, b.book_copy_num ,bt.book_catalog_num from book_title bt join book b on b.book_isbn = bt.book_isbn join book_loan_status bls on bls.loan_status_id = b.book_status where b.book_status=1 and bt.book_title like ?");

                String temp= "%"+title+"%";
                pst.setString(1, temp);

                ResultSet rs = pst.executeQuery();
            
            
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }

                    });
                   if( tableview.getColumns().size() <= 2)
                   {
                       tableview.getColumns().addAll(col);

                   }
                
                
                  }
            
                while(rs.next()){  
                         //Iterate Row  
                         ObservableList<String> row = FXCollections.observableArrayList();  
                        for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){  
                          //Iterate Column  
                          row.add(rs.getString(i));  
                        }  
                        
                        data.add(row);  
                }  
                 //FINALLY ADDED TO TableView  
                tableview.setItems(data);  

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookSearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
     
     public static void FillCheckoutTable(TableView tableview,int bookisbn,int copynum){ 
         
        try {
            data = FXCollections.observableArrayList();
        
            if (d.db_connect_lib())
            {

                PreparedStatement pst = DatabaseUtils.conn.prepareStatement("SELECT bl.username,bt.book_isbn,bt.book_title,bl.book_loan_date FROM book_loan bl join book_title bt on bt.book_isbn=bl.book_isbn WHERE bl.book_loan_status = 1 and bl.book_isbn=? and bl.book_copy_num=?");

                pst.setInt(1, bookisbn);
                pst.setInt(2, copynum);
                
                ResultSet rs = pst.executeQuery();
            
            
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }

                    });
                   if( tableview.getColumns().size() <= 3)
                   {
                       tableview.getColumns().addAll(col);

                   }
                
                
                  }
            
                while(rs.next()){  
                         //Iterate Row  
                         ObservableList<String> row = FXCollections.observableArrayList();  
                        for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){  
                          //Iterate Column  
                          row.add(rs.getString(i));  
                        }  
                        
                        data.add(row);  
                }  
                 //FINALLY ADDED TO TableView  
                tableview.setItems(data);  

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookSearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
    
}
