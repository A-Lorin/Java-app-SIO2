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
public class MembreBureau 
{
    
    String id,fonction,titre, nom, prenom,adresse, cp, ville, email, portable;
    

    public MembreBureau(String id, String fonction,String titre, String nom, String prenom, String adresse, String cp, String ville, String email, String portable)
    {
        this.id = id;
        this.fonction = fonction;
        this.titre=titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.email = email;
        this.portable = portable;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public MembreBureau()
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

    public String getFonction()
    {
        return fonction;
    }

    public void setFonction(String fonction)
    {
        this.fonction = fonction;
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

    public String getPortable()
    {
        return portable;
    }

    public void setPortable(String portable)
    {
        this.portable = portable;
    }
}
