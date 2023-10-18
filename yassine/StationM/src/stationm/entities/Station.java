/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stationm.entities;

import java.util.List;
import stationm.entities.Velo;

/**
 *
 * @author 21655
 */
public class Station {
    private int id_s;
    private String nom_s;
    private String emplacement_s;
    private List<Velo> velos;
    
    public Station (){
        
    }

    public Station(int id_s, String nom_s, String emplacement_s) {
        this.id_s = id_s;
        this.nom_s = nom_s;
        this.emplacement_s=emplacement_s;
       
    }

    public Station(String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    public String getNom_s() {
        return nom_s;
    }

    public void setNom_s(String nom_s) {
        this.nom_s = nom_s;
    }

    public String getEmplacement_s() {
        return emplacement_s;
    }

    public void setEmplacement_s(String emplacement_s) {
        this.emplacement_s = emplacement_s;
    }
    public List<Velo> getVelos() {
        return velos;
    }

    public void setVelos(List<Velo> velos) {
        this.velos = velos;
    }
   
}
