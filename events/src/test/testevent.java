/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import service.Serviceevenement;
import entity.Evenement;
/**
 *
 * @author leith
 */
public class testevent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Evenement p1 = new Evenement(10,"ibtihel",2,"ben mustfa","malhet","zatla");
        Evenement p2 = new Evenement(7,"Anis",3,"fetoui","malhet","britney");
        Evenement p3 = p1;
        System.out.println(p1);
       
        
      Serviceevenement sp = new Serviceevenement();
      //sp.ajouterevenement(p1);
      //sp.ajouterevenement(p2);
      //sp.supprimerevenement(p3);
      sp.modifierevenement(p3);
      System.out.println(sp.affihcerevenement());
    }

}   

