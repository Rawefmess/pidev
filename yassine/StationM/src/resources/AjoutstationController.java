/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import stationm.entities.Station;
import stationm.services.ServiceStation;
import stationm.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class AjoutstationController implements Initializable {
    
    @FXML
    private Button backstation;
    
     @FXML
    private Button ajoutajout;
     
      @FXML
    private Button modifiermodifier;
      
      @FXML
    private TextField nomstation;

    @FXML
    private Button supprimersupprimer;
    
     @FXML
    private TextField emplacementstation;
    
     @FXML
    private AnchorPane map;

    @FXML
    private AnchorPane map0;
    @FXML
    private Button exit;
    @FXML
    private Button confirmerstation;
    @FXML
    private TableColumn<Station,String> emplacement_col;

    @FXML
    private TableColumn<Station, Integer> id_col;
    
    

    @FXML
    private TableColumn<Station, String> nom_col;
    
   

    @FXML
    private TableView<Station> table;
    private Connection cnx;
    private Statement statement;
    private ResultSet result;
    
    @FXML
    public void selectstation(){

        int num = table.getSelectionModel().getSelectedIndex();

        Station station1 = table.getSelectionModel().getSelectedItem();

        if(num-1 < -1)
            return;

        nomstation.setText(String.valueOf(station1.getNom_s()));
        emplacementstation.setText(station1.getEmplacement_s());
        
        
        
    }
    private void modifier(ActionEvent event) {
    Station selectedStation = table.getSelectionModel().getSelectedItem(); // Obtenez la réclamation sélectionnée dans la TableView

    if (selectedStation != null) {
        
        try {
            selectedStation.setNom_s(nomstation.getText()); // Remplacez "nouveauTitre" par le titre modifié
            selectedStation.setEmplacement_s(emplacementstation.getText()); // Remplacez "nouvelleDescription" par la description modifiée

            ServiceStation.getInstance().modifier(selectedStation);
        
            table.refresh();
        } catch (SQLException ex) {
           
            
        }
    }
}
    
    private void supprimer(ActionEvent event) {
    Station selectedStation = table.getSelectionModel().getSelectedItem(); // Obtenez la station sélectionnée dans la TableView

    if (selectedStation != null) {
        try {
            int id = selectedStation.getId_s(); // Obtenez l'ID de la station sélectionnée
            ServiceStation.getInstance().supprimer(id); // Appelez la méthode supprimer du service en utilisant l'ID

             //Supprimez la station de la TableView
            table.getItems().remove(selectedStation);
        } 
        catch (SQLException ex) {
            // Gérez les erreurs en conséquence
            
        }
    }
}
    public void exit(){
        
            System.exit(0);
    }
    
    public ObservableList<Station> liststation(){
        ObservableList<Station> stationList = FXCollections.observableArrayList();
        cnx = DataSource.getInstance().getConnection();
        String sql = "SELECT * FROM station";

        try{
            statement = cnx.createStatement();
            result = statement.executeQuery(sql);
            Station station1;

            while(result.next()){
                station1 = new Station(
                        result.getInt("id_s"),
                        result.getString("nom_s"),
                        result.getString("emplacement_s")
                        
                );

                stationList.add(station1);
            }
        } catch(SQLException e){
        }

        return stationList;
    }
       
       public void showData(){

        ObservableList<Station> show = liststation();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id_s"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom_s"));
        emplacement_col.setCellValueFactory(new PropertyValueFactory<>("emplacement_s"));
        

        table.setItems(show);
        

    }
       public void changepage(){
           if(confirmerstation.isFocused()){
               map0.setVisible(false);
               map.setVisible(true);
           }
          
      
           if(backstation.isFocused()){
               map0.setVisible(true);
               map.setVisible(false);
           }
       }
       
         public void SEND() throws SQLException {
    cnx = DataSource.getInstance().getConnection();

    String sql = "INSERT INTO station (nom_s, emplacement_s) VALUES (?, ?)";

    try {
        if (nomstation.getText().isEmpty() || emplacementstation.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else {
            Station station = new Station();
            station.setNom_s(nomstation.getText());
            station.setEmplacement_s(emplacementstation.getText());
            java.util.Date currentDate = new java.util.Date();
            

            
                PreparedStatement prepare = cnx.prepareStatement(sql);
                prepare.setString(1, station.getNom_s());
                prepare.setString(2, station.getEmplacement_s());
                
                prepare.executeUpdate();

                nomstation.setText("");
                emplacementstation.setText("");
                

               
            
            }
    } catch (SQLException e) {
      
        
    }
    showData();
}
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }
}

 
    
    

        
     
    

