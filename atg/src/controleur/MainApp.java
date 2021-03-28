/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Philippe
 */
public class MainApp extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    

    
    /**
    * Constructeur
    */
    public MainApp()
    {
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Menu");
        primaryStage.getIcons().add(new Image("file:src/assets/logo_ATG.jpeg"));
        try
        {
            // Chargement du layout racine à partir du fichier fxml file
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/menu.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            // Exception qui intervient si le fichier fxml file n'a pas pu être chargé
            e.printStackTrace();
        }
        //primaryStage.setOnCloseRequest( event -> {System.out.println("Closing Stage");} );
    }

   
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
