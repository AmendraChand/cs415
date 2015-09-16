/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs415.Controller;

import cs415.Model.BookLoan;
import static cs415.Model.BookLoan.book_check_in;
import static cs415.Model.BookLoan.insert_book_loan;
import cs415.Model.BookSearchResult;
import cs415.Model.user;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
public class StaffmainController implements Initializable {
    @FXML
    private Label This1;
    @FXML
    private Label This2;
    @FXML
    private Label lblStaffName;
    @FXML
    private Label lblStaffUsername;
    @FXML
    private Label lblStaffAddress;   
    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtBookCopyNum2;
    @FXML
    private TextField txtBookISBN;
     @FXML
    private TextField txtID;
     @FXML
    private TextField txtStudID;
    @FXML
    private TextField txtBookISBNnum;
    @FXML
    private TextField txtcopynum;
    @FXML
    private TableView ptable;
    @FXML
    private TableView tblcheckout;     
    @FXML
    private TableView tblStudentDetails;
     @FXML
    private TextField txtBook_ISBN;
      @FXML
    private TextField txtAmountReceived;
       @FXML
    private TextField txtCopyNum;
        @FXML
    private TextField txtStud_ID;
    
    
     cs415.Model.DatabaseUtils d = new cs415.Model.DatabaseUtils();
     
     cs415.Model.BookLoan b = new cs415.Model.BookLoan();
     private static Integer selected_copy_num=0;
     private static Integer bookisbn=0;
     private static Integer copy_num=0;
     private static String studentid;
     
     
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
           
           ResultSet rs = null;
           
           rs= user.get_staff_details();
           
            System.out.println("recordset" + rs.getString(1) );
            
            lblStaffUsername.setText(rs.getString(1));
            lblStaffName.setText(rs.getString(2) + " " + rs.getString(3));
            lblStaffAddress.setText(rs.getString(4));
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentmainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
   
    @FXML
    private void openAddpane(ActionEvent event) {
        System.out.println("You clicked me222!");
        This2.setVisible(false);
        This1.setVisible(false);
        
    }
    
     @FXML
    private void openLogout(ActionEvent event) {
        
        try {
            d.disconnect_db();
            
            Parent root = FXMLLoader.load(getClass().getResource("/cs415/View/Login.fxml"));
            
            Scene scene = new Scene(root);
            
              if (b.check_fine())
              {
           
                  System.out.println("Student Fine Updated Successfully" );
           
           
              }
        
            
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
    private void SearchBooks(ActionEvent event) {
        
        String studentid;
        Integer bookisbn;
         Integer book_copy_num;
        
        
         if(txtStudentID.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else if(txtBookISBN.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else if(txtcopynum.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else{ 
                          
             studentid = txtStudentID.getText();
             bookisbn = Integer.parseInt(txtBookISBN.getText());
             book_copy_num = Integer.parseInt(txtcopynum.getText());
             
                        
                         int selection = JOptionPane.showConfirmDialog(
                                null
                        , "Are you sure you want to register the Book?"
                        , "Selection : "
                        , JOptionPane.OK_CANCEL_OPTION
                        , JOptionPane.INFORMATION_MESSAGE);
                      
                        
                         if (selection == JOptionPane.OK_OPTION)
                         {
                            if (insert_book_loan(bookisbn,studentid,book_copy_num))
                                {
                                    try {
                                        JOptionPane.showMessageDialog(null, "Book Successfully Registered!");

                                        Parent root;

                                        root = FXMLLoader.load(getClass().getResource("/cs415/View/Staffmain.fxml"));
                                        Stage stage = new Stage();
                                        stage.setTitle("Staff Home");
                                        stage.setScene(new Scene(root, 600, 450));
                                        stage.show();
                                        ((Node)(event.getSource())).getScene().getWindow().hide();
                                    } catch (IOException ex) {
                                        Logger.getLogger(StaffmainController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Error While Registering Book! Please Check");
                                }
                           }
                        
                  
             
         }
        
    }
    
    
    
    
    
     @FXML
    private void CheckOut(ActionEvent event) {
        
        int bookisbn;
        int copynum;
       // int studentid ;
        
        
         if(txtBookISBNnum.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
          else if(txtBookCopyNum2.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else{ 
        
             copynum = Integer.parseInt(txtBookCopyNum2.getText());
             bookisbn = Integer.parseInt(txtBookISBNnum.getText());
             
             tblcheckout.setItems(null); 
            
             BookSearchResult.FillCheckoutTable(tblcheckout,bookisbn,copynum);
             
             tblcheckout.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                   
                    if (tblcheckout.getSelectionModel().getSelectedItem() != null) 
                    {
                        
                       String  Finalvaluetablerow = newValue.toString().split(",")[0].substring((newValue.toString().split(",")[0]).lastIndexOf('[')+1);
                         
                        studentid = Finalvaluetablerow;
                        
                        
                        
                         int selection = JOptionPane.showConfirmDialog(
                                null
                        , "Are you sure you want to check in the Book?"
                        , "Selection : "
                        , JOptionPane.OK_CANCEL_OPTION
                        , JOptionPane.INFORMATION_MESSAGE);
                      
                       
                         if (selection == JOptionPane.OK_OPTION)
                         {
                             
                                    System.out.println("Values before passing to the function is " +  copynum);
                                if (BookLoan.check_student_fine_status(bookisbn,studentid,copynum))
                                {
                                    
                                            if (book_check_in(bookisbn,studentid,copynum))
                                                {
                                                    try {
                                                        JOptionPane.showMessageDialog(null, "Book Successfully Checked In!");

                                                        Parent root;

                                                        root = FXMLLoader.load(getClass().getResource("/cs415/View/Staffmain.fxml"));
                                                        Stage stage = new Stage();
                                                        stage.setTitle("Staff Home");
                                                        stage.setScene(new Scene(root, 600, 450));
                                                        stage.show();
                                                        ((Node)(event.getSource())).getScene().getWindow().hide();
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(StaffmainController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }

                                                }
                                                else
                                                {
                                                    JOptionPane.showMessageDialog(null, "Error While Checking In Book! Please Check");
                                                }
                                }          
                                else
                                {
                             
                             
                                       JOptionPane.showMessageDialog(null, "The student has a fine. Please clear fine first!");
                             
                             
                                }
                             }
                           }
                      
                    
               });
             
         }
        
    }
    
    
     @FXML
    private void ViewStudentDetails(ActionEvent event) {
        
        
        String studentid;
        
        
         if(txtID.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else{ 
        
               studentid = txtID.getText();
               
               
              BookSearchResult.FillStudentBookTable(tblStudentDetails,studentid);    
        
        
        
         }
        
    }
    
    @FXML
    private void  resettextfield(ActionEvent event) {
        txtID.setText("");
        
        
        
        
    }
    
     @FXML
    private void  ReceiveStudentFine(ActionEvent event) {
        
        int bookisbn;
        int copynum;
        String studentid ;
        double fine_received;
         
        
         if(txtBook_ISBN.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
          else if(txtAmountReceived.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
           else if(txtCopyNum.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
            else if(txtStud_ID.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else{ 
        
             copynum = Integer.parseInt(txtCopyNum.getText());
             bookisbn = Integer.parseInt(txtBook_ISBN.getText());
             studentid = txtStud_ID.getText();
             fine_received = Double.parseDouble(txtAmountReceived.getText());
             
             
              if (BookLoan.receive_fine(bookisbn,studentid,copynum,fine_received))
              {
                     try {
                                        JOptionPane.showMessageDialog(null, "Fine Successfully Updated!");

                                        Parent root;

                                        root = FXMLLoader.load(getClass().getResource("/cs415/View/Staffmain.fxml"));
                                        Stage stage = new Stage();
                                        stage.setTitle("Staff Home");
                                        stage.setScene(new Scene(root, 600, 450));
                                        stage.show();
                                        ((Node)(event.getSource())).getScene().getWindow().hide();
                           } catch (IOException ex) {
                                        Logger.getLogger(StaffmainController.class.getName()).log(Level.SEVERE, null, ex);
                           }

                }
                else
                {
                                    JOptionPane.showMessageDialog(null, "Error While Updating Fine! Please Check");
                }
        
        
            }    
        
    }
    
    
}
