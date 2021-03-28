/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author User
 */
public class Membre
{
     String id,titre, nom, prenom,adresse, cp, ville,dateVersement, email,don, recu,pays, telFixe, telPortable;

    public Membre(String id, String titre, String nom, String prenom, String adresse, String cp, String ville,String dateVersement, String email,String don, String recu)
    {
        this.id = id;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.dateVersement=dateVersement;
        this.email = email;
        this.don=don;
        this.recu = recu;
    }

    public String getDateVersement()
    {
        return dateVersement;
    }

    public void setDateVersement(String dateVersement)
    {
        this.dateVersement = dateVersement;
    }

    public String getDon()
    {
        return don;
    }

    public void setDon(String don)
    {
        this.don = don;
    }

    public Membre()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public String getCp()
    {
        return cp;
    }

    public void setCp(String cp)
    {
        this.cp = cp;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRecu()
    {
        return recu;
    }

    public void setRecu(String recu)
    {
        this.recu = recu;
    }
    
     public Membre(String id, String titre, String nom, String prenom, String adresse, String cp, String ville, String email,String pays,String telFixe,String telPortable,String dateVersement)
    {
        this.id = id;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.email = email;
        this.pays = pays;
        this.telFixe=telFixe;
        this.telPortable=telPortable;
    }

    public String getPays()
    {
        return pays;
    }

    public void setPays(String pays)
    {
        this.pays = pays;
    }

    public String getTelFixe()
    {
        return telFixe;
    }

    public void setTelFixe(String telFixe)
    {
        this.telFixe = telFixe;
    }

    public String getTelPortable()
    {
        return telPortable;
    }

    public void setTelPortable(String telPortable)
    {
        this.telPortable = telPortable;
    }
}
