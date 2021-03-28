/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import modele.GestionBureau;
import modele.MembreBureau;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SaisieBureauController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<MembreBureau> lesMembreBureau;
    
    private Stage troisiemeStage;
    @FXML
    private TableView<MembreBureau> SaisieMembreTableview;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCID;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCFonction;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCNom;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCPrenom;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCAdresse;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCCp;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCVille;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCEmail;
    
    @FXML
    private TableColumn<MembreBureau, String> SaisieMembreTableviewCPortable;
    
    @FXML private javafx.scene.control.Button btnSupprimer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         lesMembreBureau = GestionBureau.listeBureau();
        SaisieMembreTableview.setItems(lesMembreBureau);
        
        SaisieMembreTableviewCID.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("id"));
        SaisieMembreTableviewCFonction.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("fonction"));
        SaisieMembreTableviewCNom.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("nom"));
        SaisieMembreTableviewCPrenom.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("prenom"));
        SaisieMembreTableviewCAdresse.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("adresse"));
        SaisieMembreTableviewCCp.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("cp"));
        SaisieMembreTableviewCVille.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("ville"));
        SaisieMembreTableviewCEmail.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("email"));
        SaisieMembreTableviewCPortable.setCellValueFactory(new PropertyValueFactory<MembreBureau, String>("portable"));
    }

 public void AjoutEditMembreBureau()
    {
         try
        {
        troisiemeStage = new Stage();
        troisiemeStage.setTitle("Ajout d'un membre du bureau");
        troisiemeStage.getIcons().add(new Image("file:src/assets/logo_ATG.jpeg"));
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/AjoutEditMembreBureau.fxml"));

        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        troisiemeStage.setScene(scene);
        troisiemeStage.show();
        }
        catch (IOException e)
        {
        System.out.println("Erreur chargement seconde fenetre : " + e.getMessage());
        }
    }    
 
  public void EditMembreBureau()
    {
        if(SaisieMembreTableview.getSelectionModel().getSelectedItem()!=null)
        {
            try 
            {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vue/AjoutEditMembreBureau.fxml"));
                Parent root = (Parent) loader.load();

                AjoutEditMembreBureauController secController=loader.getController();
                secController.Remplissage(SaisieMembreTableview.getSelectionModel().getSelectedItem());

                Stage stage=new Stage();
                stage.setTitle("Modification d'un membre du bureau");
                stage.getIcons().add(new Image("file:src/assets/logo_ATG.jpeg"));
                stage.setScene(new Scene(root));
                stage.show();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
 
            alert.setTitle("Error alert");
            alert.setHeaderText("Aucun membre selectionné");
            alert.setContentText("Vous devez selectionnez un membre avant de cliquer sur modifier");

            alert.showAndWait();
        }
        
    }
  
  public void supprimerUnMembreBureau()
    {
        if(SaisieMembreTableview.getSelectionModel().getSelectedItem()!=null)
        {
            int nbligne=0;

               nbligne = GestionBureau.SupprimerMembreBureau(SaisieMembreTableview.getSelectionModel().getSelectedItem().getId());


            if(nbligne>0)
            {
                showMessageDialog(null, "Suppression effectue avec succès");
                initialize();
            }
        }
        else
        {
               Alert alert = new Alert(AlertType.ERROR);
 
            alert.setTitle("Error alert");
            alert.setHeaderText("Aucun membre selectionné");
            alert.setContentText("Vous devez selectionnez un membre avant de cliquer sur supprimer");

            alert.showAndWait();
        }
    }
  
  public void initialize() 
    {
        lesMembreBureau = GestionBureau.listeBureau();
        SaisieMembreTableview.setItems(lesMembreBureau);
        
    }
    
}
