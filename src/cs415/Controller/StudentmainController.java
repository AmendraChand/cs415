/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs415.Controller;

import static cs415.Model.BookLoan.reservebook;
import cs415.Model.BookSearchResult;
import cs415.Model.Fine;
import cs415.Model.user;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
public class StudentmainController implements Initializable {
    @FXML
    private Label This1;
    @FXML
    private Label This2;   
    @FXML
    private Pane psearch_items;
    @FXML
    private Pane ptableview;
    @FXML
    private Pane reserveview;
    @FXML
    private TableView tbviewbooks;
    @FXML
    private TableView tblfine;
    @FXML
    private TextField txtkeyword;
    @FXML
    private TextField bktitle;
    @FXML
    private TableView tbview;
    @FXML
    private TableView rtbview;
    @FXML
    private Label lblName;
    @FXML
    private Label lblProgram;
    @FXML
    private Label lblStudentID;
    @FXML
    private Label lblMajor1;
    @FXML
    private Label lblMajor2;
    @FXML
    private Label lblAddress;
     
   
    
    
    
      cs415.Model.DatabaseUtils d = new cs415.Model.DatabaseUtils();
      
     // TableView tableview = new TableView(); 
    
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
           ResultSet rs = null;
            ResultSet rs2 = null;
            
           rs= user.get_user_details();
            rs2= user.get_user_details();
         
            System.out.println("recordset" + rs.getString(1) );
            
            lblStudentID.setText(rs.getString(1));
            lblName.setText(rs.getString(2) + " " + rs.getString(3));
            lblAddress.setText(rs.getString(4));
            lblMajor1.setText(rs.getString(5));
            lblMajor2.setText(rs.getString(6));
            lblProgram.setText(rs.getString(7));
            
            
              //tbviewbooks.setItems(null); 
               tbviewbooks.layout();
                tblfine.layout();
              // tbview.getSelectionModel().clearSelection();
              
              BookSearchResult.FillStudentBookTable(tbviewbooks,rs.getString(1));
               Fine.ViewStudentFine(tblfine,rs.getString(1));
        
             
        } catch (SQLException ex) {
            Logger.getLogger(StudentmainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }  
    
     
     @FXML
    private void openLogout(ActionEvent event) {
        
        try {
            d.disconnect_db();
            
            Parent root = FXMLLoader.load(getClass().getResource("/cs415/View/Login.fxml"));
            
            Scene scene = new Scene(root);
            
             Stage stage = new Stage();
             stage.setTitle("Library System");
             
            stage.setScene(scene);
            
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
        } catch (IOException ex) {
            Logger.getLogger(StaffmainController.class.getName()).log(Level.SEVERE, null, ex);
        }              
    }
    
    
   
    
     @FXML
    private void ViewProfile(ActionEvent event) {
        
       // lblmessage.setVisible(false);
        psearch_items.setVisible(true);
       
             
    }
    
     @FXML
    private void SearchBook(ActionEvent event) {
        
       // ptableview.setVisible(true);
        
        String keyword;
        
          if(txtkeyword.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Please enter the title of the book to search!");
          else
          {
              
              keyword = txtkeyword.getText();
              
              keyword = "%" + keyword + "%";
              
               tbview.setItems(null); 
               tbview.layout();
             
              
              BookSearchResult.buildData(tbview,keyword);
              
            
          }
            
    }
    
    @FXML
    private void ResearveBookList(ActionEvent event) {
        
       
        
        String keyword;
        
          if(bktitle.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Please enter the title of the book to search!");
          else
          {
              
              keyword = bktitle.getText();
              
              keyword = "%" + keyword + "%";
              
               rtbview.setItems(null); 
               rtbview.layout();
            
              
              BookSearchResult.FillResearveTable(rtbview,keyword);
              
             
          }
            
    }
    
    @FXML
    private void ReserveBook(ActionEvent event) throws SQLException {
        String bookdetail= rtbview.getSelectionModel().getSelectedItem().toString().trim();  
        String regex = "\\[|\\]";
        String user_id;
        ResultSet rs = null;
           
         
        rs= user.get_user_details();
        user_id=rs.getString(1);
        
        
        if(bookdetail.length()==0)  // Checking for empty field
            JOptionPane.showMessageDialog(null, "Please select a book");
    
        else{
           
            System.out.println(bookdetail);
            String [] bookdata=bookdetail.replace(regex, "").split(",");
            String tempcatalog=bookdata[4].replaceAll("\\]", "").trim();
            System.out.println(bookdata[4]);
            int bookCatalognum=Integer.parseInt(tempcatalog);

                   System.out.println(bookCatalognum+"::");
            
             int selection = JOptionPane.showConfirmDialog(
                                null
                        , "Are you sure you want to reserve this Book?"
                        , "Selection : "
                        , JOptionPane.OK_CANCEL_OPTION
                        , JOptionPane.INFORMATION_MESSAGE);
             if (selection == JOptionPane.OK_OPTION){
                 if (reservebook(user_id,bookCatalognum))
                    {
                        try {
                            JOptionPane.showMessageDialog(null, "Book Successfully Reserved");

                            Parent root;

                            root = FXMLLoader.load(getClass().getResource("/cs415/View/StudentHome.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Student Home");
                            stage.setScene(new Scene(root, 600, 450));
                            stage.show();
                            ((Node)(event.getSource())).getScene().getWindow().hide();
                        } catch (IOException ex) {
                            Logger.getLogger(StaffmainController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error While Researving Book! Please Check");
                    }
             }
            
        }
    }
              
             
    
}
