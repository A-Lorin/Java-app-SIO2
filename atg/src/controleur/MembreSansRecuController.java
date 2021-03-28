/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import static java.awt.SystemColor.text;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.swing.JOptionPane.showMessageDialog;
import modele.GestionBureau;
import modele.GestionMembre;
import modele.Membre;
import modele.MembreBureau;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import java.util.Properties;

//
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MembreSansRecuController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    ObservableList<Membre> listeMembreSansRecu;
    ObservableList<MembreBureau> lesMembreBureau;
    
    @FXML
    private TableView<Membre> MembresRecuTableview;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCID;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCTitre;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCNom;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCPrenom;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCAdresse;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCCp;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCVille;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCEmail;
    
    @FXML
    private TableColumn<Membre, String> MembresRecuTableviewCRecu;
    
    @FXML
    ComboBox<MembreBureau> cmbFonctionSignataire;

Callback<ListView<MembreBureau>, ListCell<MembreBureau>> factory = lv -> new ListCell<MembreBureau>() {

    @Override
    protected void updateItem(MembreBureau item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getFonction());
    }

};


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lesMembreBureau = GestionBureau.listeBureau();
        cmbFonctionSignataire.setItems(lesMembreBureau);
        cmbFonctionSignataire.getSelectionModel().selectFirst();
        cmbFonctionSignataire.setCellFactory(factory);
        cmbFonctionSignataire.setButtonCell(factory.call(null));
        
         listeMembreSansRecu = GestionMembre.listeMembreSansRecu();
         
        MembresRecuTableview.setItems(listeMembreSansRecu);
        
        
        
        MembresRecuTableviewCID.setCellValueFactory(new PropertyValueFactory<Membre, String>("id"));
        MembresRecuTableviewCTitre.setCellValueFactory(new PropertyValueFactory<Membre, String>("titre"));
        MembresRecuTableviewCNom.setCellValueFactory(new PropertyValueFactory<Membre, String>("nom"));
        MembresRecuTableviewCPrenom.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        MembresRecuTableviewCAdresse.setCellValueFactory(new PropertyValueFactory<Membre, String>("adresse"));
        MembresRecuTableviewCCp.setCellValueFactory(new PropertyValueFactory<Membre, String>("cp"));
        MembresRecuTableviewCVille.setCellValueFactory(new PropertyValueFactory<Membre, String>("ville"));
        MembresRecuTableviewCEmail.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        MembresRecuTableviewCRecu.setCellValueFactory(new PropertyValueFactory<Membre, String>("recu"));
    }    
    
    public void creePDF() throws AddressException, NoSuchProviderException, MessagingException
    {
            
            if(MembresRecuTableview.getSelectionModel().getSelectedItem()!=null )
            {
                MembreBureau signataire=cmbFonctionSignataire.getSelectionModel().getSelectedItem();
                   LocalDate anneeActuel = LocalDate.now();
                    Membre unMembre=MembresRecuTableview.getSelectionModel().getSelectedItem();
                 try{

                String fileName = "Reçufiscaux/ReçuFiscal_"+unMembre.getPrenom()+"_"+unMembre.getNom()+".pdf"; // name of our file

                PDDocument doc = new PDDocument();
                PDPage page = new PDPage();

                doc.addPage(page);
                //Creating PDImageXObject object
                String imageName = "logo.jpg";
                PDXObjectImage image = new PDJpeg(doc, new FileInputStream(imageName));
       
                PDPageContentStream content = new PDPageContentStream(doc, page);
                
                 //Drawing the image in the PDF document
                content.drawImage(image, 80, 640);
                
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 26);
                content.moveTextPositionByAmount(250, 688);
                content.drawString("REÇU FISCAL "+anneeActuel.getYear());
                content.endText();





                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.moveTextPositionByAmount(80,620);
                content.drawString("Je soussignée "+signataire.getPrenom()+" "+signataire.getNom()+", "+signataire.getFonction()+" de l'association AUTISME TREGOR GOËLO");
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(80,580);
                content.drawString("Déclare avoir reçu de "+unMembre.getTitre()+" "+unMembre.getPrenom()+" "+unMembre.getNom());
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(178,560);
                content.drawString(unMembre.getAdresse());
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(178,540);
                content.drawString(unMembre.getCp()+" "+unMembre.getVille());
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(80,520);
                content.drawString("un don d'une valeur de "+unMembre.getDon()+" euros le "+unMembre.getDateVersement()+".");
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(80,470);
                content.drawString("À Lannion,");
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(80,450);
                content.drawString(signataire.getPrenom()+" "+signataire.getNom());
                content.endText();


                content.close();
                doc.save(fileName);
                doc.close();


                }
                catch(IOException | COSVisitorException e)
                {

                System.out.println(e.getMessage());

                }
                 showMessageDialog(null, "PDF crée avec succès");
                 /*                                         */
                String to = unMembre.getEmail();
                String subject = "Reçu fiscal";
                String msg ="Bonjour "+unMembre.getTitre()+" "+unMembre.getPrenom()+",\n \n vous trouverez ci-joint le reçu fiscal correspondant à votre don \n Merci pour votre don. \n \n  "+signataire.getPrenom()+" "+signataire.getNom();
                final String from ="";
                final  String password ="";


                Properties props = new Properties();  
                props.setProperty("mail.transport.protocol", "smtp");     
                props.setProperty("mail.host", "smtp.laposte.net");  
                props.put("mail.smtp.auth", "true");  
                props.put("mail.smtp.port", "465");  
                props.put("mail.debug", "true");  
                props.put("mail.smtp.socketFactory.port", "465");  
                props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
                props.put("mail.smtp.socketFactory.fallback", "false");  
                Session session = Session.getDefaultInstance(props,  
                new javax.mail.Authenticator() 
                {
                   @Override
                   protected PasswordAuthentication getPasswordAuthentication() 
                   {  
                   return new PasswordAuthentication(from,password);  
                   }  
               });  

               //session.setDebug(true);  
               Transport transport = session.getTransport();  
               InternetAddress addressFrom = new InternetAddress(from);  

               MimeMessage message = new MimeMessage(session);  
               message.setSender(addressFrom);  
               message.setSubject(subject);  
               message.setContent(msg, "text/plain");  
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  
               
               
               // Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();

                      // Create a multipar message
                Multipart multipart = new MimeMultipart();
                messageBodyPart.setText(msg);
                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                String filename = "Reçufiscaux/ReçuFiscal_"+unMembre.getPrenom()+"_"+unMembre.getNom()+".pdf";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                message.setContent(multipart);

                transport.connect();  
                Transport.send(message);  
                transport.close();
                   //edit bdd
                 GestionMembre.UpdateMembreSansRecu(unMembre.getId());
                   
        
                  
            }
            else
            {
                 Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error alert");
                alert.setHeaderText("Aucun membre selectionné");
                alert.setContentText("Vous devez selectionnez un membre avant de cliquer sur le boutton édition");

                alert.showAndWait();
            }
    }
    
    
    public void initialize()
    {
        listeMembreSansRecu = GestionMembre.listeMembreSansRecu();
        MembresRecuTableview.setItems(listeMembreSansRecu);
    }    
    
    public void test() {

      try {

        if ((new File("Reçufiscaux/ReçuFiscal_Lionel_GERMAIN.pdf")).exists()) {

            Process p = Runtime
               .getRuntime()
               .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\User\\Documents\\NetBeansProjects\\atg\\atg\\Reçufiscaux\\ReçuFiscal_Lionel_GERMAIN.pdf");
            p.waitFor();

        } else {

            System.out.println("File is not exists");

        }

        System.out.println("Done");

      } catch (Exception ex) {
        ex.printStackTrace();
      }

    }
    
}
