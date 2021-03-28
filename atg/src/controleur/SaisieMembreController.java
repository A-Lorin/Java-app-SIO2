/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import com.jfoenix.controls.JFXAutoCompletePopup;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

import modele.GestionMembre;
import modele.Membre;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class SaisieMembreController implements Initializable
{
    @FXML
    ComboBox cmbCivilite;
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
    TextField tbxPays;
    @FXML
    TextField tbxTelFixe;
    @FXML
    TextField tbxTelPortable;
    @FXML
    TextField tbxMail;
    @FXML
    TextField tbxSomme;
    @FXML
    TextField Idmember;
    @FXML
    DatePicker Ddate;
    @FXML private javafx.scene.control.Button btnInserer;
    ObservableList<Membre> listeMembre;
    JFXAutoCompletePopup<String> autoCompletePopupTbxNom = new JFXAutoCompletePopup<>();
    
    JFXAutoCompletePopup<String> autoCompletePopupTbxPrenom = new JFXAutoCompletePopup<>();
    int MembreAlreadyExist=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tbxPrenom.focusedProperty().addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                        
                    }
                    else
                    {
                        autocomplete();
                    }
                }
            });
        
        tbxNom.focusedProperty().addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                        
                    }
                    else
                    {
                        autocomplete();
                    }
                }
            });
        //
         ArrayList<String> noms = new ArrayList<String>();
         ArrayList<String> prenoms = new ArrayList<String>();
        listeMembre = GestionMembre.listeMembre();
        for( Membre unMembre : listeMembre) {
            noms.add(unMembre.getNom());
            prenoms.add(unMembre.getPrenom());
        }/*
        TextFields.bindAutoCompletion(tbxNom,noms);
        TextFields.bindAutoCompletion(tbxNom,prenoms);*/
        
        //autocomplete tbxnom
        autoCompletePopupTbxNom.getSuggestions().addAll(noms);

        autoCompletePopupTbxNom.setSelectionHandler(event -> {
            tbxNom.setText(event.getObject());

            // you can do other actions here when text completed
        });

    // filtering options
    tbxNom.textProperty().addListener(observable -> {
        autoCompletePopupTbxNom.filter(string -> string.toLowerCase().contains(tbxNom.getText().toLowerCase()));
        if (autoCompletePopupTbxNom.getFilteredSuggestions().isEmpty() || tbxNom.getText().isEmpty()) {
            autoCompletePopupTbxNom.hide();
            // if you remove textField.getText.isEmpty() when text field is empty it suggests all options
            // so you can choose
        } else {
             /*autoCompletePopupTbxNom.setStyle("-fx-control-inner-background:WHITE;"
                + "-fx-accent: #E8EAF6;"
                + "-fx-selection-bar-non-focused:red;"
                + "-fx-font:18px 'Arial'");*/
            autoCompletePopupTbxNom.show(tbxNom);
        }
    });
        //autocompletetbxprenom
         autoCompletePopupTbxPrenom.getSuggestions().addAll(prenoms);

        autoCompletePopupTbxPrenom.setSelectionHandler(event -> {
            tbxPrenom.setText(event.getObject());

            // you can do other actions here when text completed
        });

    // filtering options
    tbxPrenom.textProperty().addListener(observable -> {
        autoCompletePopupTbxPrenom.filter(string -> string.toLowerCase().contains(tbxPrenom.getText().toLowerCase()));
        if (autoCompletePopupTbxPrenom.getFilteredSuggestions().isEmpty() || tbxPrenom.getText().isEmpty()) {
            autoCompletePopupTbxPrenom.hide();
            // if you remove textField.getText.isEmpty() when text field is empty it suggests all options
            // so you can choose
        } else {
             /*autoCompletePopupTbxPrenom.setStyle("-fx-control-inner-background:#35393F;"
                + "-fx-accent: #E8EAF6;"
                + "-fx-selection-bar-non-focused:green;"
                + "-fx-font:12px 'System';");*/
             
            autoCompletePopupTbxPrenom.show(tbxPrenom);
        }
    });
  
        
        //test
        Ddate.setValue(LocalDate.now());
        tbxSomme.setText("15");
        ObservableList lesCivilites = FXCollections.observableArrayList();
        lesCivilites.add("Monsieur");
        lesCivilites.add("Madame");
        cmbCivilite.setItems(lesCivilites);
        cmbCivilite.getSelectionModel().selectFirst();
        
        ////text formatter cp
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
        
        //text formatter tel
        final Pattern patternTel = Pattern.compile("\\d{0,10}");
        TextFormatter<?> formatTelFixe = new TextFormatter<>(changeTelFixe -> {
        if (patternTel.matcher(changeTelFixe.getControlNewText()).matches()) {
        // todo: remove error message/markup
        return changeTelFixe; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });
       
        tbxTelFixe.setTextFormatter(formatTelFixe);
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
        
        //text formatter pays
         final Pattern patternPays = Pattern.compile("[a-zA-Z]{0,4}");
        TextFormatter<?> formatPays = new TextFormatter<>(changePays -> {
        if (patternPays.matcher(changePays.getControlNewText()).matches()) {
        // todo: remove error message/markup
        return changePays; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });
        //
        /*TextFormatter<?> formatPays = new TextFormatter<>(changeformatPays -> {
        if ((tbxPays.getLength())<4) {
        // todo: remove error message/markup
        return changeformatPays; // allow this change to happen
        } else {
            // todo: add error message/markup
            return null; // prevent change
        }
        });*/
        tbxPays.setTextFormatter(formatPays);
        
        //text formatter somme
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {

                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                

                if (t.isAdded()) {
                    if (t.getControlText().contains(".")) {
                        if (t.getText().matches("[^0-9]")) {
                            t.setText("");
                        }
                    } else if (t.getText().matches("[^0-9.]")) {
                        t.setText("");
                    }
                }

                return t;
            }
        };
    tbxSomme.setTextFormatter(new TextFormatter<>(filter));
    //
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
    
    public void AjouterUnMembre()
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
        str=tbxPays.getText().replaceAll(String.valueOf('"'),"");
        tbxPays.setText(str);
        
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
        str=tbxPays.getText().replaceAll(String.valueOf("'"),"");
        tbxPays.setText(str);
        
        /*if(validate(tbxMail.getText()))
        {
            
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Error alert");
            alert.setHeaderText("Adresse mail invalide");
            alert.setContentText("Vous devez entrer une adresse mail valide");

            alert.showAndWait();
        }*/
       

        if(!(Ddate.getValue()==null|tbxSomme.getText().equals(null)|tbxPrenom.getText().equals("")|tbxNom.getText().equals("")|tbxAdresse.getText().equals("")|tbxCodePostal.getText().equals("")|tbxPays.getText().equals("")|tbxVille.getText().equals("")|tbxMail.getText().equals("")|(tbxTelFixe.getText().equals("")&tbxTelPortable.getText().equals(""))))
        {
            int minCotisation=GestionMembre.montantCotisation();
            if(Integer.parseInt(tbxSomme.getText())>=minCotisation)
            {
                if(validate(tbxMail.getText()))
                {
                if(MembreAlreadyExist==0)
                    {
                       int idInserted = GestionMembre.EnregistrerMembre(cmbCivilite.getValue().toString(),tbxNom.getText(),tbxPrenom.getText(),tbxAdresse.getText(),tbxCodePostal.getText(),tbxVille.getText(),tbxPays.getText(),tbxTelFixe.getText(),tbxTelPortable.getText(),tbxMail.getText() );
                        if(idInserted>0)
                        {
                        int nbligne=GestionMembre.enregisterCotisation(idInserted, Ddate.getValue(), Double.parseDouble(tbxSomme.getText()));
                            if(nbligne>0)
                            {
                                showMessageDialog(null, "Ajout effectue avec succès");
                                // get a handle to the stage
                                Stage stage = (Stage) btnInserer.getScene().getWindow();
                                // do what you have to do
                                stage.close();
                            }

                        }
                    }
                  else
                    {
                        if(!GestionMembre.membreDejaDonner(Idmember.getText()))
                        {
                            int nbligne=GestionMembre.enregisterCotisation( Integer.parseInt(Idmember.getText()), Ddate.getValue(), Double.parseDouble(tbxSomme.getText()));
                                if(nbligne>0)
                                {
                                    showMessageDialog(null, "Ajout effectue avec succès");
                                    // get a handle to the stage
                                    Stage stage = (Stage) btnInserer.getScene().getWindow();
                                    // do what you have to do
                                    stage.close();
                                }
                        }
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                              alert.setTitle("Error alert");
                        alert.setHeaderText("Don déja effectuer");
                        alert.setContentText("Ce membre a déjà fait un don cette année, il doit attendre l'année prochaine pour refaire un don");

                        alert.showAndWait();
                        }

                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("Error alert");
                    alert.setHeaderText("Adresse mail invalide");
                    alert.setContentText("Vous devez entrer une adresse mail valide");

                    alert.showAndWait();
                }
                
            }
            else
            {
                 Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error alert");
                alert.setHeaderText("Montant insuffisant");
                alert.setContentText("Le montant entrer est inférieur au montant de la cotisation qui est de "+minCotisation +"€");

                alert.showAndWait();
            }
      
        }
        else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
 
            alert.setTitle("Error alert");
            alert.setHeaderText("Champ(s) vide");
            alert.setContentText("Tous les champs doivent être remplie et au moins un numéro de téléphone doit être entré");

            alert.showAndWait();
        }
       
        
    }
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
}
    
    public void autocomplete()
    {
       String strtbxNom=tbxNom.getText().replaceAll(String.valueOf('"'),"");
       String strtbxPrenom=tbxPrenom.getText().replaceAll(String.valueOf('"'),"");
         strtbxNom=tbxNom.getText().replaceAll(String.valueOf("'"),"");
        strtbxPrenom=tbxPrenom.getText().replaceAll(String.valueOf("'"),"");
        
        
        MembreAlreadyExist=0;
        if(strtbxNom!=null&&strtbxPrenom!=null)
        {
            Membre leMembre=GestionMembre.membreExist(strtbxNom,strtbxPrenom);
            if(leMembre!=null)
            {
                int indexcmbcivilite;
                if(leMembre.getTitre()=="Monsieur")
                {
                    indexcmbcivilite=0;
                }
                else
                {
                    indexcmbcivilite=1;
                }
                cmbCivilite.getSelectionModel().select(indexcmbcivilite);
                tbxNom.setText(leMembre.getNom());
                tbxPrenom.setText(leMembre.getPrenom());
                tbxAdresse.setText(leMembre.getAdresse());
                tbxCodePostal.setText(leMembre.getCp());
                tbxVille.setText(leMembre.getVille());
               
                 tbxPays.setText(leMembre.getPays());
                 
                 
                 tbxTelFixe.setText(leMembre.getTelFixe());
                 tbxTelPortable.setText(leMembre.getTelPortable());
                 tbxMail.setText(leMembre.getEmail());
                 
                Idmember.setText(leMembre.getId());
                autoCompletePopupTbxPrenom.hide();
                autoCompletePopupTbxNom.hide();
                MembreAlreadyExist=1;
            }
            else
            {
                autoCompletePopupTbxNom.getSuggestions().clear();
                autoCompletePopupTbxPrenom.getSuggestions().clear();

                ArrayList<String> noms = new ArrayList<String>();
                ArrayList<String> prenoms = new ArrayList<String>();
                listeMembre = GestionMembre.listeMembreAutocomplete(strtbxNom,strtbxPrenom );
                for( Membre unMembre : listeMembre) {
                    noms.add(unMembre.getNom());
                    prenoms.add(unMembre.getPrenom());
                }/*
                TextFields.bindAutoCompletion(tbxNom,noms);
                TextFields.bindAutoCompletion(tbxNom,prenoms);*/

                autoCompletePopupTbxNom.getSuggestions().addAll(noms);

                autoCompletePopupTbxNom.setSelectionHandler(event -> {
                    tbxNom.setText(event.getObject());
                    

                    // you can do other actions here when text completed
                });

                autoCompletePopupTbxPrenom.getSuggestions().addAll(prenoms);

                    autoCompletePopupTbxPrenom.setSelectionHandler(event -> {
                        tbxPrenom.setText(event.getObject());

                        // you can do other actions here when text completed
                    });

            }
        }
    }
    
    
    
    
}
