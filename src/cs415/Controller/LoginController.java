/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs415.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
public class LoginController implements Initializable {
    @FXML
    private Label NameText;
     @FXML
     private TextField txtusername;
     @FXML
     private PasswordField txtpassword;
     @FXML
     private ComboBox cmbuser_type;
     
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     @FXML private void handleLoginButtonAction(ActionEvent event) {
       // actiontarget.setText("Sign in button pressed");#handleLoginButtonAction
    
        
         cs415.Model.user u = new cs415.Model.user();
         
         String username,password,user_type;
         int user_role = 0;
         int active_user=0;
        
         if(txtusername.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else if(txtpassword.getText().length()==0)  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
         else if(cmbuser_type.getSelectionModel().isEmpty())  // Checking for empty field
                  JOptionPane.showMessageDialog(null, "Please Select Staff or Student !");
         else{ 
        
           

                username = txtusername.getText();
                password = txtpassword.getText();
                user_type = cmbuser_type.getSelectionModel().getSelectedItem().toString(); 

                   if (user_type.equals("Staff"))
                   {
                       user_role = u.authenticate_user(username,password,user_type);

                       if (user_role == 0)
                       {
                            System.out.println("Login Failed  ");

                       }
                       else
                       {

                           active_user = u.authenticate_library_user(username,user_type);

                           if (active_user ==1)
                           {

                               try {
                                   System.out.println("Login Success......Your are a active user");

                                   Parent root;

                                   root = FXMLLoader.load(getClass().getResource("/cs415/View/Staffmain.fxml"));
                                   Stage stage = new Stage();
                                   stage.setTitle("Staff Home");
                                   stage.setScene(new Scene(root, 585, 390));
                                   stage.setResizable(false);
                                   stage.show();
                                   ((Node)(event.getSource())).getScene().getWindow().hide();
                                   
                                   
                                   
                                   
                               } catch (IOException ex) {
                                   Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                               }


                           }
                           else
                           {
                                System.out.println("Login Not Success......You are not active");

                           }

                       }

                   }
                   else
                   {
                        user_role = u.authenticate_user(username,password,user_type);

                       if (user_role == 0)
                       {
                            System.out.println("Login Failed  ");

                       }
                       else
                       {

                           active_user = u.authenticate_library_user(username,user_type);

                           if (active_user ==1)
                           {                        

                                  try {
                                   System.out.println("Login Success......Your are a active user");

                                   Parent root;

                                   root = FXMLLoader.load(getClass().getResource("/cs415/View/StudentHome.fxml"));
                                   Stage stage = new Stage();
                                   stage.setTitle("Student Home");
                                   stage.setScene(new Scene(root, 616.0, 454.0));
                                   stage.setResizable(false);
                                   stage.show();
                                   ((Node)(event.getSource())).getScene().getWindow().hide();
                               } catch (IOException ex) {
                                   Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                               }

                           }
                           else
                           {
                                System.out.println("Login Not Success......You are not active");                                              
                           }

                       }

                   }

       }
         
         
    }
    
}
