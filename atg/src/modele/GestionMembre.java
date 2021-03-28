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
public class GestionMembre
{
    // Méthode pour récupérer l'ensemble des Etudiants
    public static ObservableList<Membre> listeMembreSansRecu()
    {
        ObservableList<Membre> lesMembresSReçu = FXCollections.observableArrayList();
        Membre unMembre;
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
            req = "Select * from membres, cotiser where membres.id = cotiser.idMembre and  recuEmail =0";           
            rs = stmt.executeQuery(req);
            while (rs.next())
            {
                unMembre = new Membre(rs.getString("membres.id"), rs.getString("titre"), rs.getString("nom"),rs.getString("prenom"), rs.getString("adresse"), rs.getString("cp"), rs.getString("ville"),rs.getString("dateVersement"), rs.getString("email"), rs.getString("somme_versee"),rs.getString("recuEmail"));
                lesMembresSReçu.add(unMembre);
            }
            stmt.close();
            conn.close();
            return lesMembresSReçu;
        }
        catch (Exception e)
        {
            System.out.println("Erreur Requete SQL listeEtudiants - " + e.getMessage());
            return null;
        }
    }
    
    
    // Méthode pour insérer ou modifier les Etudiants dans la table Etudiant
    public static int EnregistrerMembre(String ptitre, String pnom,String pprenom,  String padresse, String pcp, String pville ,String ppays,String ptelFixe,String ptelPortable, String pemail)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        int id  = 0;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/atg");
        String req;
        // Sélection des différentes spécialités
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "INSERT INTO membres(titre, nom, prenom, adresse, cp, ville, pays, telFixe, telPortable, email) Values ('" + ptitre + "', '" + pnom + "', '" + pprenom + "', '" + padresse + "', '" + pcp + "', '" + pville + "','"+ppays+"','"+ptelFixe+"','"+ptelPortable+"','"+pemail+"'); ";
            
            stmt.executeUpdate(req, stmt.RETURN_GENERATED_KEYS);
            rs=stmt.getGeneratedKeys();
            if(rs.next())
            {
            id=Integer.parseInt(rs.getString(1));
            }
            
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Erreur Insert Etudiant - " + e.getMessage());
        }
        return id;
    }
    
    public static int  UpdateMembreSansRecu(String pid)
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
            req = "UPDATE `cotiser` SET `recuEmail`=1 WHERE idMembre= '"+pid+"' ORDER BY `dateVersement` DESC LIMIT 1";
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
    
    public static int enregisterCotisation(int pid,LocalDate pdate,double sommeVersee )
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        int nbLigne  = 0;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/atg");
        String req;
        // Sélection des différentes spécialités
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            req = "INSERT INTO cotiser(idMembre, dateVersement, somme_versee) Values ('" + pid + "', '" + pdate + "', '" + sommeVersee +"')";
            
            nbLigne  = stmt.executeUpdate(req);
            
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Erreur Insert Etudiant - " + e.getMessage());
        }
        return nbLigne;
    }
    
    public static ObservableList<Membre> listeMembre()
    {
        ObservableList<Membre> lesMembresSReçu = FXCollections.observableArrayList();
        Membre unMembre;
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
            req = "Select * from membres, cotiser where membres.id = cotiser.idMembre";           
            rs = stmt.executeQuery(req);
            while (rs.next())
            {
                unMembre = new Membre(rs.getString("membres.id"), rs.getString("titre"), rs.getString("nom"),rs.getString("prenom"), rs.getString("adresse"), rs.getString("cp"), rs.getString("ville"),rs.getString("dateVersement"), rs.getString("email"), rs.getString("somme_versee"),rs.getString("recuEmail"));
                lesMembresSReçu.add(unMembre);
            }
            stmt.close();
            conn.close();
            return lesMembresSReçu;
        }
        catch (Exception e)
        {
            System.out.println("Erreur Requete SQL listeEtudiants - " + e.getMessage());
            return null;
        }
    }
    
     public static ObservableList<Membre> listeMembreAutocomplete(String pnom,String pprenom )
    {
        ObservableList<Membre> lesMembresSReçu = FXCollections.observableArrayList();
        Membre unMembre;
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
            req = "Select * from membres where  nom like '%"+pnom+"%' and prenom like '%"+pprenom+"%' group by nom";           
            rs = stmt.executeQuery(req);
            while (rs.next())
            {
                unMembre = new Membre(rs.getString("membres.id"), rs.getString("titre"), rs.getString("nom"),rs.getString("prenom"), rs.getString("adresse"), rs.getString("cp"), rs.getString("ville"), rs.getString("email"),rs.getString("pays"),rs.getString("telFixe"),rs.getString("telPortable"),"");
                 lesMembresSReçu.add(unMembre);
            }
            stmt.close();
            conn.close();
            return lesMembresSReçu;
        }
        catch (Exception e)
        {
            System.out.println("Erreur Requete SQL listeEtudiants - " + e.getMessage());
            return null;
        }
    }
     
     
     public static Membre membreExist(String pnom,String pprenom)
             
     {
         Membre unMembre=null;
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
            req = "Select * from membres where  nom like '"+pnom+"' and prenom like '"+pprenom+"'";           
            rs = stmt.executeQuery(req);
            if (rs.next())
            {
              unMembre = new Membre(rs.getString("membres.id"), rs.getString("titre"), rs.getString("nom"),rs.getString("prenom"), rs.getString("adresse"), rs.getString("cp"), rs.getString("ville"), rs.getString("email"),rs.getString("pays"),rs.getString("telFixe"),rs.getString("telPortable"),"");
                 
            }
            stmt.close();
            conn.close();
            return unMembre;
        }
        catch (Exception e)
        {
            System.out.println("Erreur Requete SQL listeEtudiants - " + e.getMessage());
            return null;
        }
     }
     
     public static boolean membreDejaDonner(String pid)
             
     {
        boolean result=false;
        LocalDate anneeActuel = LocalDate.now();
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
            req = "Select * from cotiser where  idMembre like '"+pid+"' and dateVersement like '"+anneeActuel.getYear()+"%'";           
            rs = stmt.executeQuery(req);
            if (rs.next())
            {
              result=true  ;
            }
            stmt.close();
            conn.close();
            return result;
        }
        catch (Exception e)
        {
            System.out.println("Erreur Requete SQL listeEtudiants - " + e.getMessage());
            return result;
        }
     }
     
      public static int montantCotisation()
             
     {
         int montant=0;
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
            req = "Select * from cotisation";           
            rs = stmt.executeQuery(req);
            if (rs.next())
            {
              montant= Integer.parseInt(rs.getString("montant")) ;
            }
            stmt.close();
            conn.close();
            return montant;
        }
        catch (Exception e)
        {
            System.out.println("Erreur Requete SQL listeEtudiants - " + e.getMessage());
            return montant;
        }
     }
}
