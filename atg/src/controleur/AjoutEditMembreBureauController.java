/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import modele.GestionBureau;
import modele.MembreBureau;

/**
 * FXML Controller class
 *
 * @author User
 */

public class AjoutEditMembreBureauController implements Initializable
{

    /**
     * Initializes the controller class.
     */
     @FXML
    Label TitrePage;
    @FXML
    ComboBox cmbCivilite;
    @FXML
    TextField tbxFonction;
    @FXML
    TextField tbxNom;
    @FXML
    TextField tbxPrenom;
    @FXML
    TextField tbxAdresse;
    @FXML
    TextField tbxCodePostal;
    @FXML
    TextField tbxVille;
    @FXML
    TextField tbxTelPortable;
    @FXML
    TextField tbxMail;
    int edition=0;
    MembreBureau membreAModifier;
    @FXML private javafx.scene.control.Button btnEnregistrerMembreBureau;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList lesCivilites = FXCollections.observableArrayList();
        lesCivilites.add("Monsieur");
        lesCivilites.add("Madame");
        cmbCivilite.setItems(lesCivilites);
        cmbCivilite.getSelectionModel().selectFirst();
        
        final Pattern patternTel = Pattern.compile("\\d{0,10}");
        TextFormatter<?> formatTelPortable = new TextFormatter<>(changeTelPortable -> {
        if (patternTel.matcher(changeTelPortable.getControlNewText()).matches()) {
        // todo: remove error message/markup
        return changeTelPortable; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });
        tbxTelPortable.setTextFormatter(formatTelPortable);
        
        final Pattern patternCp = Pattern.compile("\\d{0,5}");
        TextFormatter<?> formatCp = new TextFormatter<>(changeCp -> {
        if (patternCp.matcher(changeCp.getControlNewText()).matches()) {
        // todo: remove error message/markup
        return changeCp; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });

        tbxCodePostal.setTextFormatter(formatCp);
        
         final Pattern patterString = Pattern.compile("^[\\s|\\D]*");
        TextFormatter<?> formatNom = new TextFormatter<>(changeNom -> {
        if (patterString.matcher(changeNom.getControlNewText()).matches()) {
        // todo: remove error message/markup
        return changeNom; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });
        
        tbxNom.setTextFormatter(formatNom);
        
        TextFormatter<?> formatPrenom = new TextFormatter<>(changePrenom -> {
        if (patterString.matcher(changePrenom.getControlNewText()).matches()) {
        // todo: remove error message/markup
        return changePrenom; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });
        
        tbxPrenom.setTextFormatter(formatPrenom);
        
        TextFormatter<?> formatVille = new TextFormatter<>(changeVille -> {
        if (patterString.matcher(changeVille.getControlNewText()).matches()) {
        // todo: remove error message/markup
        return changeVille; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });
        
        tbxVille.setTextFormatter(formatVille);
    }    
    
    public void AjouterUnMembreBureau()
    {
        String str=tbxVille.getText().replaceAll(String.valueOf('"'),"");
        tbxVille.setText(str);
        str=tbxMail.getText().replaceAll(String.valueOf('"'),"");
        tbxMail.setText(str);
        str=tbxNom.getText().replaceAll(String.valueOf('"'),"");
        tbxNom.setText(str);
        str=tbxPrenom.getText().replaceAll(String.valueOf('"'),"");
        tbxPrenom.setText(str);
        str=tbxAdresse.getText().replaceAll(String.valueOf('"'),"");
        tbxAdresse.setText(str);
        str=tbxFonction.getText().replaceAll(String.valueOf('"'),"");
        tbxFonction.setText(str);
        
        str=tbxVille.getText().replaceAll(String.valueOf("'"),"");
        tbxVille.setText(str);
        str=tbxMail.getText().replaceAll(String.valueOf("'"),"");
        tbxMail.setText(str);
        str=tbxNom.getText().replaceAll(String.valueOf("'"),"");
        tbxNom.setText(str);
        str=tbxPrenom.getText().replaceAll(String.valueOf("'"),"");
        tbxPrenom.setText(str);
        str=tbxAdresse.getText().replaceAll(String.valueOf("'"),"");
        tbxAdresse.setText(str);
        str=tbxFonction.getText().replaceAll(String.valueOf("'"),"");
        tbxFonction.setText(str);
        
        if(!(tbxFonction.getText().equals("")|tbxNom.getText().equals("")|tbxPrenom.getText().equals("")|tbxAdresse.getText().equals("")|tbxCodePostal.getText().equals("")|tbxVille.getText().equals("")|tbxMail.getText().equals("")|tbxTelPortable.getText().equals("")))
        {
            int nbligne=0;
        if(edition==0)
        {
           nbligne = GestionBureau.EnregistrerMembreBureau(tbxFonction.getText(),cmbCivilite.getValue().toString(),tbxNom.getText(),tbxPrenom.getText(),tbxAdresse.getText(),tbxCodePostal.getText(),tbxVille.getText(),tbxMail.getText(),tbxTelPortable.getText());
        
        }
        else
        {
             nbligne = GestionBureau.UpdateMembreBureau(membreAModifier.getId(),tbxFonction.getText(),cmbCivilite.getValue().toString(),tbxNom.getText(),tbxPrenom.getText(),tbxAdresse.getText(),tbxCodePostal.getText(),tbxVille.getText(),tbxMail.getText(),tbxTelPortable.getText());
        
        }
        if(nbligne>0)
        {
            if(edition==0)
            {
                showMessageDialog(null, "Ajout effectue avec succès");
            }
            else
            {
                showMessageDialog(null, "Midification effectue avec succès");
            }
            
            // get a handle to the stage
            Stage stage = (Stage) btnEnregistrerMembreBureau.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
        }
        else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
 
            alert.setTitle("Error alert");
            alert.setHeaderText("Champ(s) vide");
            alert.setContentText("Tous les champs doivent être remplie");

            alert.showAndWait();
        }
        
    }
    
        @FXML
public void Remplissage(MembreBureau Membre) 
{
    membreAModifier=Membre;
    edition=1;
    int indexcmbcivilite;
    if(Membre.getTitre()=="Monsieur")
    {
        indexcmbcivilite=0;
    }
    else
    {
        indexcmbcivilite=1;
    }
    cmbCivilite.getSelectionModel().select(indexcmbcivilite);
    TitrePage.setText("Modification d'un membre du bureau");
    tbxFonction.setText(Membre.getFonction());
    tbxNom.setText(Membre.getNom());
    tbxPrenom.setText(Membre.getPrenom());
    tbxAdresse.setText(Membre.getAdresse());
    tbxCodePostal.setText(Membre.getCp());
    tbxVille.setText(Membre.getVille());
    tbxTelPortable.setText(Membre.getPortable());
    tbxMail.setText(Membre.getEmail());
}



    
}
