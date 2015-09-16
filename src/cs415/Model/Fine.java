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
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
public class Fine {
    
    
    private static ObservableList<ObservableList> data; 
    
    private static final cs415.Model.DatabaseUtils d = new cs415.Model.DatabaseUtils();
    
    
    
    
     public static void ViewStudentFine(TableView tableview,String username){ 
         
        try {
            data = FXCollections.observableArrayList();
        
            if (d.db_connect_lib())
            {

                PreparedStatement pst = DatabaseUtils.conn.prepareStatement("select f.book_isbn,f.book_copy_num,f.fine_amount - f.fine_received_amount as fine_due from fine f where f.user_id=? and f.fine_status_id=2");

                pst.setString(1, username);

                ResultSet rs = pst.executeQuery();
            
            
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
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
     
     
    
    
}
