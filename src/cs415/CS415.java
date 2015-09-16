/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs415;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *s11086903	Amendra Chand
 *s11087148	Javed Ali
 *s11056717	Suneet Prakash
 *s11074812	Christopher Prasad
 */
public class CS415 extends Application {
    
    
     cs415.Model.BookLoan b = new cs415.Model.BookLoan();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        
       if (b.check_fine())
       {
           
            System.out.println("Student Fine Updated Successfully" );
           
           
       }
        
        
        
        stage.setScene(scene);
         stage.setTitle("USP Library System");
         stage.setWidth(600.00);
         stage.setHeight(400.00);
         stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
