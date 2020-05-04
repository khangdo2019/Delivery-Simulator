/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangdo.assignment3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This is a program that users can choose a mean of delivery for their product
 * @author Khang Do 2020
 */
public class DeliverySimulator extends Application{
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().
                getResource("FXMLDeliverySimulator.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Delivery Tool");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
