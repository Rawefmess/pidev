/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamGui;

import entities.Reclamation;
import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tools.DataSource;
import java.util.regex.Pattern;
/**
 * FXML Controller class
 *
 * @author med amine nsir
 */
public class AjouterReclamationController implements Initializable {
        
    
    private Connection con;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private Button EXIT;
    @FXML
    private Button send;
    
    @FXML
    private TextField email;
    @FXML
    private ComboBox<Reclamation> combobox;
    @FXML
    private TextArea description;

    @FXML
    private TextField iduser;

    
    @FXML
    private TextField titre;
    public User getUserById(int iduser) {
    String sql = "SELECT * FROM user WHERE iduser = ?";

    try (PreparedStatement statement = con.prepareStatement(sql)) {
        statement.setInt(1, iduser);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setIduser(resultSet.getInt("iduser"));
            return user;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions SQL ici, affichez des messages d'erreur si nécessaire
    }

    return null;
}
public boolean validationEmail(){
//
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

        Matcher match = pattern.matcher(email.getText());

        if(match.find() && match.group().equals(email.getText())){

            return true;

        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();

            return false;

        }

    }
public void SEND() {
    con = DataSource.getinstance().getCon();

    String sql = "INSERT INTO reclamation (titre, description, date, iduser, email) VALUES (?, ?, ?, ?, ?)";

    try {
        if (titre.getText().isEmpty() || description.getText().isEmpty() || iduser.getText().isEmpty() || email.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else {
            Reclamation reclamation = new Reclamation();
            reclamation.setTitre(titre.getText());
            reclamation.setDescription(description.getText());
            java.util.Date currentDate = new java.util.Date();
            reclamation.setDate(new Date(currentDate.getTime()));
            
            // Assurez-vous que iduser est un nombre entier valide
            if (iduser.getText().matches("\\d+")) {
                int userId = Integer.parseInt(iduser.getText());
                User user = getUserById(userId);
                
                if (user != null) {
                    reclamation.setU(user);
                    
                    // Valider l'adresse e-mail
                    if (validationEmail(email.getText())) {
                        reclamation.setEmail(email.getText());

                        PreparedStatement prepare = con.prepareStatement(sql);
                        prepare.setString(1, reclamation.getTitre());
                        prepare.setString(2, reclamation.getDescription());
                        prepare.setDate(3, new java.sql.Date(reclamation.getDate().getTime()));
                        prepare.setInt(4, reclamation.getU().getIduser());
                        prepare.setString(5, reclamation.getEmail());
                        prepare.executeUpdate();

                        titre.setText("");
                        description.setText("");
                        iduser.setText("");
                        email.setText("");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Message de réussite");
                        alert.setHeaderText(null);
                        alert.setContentText("Réclamation créée avec succès !");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Message d'erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("L'adresse e-mail n'est pas valide !");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("L'utilisateur avec l'ID spécifié n'existe pas !");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'ID utilisateur doit être un nombre entier valide !");
                alert.showAndWait();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions SQL ici, affichez des messages d'erreur spécifiques si nécessaire
    }
}

public boolean validationEmail(String email) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    Matcher match = pattern.matcher(email);

    return match.find() && match.group().equals(email);
}


     
     public void textfieldDesign1() {
    if (titre.isFocused()) {
        titre.setStyle("-fx-background-color: #fff; -fx-border-width: 2px");
        description.setStyle("-fx-background-color: transparent; -fx-border-width: 1px");
        iduser.setStyle("-fx-background-color: transparent; -fx-border-width: 1px");
    } else if (description.isFocused()) {
        titre.setStyle("-fx-background-color: transparent; -fx-border-width: 1px");
        description.setStyle("-fx-background-color: #fff; -fx-border-width: 2px");
        iduser.setStyle("-fx-background-color: transparent; -fx-border-width: 1px");
    } else if (iduser.isFocused()) {
        titre.setStyle("-fx-background-color: transparent; -fx-border-width: 1px");
        description.setStyle("-fx-background-color: transparent; -fx-border-width: 1px");
        iduser.setStyle("-fx-background-color: #fff; -fx-border-width: 2px");
    }}
     
    
    
    public void EXIT (){
         System.exit(0);
        
    }
private String typeData[] = {"Reclamation", "Incident", "Feedback"};

    public void comboBox() {

        List<String> list = new ArrayList<>();

        for (String data1 : typeData) {

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        combobox.setItems(dataList);

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
