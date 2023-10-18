/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stationm.services;

import static com.oracle.nio.BufferSecrets.instance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.objects.Global.instance;
import stationm.entities.Station;
import stationm.entities.Velo;
import stationm.tools.DataSource;

/**
 *
 * @author 21655
 */
public class ServiceStation implements IserviceStation<Station> {
    private static ServiceStation instance;
    
    Connection cnx ;
    
    public ServiceStation(){
    this.cnx= DataSource.getInstance().getConnection();
}
     public static ServiceStation getInstance(){
        if (instance == null){
            instance = new ServiceStation();            
        }
        return instance;
    }

    @Override
    public void ajouter(Station s) {
       try {
            String req = "INSERT INTO station(nom_s,emplacement_s)values(?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
            
            pre.setString(1,s.getNom_s());
            pre.setString(2,s.getEmplacement_s() );
           
            pre.executeUpdate();
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
    }

    @Override
    public void modifier(Station s) throws SQLException {
        String req = "update station set nom_s = ?, emplacement_s = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setString(1,s.getNom_s());
        ps.setString(2, s.getEmplacement_s());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id_s) throws SQLException {
         String req = "DELETE FROM station WHERE id_s = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, id_s);
    
    ps.executeUpdate();
    }
    
   

    @Override
    public List<Station> getAll(Station s) throws SQLException {
         List<Station> stations = new ArrayList<>();
         
        try {
            String req = "SELECT * FROM Station";

            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
               
                Station station = new Station();
                station.setId_s(rs.getInt("id_s"));
                station.setNom_s(rs.getString("nom_s"));
                station.setEmplacement_s(rs.getString("emplacement_s"));
                List<Velo> velos = getVelosForStation(station.getId_s());
                station.setVelos(velos);
                stations.add(station);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stations;
    }
    private List<Velo> getVelosForStation(int stationId) throws SQLException {
    List<Velo> velos = new ArrayList<>();
    
    try {
        String req = "SELECT * FROM Velo WHERE id_station = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, stationId);
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Velo velo = new Velo();
   
            velo.setId_v(rs.getInt("id_v"));
            velo.setModel(rs.getString("model"));
            velo.setType(rs.getString("type"));
            velo.setStatus(rs.getString("status"));
        
            
            velos.add(velo);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return velos;
}

    }
    

