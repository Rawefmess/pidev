/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamGui;

import entities.Reclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceReclamation;
import tools.DataSource;

/**
 * FXML Controller class
 *
 * @author med amine nsir
 */
public class BackreclController implements Initializable {
    private Connection con;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private Button exit;
    @FXML
    private Button afficher;
    @FXML
    private TextField date2;
    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;
    @FXML
    private TextField email2;

    @FXML
    private TableColumn<Reclamation, Date> date;
    @FXML
    private TextField titre2;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TextField description2;
    @FXML
    private TableColumn<Reclamation, String> email;

    @FXML
    private TableColumn<Reclamation, Integer> id;

    @FXML
    private TableColumn<Reclamation, Integer> iduser;
     @FXML
    private TableColumn<Reclamation, String> titre;
    @FXML
private void supprimer(ActionEvent event) {
    Reclamation selectedReclamation = table.getSelectionModel().getSelectedItem(); // Obtenez la réclamation sélectionnée dans la TableView

    if (selectedReclamation != null) {
        try {
            int id = selectedReclamation.getId(); // Obtenez l'ID de la réclamation sélectionnée
            ServiceReclamation.getInstance().supprimer(id); // Appelez la méthode supprimer du service en utilisant l'ID

            // Supprimez la réclamation de la TableView
            table.getItems().remove(selectedReclamation);
        } catch (SQLException ex) {
            ex.printStackTrace(); // Gérez les erreurs en conséquence
        }
    }
}
public void EXIT (){
         System.exit(0);
        
    }
public void selectutilisateur(){

        int num = table.getSelectionModel().getSelectedIndex();

        Reclamation reclamation1 = table.getSelectionModel().getSelectedItem();

        if(num-1 < -1)
            return;

        titre2.setText(String.valueOf(reclamation1.getTitre()));
        description2.setText(reclamation1.getDescription());
        email2.setText(reclamation1.getEmail());
        

    }

    @FXML
private void modifier(ActionEvent event) {
    Reclamation selectedReclamation = table.getSelectionModel().getSelectedItem(); // Obtenez la réclamation sélectionnée dans la TableView

    if (selectedReclamation != null) {
        // Affichez un formulaire ou une boîte de dialogue pour permettre à l'utilisateur de modifier les détails de la réclamation
        // Vous pouvez utiliser un nouveau fichier FXML ou créer un formulaire dans le même fichier FXML existant.
        
        // Une fois que l'utilisateur a modifié les détails, capturez ces modifications.
        
        // Appelez la méthode de service pour mettre à jour la réclamation
        try {
            selectedReclamation.setTitre(titre2.getText()); // Remplacez "nouveauTitre" par le titre modifié
            selectedReclamation.setDescription(description2.getText()); // Remplacez "nouvelleDescription" par la description modifiée
            selectedReclamation.setEmail(email2.getText());
            
            // Assurez-vous de mettre à jour d'autres propriétés si nécessaire
            
            ServiceReclamation.getInstance().modifier(selectedReclamation);
            
            // Rafraîchissez la TableView pour afficher les modifications
            table.refresh();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Gérez les erreurs en conséquence
        }
    }
}

    @FXML
    private void afficher(ActionEvent event) {
    try {
        // Appelez la méthode getAll du service pour obtenir la liste de réclamations
        List<Reclamation> reclamation = ServiceReclamation.getInstance().getAll();
        
        // Créez une liste ObservableList pour la TableView
        ObservableList<Reclamation> observableList = FXCollections.observableArrayList(reclamation);
        
        // Associez la liste observable à la TableView (table)
        table.setItems(observableList);
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        iduser.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        // Assurez-vous que les colonnes de la TableView correspondent aux propriétés de l'objet Reclamation
        // par exemple : id, titre, description, date, etc.
        // Vous pouvez configurer cela dans votre fichier FXML ou dans le code.
        
    } catch (SQLException ex) {
        // Gérez les erreurs en conséquence
    }
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Initializes the controller class.
     */
   
   

    
    
}
