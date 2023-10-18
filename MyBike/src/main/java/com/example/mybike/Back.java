package com.example.mybike;

import entity.Velo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Back implements Initializable {

    @FXML
    private Button delete_back;

    @FXML
    private ComboBox<?> etat_back;

    @FXML
    private TableColumn<Velo, String> etat_table;

    @FXML
    private TextField id_back;

    @FXML
    private TableColumn<Velo, Integer> id_table;

    @FXML
    private TableColumn<Velo, String> image_table;

    @FXML
    private Button insert_back;

    @FXML
    private ComboBox<?> modele_back;

    @FXML
    private TableColumn<Velo, String> modele_table;

    @FXML
    private TextField prix_back;

    @FXML
    private TableColumn<Velo, Float> prix_table;

    @FXML
    private TextField station_back;

    @FXML
    private TableColumn<Velo, String> station_table;

    @FXML
    private TableView<Velo> table;

    @FXML
    private Button update_back;

    private Connection con;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private String etat[] = {"dispo", "non dispo"};

    public void comboBox(){

        List<String> list = new ArrayList<>();

        for(String data1: etat){

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        etat_back.setItems(dataList);

    }
    private String modele[] = {"electrique", "mecanique"};

    public void comboBox1(){

        List<String> list = new ArrayList<>();

        for(String data1: modele){

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        modele_back.setItems(dataList);

    }



    //    INSERT IMAGE


    public void showData() {
        ServiceVelo sv = new ServiceVelo();
        List<Velo> veloList = sv.afficher(); // Assurez-vous que la méthode afficher retourne une liste de vélos

        id_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        modele_table.setCellValueFactory(new PropertyValueFactory<>("modele"));
        etat_table.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prix_table.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image_table.setCellValueFactory(new PropertyValueFactory<>("image"));
        station_table.setCellValueFactory(new PropertyValueFactory<>("station"));

        ObservableList<Velo> data = FXCollections.observableArrayList(veloList); // Convertir la liste en une liste observable

        table.setItems(data); // Définir les données dans la table
    }

    public void insert() {
        con = MyDB.getinstance().getCon();
        ServiceVelo sv = new ServiceVelo();

        try {
            if ( station_back.getText().isEmpty()
                    || prix_back.getText().isEmpty() || etat_back.getSelectionModel().isEmpty()
                    || modele_back.getSelectionModel().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
            } else {
                //int id = Integer.parseInt(id_back.getText());
                String station = station_back.getText();
                float prix = Float.parseFloat(prix_back.getText());

                // If "etat" and "modele" are ComboBoxes, use getSelectionModel().getSelectedItem() to get the selected values.
                String etat = (String) etat_back.getSelectionModel().getSelectedItem();
                String modele =(String) modele_back.getSelectionModel().getSelectedItem();



                // Create a new Velo object with the values
                Velo velo = new Velo(modele,etat,station,prix);

                sv.ajouter(velo);

                // To immediately display the current data
                showData();
                clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void selectutilisateur(){

        int num = table.getSelectionModel().getSelectedIndex();

        Velo velo1 = table.getSelectionModel().getSelectedItem();

        if(num-1 < -1)
            return;

        id_back.setText(String.valueOf(velo1.getId()));
        station_back.setText(velo1.getStation());
        prix_back.setText(String.valueOf(velo1.getPrix()));
        etat_back.getSelectionModel().clearSelection();
        modele_back.getSelectionModel().clearSelection();


    }
    public void update(Velo veloMisAJour) {
        con = MyDB.getinstance().getCon();
        ServiceVelo sv = new ServiceVelo();

        try {
            if (veloMisAJour != null) {
                // Validez que l'objet mis à jour contient toutes les informations requises
                if (veloMisAJour.getStation() != null && veloMisAJour.getPrix() > 0
                        && veloMisAJour.getEtat() != null && veloMisAJour.getModele() != null) {

                    // Supposons que l'ID est un champ requis pour identifier l'enregistrement à mettre à jour
                    int id = veloMisAJour.getId();
                    String station = veloMisAJour.getStation();
                    float prix = veloMisAJour.getPrix();
                    String etat = veloMisAJour.getEtat();
                    String modele = veloMisAJour.getModele();

                    String sqlMiseAJour = "UPDATE votre_nom_de_table SET station = ?, prix = ?, etat = ?, modele = ? WHERE id = ?";
                    PreparedStatement pre = con.prepareStatement(sqlMiseAJour);
                    pre.setString(1, station);
                    pre.setFloat(2, prix);
                    pre.setString(3, etat);
                    pre.setString(4, modele);
                    pre.setInt(5, id);

                    int lignesModifiees = pre.executeUpdate();

                    if (lignesModifiees > 0) {
                        // Mise à jour réussie
                        showData(); // Rafraîchir les données affichées
                        clear();
                    } else {
                        // Gérez le cas où aucune ligne n'a été mise à jour
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Message d'erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Échec de la mise à jour, aucune ligne modifiée.");
                        alert.showAndWait();
                    }
                } else {
                    // Gérez le cas où tous les champs requis ne sont pas fournis
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez fournir toutes les informations requises.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear(){

        id_back.setText("");
        station_back.setText("");
        prix_back.setText("");
        

        etat_back.getSelectionModel().clearSelection();
        modele_back.getSelectionModel().clearSelection();
        

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox();
        comboBox1();
        showData();

    }
}
