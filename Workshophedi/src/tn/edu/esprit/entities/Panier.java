/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.edu.esprit.entities;

import java.util.List;

public class Panier {
    private int idPanier;
    private float prixTot;
    private int quantite; // Correction de la variable quantite
    private List<Produit> produits;

    

    public Panier(int idPanier, float prixTot, int etat, int quantite, List<Produit> produits) {
        this.idPanier = idPanier;
        this.prixTot = prixTot;
        this.quantite = quantite;
        this.produits = produits;
    }

    public Panier() {
    }

    public Panier(int idPanier) {
        this.idPanier = idPanier;
    }

       

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public float getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(float prixTot) {
        this.prixTot = prixTot;
    }

  

   

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public void setQantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
