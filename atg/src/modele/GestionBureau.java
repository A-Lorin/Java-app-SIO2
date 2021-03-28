package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Philippe
 */
public class GestionBureau
{
    // Méthode pour récupérer l'ensemble des Etudiants
    public static ObservableList<MembreBureau> listeBureau()
    {
        ObservableList<MembreBureau> lesMembreBureau = FXCollections.observableArrayList();
        MembreBureau unMembreBureau;
        Connection conn;
        Statement stmt;
        ResultSet rs;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/atg");
        String req;
        // Sélection des étudiants de la base etudiant
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "Select * from bureau";           
            rs = stmt.executeQuery(req);
            while (rs.next())
            {
                
                unMembreBureau = new MembreBureau(rs.getString("id"), rs.getString("fonction"), rs.getString("titre"), rs.getString("nom"),rs.getString("prenom"), rs.getString("adresse"), rs.getString("cp"), rs.getString("ville"), rs.getString("email"), rs.getString("telPortable"));
                lesMembreBureau.add(unMembreBureau);
            }
            stmt.close();
            conn.close();
            return lesMembreBureau;
        }
        catch (Exception e)
        {
            System.out.println("Erreur Requete SQL listeBureau - " + e.getMessage());
            return null;
        }
    }
    
    
    // Méthode pour insérer ou modifier les Etudiants dans la table Etudiant
    public static int  EnregistrerMembreBureau(String pfonction,String ptitre, String pnom,String pprenom,  String padresse, String pcp, String pville ,String pemail,String ptelPortable)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/atg");
        String req;
        int nbLigne  =0;
        // Sélection des différentes spécialités
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "INSERT INTO bureau(fonction,titre, nom, prenom, adresse, cp, ville,email, telPortable) Values ('" + pfonction + "','" + ptitre + "', '" + pnom + "', '" + pprenom + "', '" + padresse + "', '" + pcp + "', '" + pville + "','"+pemail+"','"+ptelPortable+"')";
             nbLigne  = stmt.executeUpdate(req);
            
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Erreur Insert Membre Bureau - " + e.getMessage());
        }
        return nbLigne;
    }
    
    public static int  SupprimerMembreBureau(String id)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/atg");
        String req;
        int nbLigne  =0;
        // Sélection des différentes spécialités
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "DELETE FROM `bureau` WHERE id = "+id+"";
             nbLigne  = stmt.executeUpdate(req);
            
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Erreur Insert Membre Bureau - " + e.getMessage());
        }
        return nbLigne;
    }
    
     public static int  UpdateMembreBureau(String pid,String pfonction,String ptitre, String pnom,String pprenom,  String padresse, String pcp, String pville ,String pemail,String ptelPortable)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/atg");
        String req;
        int nbLigne  =0;
        // Sélection des différentes spécialités
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "UPDATE `bureau` SET `fonction`='"+pfonction+"',`titre`='"+ptitre+"',`nom`='"+pnom+"',`prenom`='"+pprenom+"',`adresse`='"+padresse+"',`cp`='"+pcp+"',`ville`='"+pville+"',`email`='"+pemail+"',`telPortable`='"+ptelPortable+"' WHERE id= '"+pid+"'";
             nbLigne  = stmt.executeUpdate(req);
            
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Erreur Insert Membre Bureau - " + e.getMessage());
        }
        return nbLigne;
    }
}
