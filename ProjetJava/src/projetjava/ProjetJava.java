/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import entities.User;
import entities.Reclamation;
import entities.Reponse;
import java.sql.Connection;
import java.sql.SQLException;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.List;
import services.ServiceReclamation;
import tools.DataSource;
import java.sql.Date;
import services.ServiceReponse;
import services.ServiceUser;


/**
 *
 * @author med amine nsir
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        String str = "2018-03-21";
        Date date = Date.valueOf(str);
        Connection con;
        con=DataSource.getinstance().getCon();
        
        ServiceUser SU=ServiceUser.getInstance();
        User U =new User();
        SU.ajouter(U);
        
        
        ServiceReclamation sr=ServiceReclamation.getInstance();
        Reclamation R=new Reclamation(8,"rec","aaKKK",date,U,"mofff");
        sr.ajouter(R);
        //sr.modifier(R);
        //sr.supprimer(2);
        /*List<Reclamation> reclamation=new ArrayList<>();
        reclamation=sr.getAll();
        for (int i=0;i<reclamation.size();i++){
            System.out.println(reclamation.get(i));
        */
        /////////////////////////////
        /*ServiceReponse sre=ServiceReponse.getInstance();
        Reponse RE=new Reponse(7,R,"aKK55");
        sre.ajouter(RE);
        //sre.modifier(RE);
        List<Reponse> reponse=new ArrayList<>();
        reponse=sre.recuperer();
        for (int i=0;i<reponse.size();i++){
            System.out.println(reponse.get(i));
        }*/
        }
}
            
        
        
        
    
 
