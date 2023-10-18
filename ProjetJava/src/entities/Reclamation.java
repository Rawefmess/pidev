/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author med amine nsir
 */
public class Reclamation {
     private int id; 
     private String titre;
    private String description;
    private Date date;
    private User u;
    private String email;

    public Reclamation(int aInt, String string, String string0, Date date, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String nom) {
        this.description = nom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Reclamation(int id, String titre, String description, Date date, User u, String email) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.u = u;
        this.email = email;
    }

   

    public Reclamation(int id, String titre, String description, Date date) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
    }

    

    

    

    public Reclamation(String nom) {
        this.description = description;
    }

    public Reclamation() {
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", date=" + date + ", u=" + u + '}';
    }

    

   

    
}
