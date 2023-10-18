/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.PreparedStatement;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import entities.User;

/**
 *
 * @author med amine nsir
 */
public class ServiceReclamation implements IServiceReclamation<Reclamation>{
private static ServiceReclamation instance;
PreparedStatement preparedstatement;
Connection con;
 Statement ste;

public ServiceReclamation(){
    con =tools.DataSource.getinstance().getCon();
    
    
}
public static ServiceReclamation getInstance(){
    if (instance == null){
        instance = new ServiceReclamation();
    }     
    return instance;

}
    @Override
    public void ajouter(Reclamation r) {
         try {
            String req = "INSERT INTO reclamation(id,titre,description,date,iduser,email)values(?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,r.getId() );
            pre.setString(2,r.getTitre());
            pre.setString(3,r.getDescription() );
            pre.setDate(4, r.getDate());
            pre.setInt(5,r.getU().getIduser());
            pre.setString(6,r.getEmail());
            pre.executeUpdate();
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
    }

    @Override
    public void modifier(Reclamation r) throws SQLException{
        String req = "update reclamation set  titre=? ,description=? ,date=? ,iduser=?,email=? where id=?";
        PreparedStatement ps = con.prepareStatement(req);

        ps.setString(1, r.getTitre());
        ps.setString(2,r.getDescription());
        ps.setDate(3, r.getDate());
        ps.setInt(4, r.getU().getIduser());
        ps.setInt(5, r.getId());
        ps.setString(6,r.getEmail());
        ps.executeUpdate();

    }
    


   

    /**
     *
     * @param id
     * @throws SQLException
     */
    @Override
    public void supprimer(int id) throws SQLException {
         String req = "DELETE FROM reclamation WHERE id = ?";
         PreparedStatement ps = con.prepareStatement(req);
         ps.setInt(1, id);
    
    ps.executeUpdate();
    }


    

    @Override
    public Reclamation getOne(Reclamation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public List<Reclamation> getAll(Reclamation r)throws SQLException {
        
        List<Reclamation> commandes = new ArrayList<>();
        String req = "select * from reclamation";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        Reclamation rec = new Reclamation();
        rec.setid(rs.getInt("idrec"));
        rec.setdescription(.valueOf(rs.getString("type")));

            commandes.add(com);
        }
        return reclamation;
    }
    List<Reclamation> reclamation = new ArrayList<>();
        String req = "select * from reclamation";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        Reclamation rec = new Reclamation();
        rec.setid(rs.getInt("idrec"));
        rec.setdescription(.valueOf(rs.getString("type")));

            reclamation.add(rec);
        }
        return reclamation;
    */

    /*@Override
    public List<Reclamation> getAll(Reclamation r) throws SQLException{
        List<Reclamation>  = new ArrayList<>();
        String sql ="select * from reclamation";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Reclamation r = new Reclamation(rs.getInt("id"),
                        rs.getString("nom"), rs.getString(2));
                reclamation.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    } 
    */

    @Override
    public List<Reclamation> getAll() throws SQLException {
       
        List<Reclamation> listReclamation=new ArrayList<>();
        String req = "SELECT rec.id AS reclamation_id,rec.description,rec.iduser,rec.titre,rec.date,rec.email"
               + " FROM reclamation rec "
               + "JOIN user u ON rec.iduser = u.iduser ";
        try{
        ste= con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                User user = new User(
                rs.getInt("iduser")
                );
                Reclamation reclamation = new Reclamation(
                rs.getInt("reclamation_id"),
                rs.getString("titre"),
                rs.getString("description"),
                rs.getDate("date"),
                user,
                rs.getString("email")
                );
                
            listReclamation.add(reclamation);
             }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listReclamation;
    }
        
    
    
}
    

/*List<Reclamation> reclamation = new ArrayList<>();
        String req = "select * from reclamation";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        Reclamation rec = new Reclamation();
        rec.setId(rs.getInt("id"));
        rec.setTitre(rs.getString("titre"));
        rec.setDescription(rs.getString("description"));
        rec.setDate(rs.getDate("date"));
        rec.setIduser(rs.getInt("iduser"));
        

            reclamation.add(rec);
        }
        return reclamation;
    } */
