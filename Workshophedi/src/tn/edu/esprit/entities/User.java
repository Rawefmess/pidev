/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author Lenovo
 */
public class User {
    
    int id;
    Panier panier;

    public User(int id, Panier panier) {
        this.id = id;
    
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
    
    
    
}
