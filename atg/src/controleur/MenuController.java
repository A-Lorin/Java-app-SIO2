/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class MenuController implements Initializable
{
    

    private Stage deuxiemeStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    public void afficherInsert()
    {
        try
        {
        deuxiemeStage = new Stage();
        deuxiemeStage.setTitle("Récapitulatif des etudiants");
        deuxiemeStage.getIcons().add(new Image("file:src/assets/logo_ATG.jpeg"));
        FXMLLoader loader = new
       FXMLLoader(MainApp.class.getResource("/vue/FenFXML_Recap.fxml"));

        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        deuxiemeStage.setScene(scene);
        deuxiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement seconde fenetre : " + e.getMessage());
        }
    }
    
    public void afficherInsertMembre()
    {
         try
        {
        deuxiemeStage = new Stage();
        deuxiemeStage.setTitle("Saisie d'un membre");
        deuxiemeStage.getIcons().add(new Image("file:src/assets/logo_ATG.jpeg"));
        FXMLLoader loader = new
       FXMLLoader(MainApp.class.getResource("/vue/saisieMembre.fxml"));

        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        deuxiemeStage.setScene(scene);
        deuxiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement seconde fenetre : " + e.getMessage());
        }
     }
    
    
    public void afficherSaisieBureau()
    {
         try
        {
        deuxiemeStage = new Stage();
        deuxiemeStage.setTitle("Membres du bureau");
        deuxiemeStage.getIcons().add(new Image("file:src/assets/logo_ATG.jpeg"));
        FXMLLoader loader = new
       FXMLLoader(MainApp.class.getResource("/vue/saisieBureau.fxml"));

        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        deuxiemeStage.setScene(scene);
        deuxiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement seconde fenetre : " + e.getMessage());
        }
     }
    
    public void afficherMembreSansRecu()
    {
         try
        {
        deuxiemeStage = new Stage();
        deuxiemeStage.setTitle("Membres sans reçu");
        deuxiemeStage.getIcons().add(new Image("file:src/assets/logo_ATG.jpeg"));
        FXMLLoader loader = new
       FXMLLoader(MainApp.class.getResource("/vue/MembreSansRecu.fxml"));

        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        deuxiemeStage.setScene(scene);
        deuxiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement seconde fenetre : " + e.getMessage());
        }
     }
}
    
 
