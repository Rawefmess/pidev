/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.jui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import tn.edu.esprit.entities.Produit;
import tn.edu.esprit.services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.scene.image.Image;

import java.util.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Categorie;
import tn.edu.esprit.services.ServiceCategorie;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class BackController implements Initializable {
    
      @FXML
    private Button ajouterprod;
       

      private File selectedImageFile;


    @FXML
    private TableView<Produit> table;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtidprod;

    @FXML
    private TextField txtnomprod;

    @FXML
    private TextField txtprixprod;

    @FXML
    private TextField txtremise;
    @FXML
    private ComboBox<Categorie> list_categorie;
    @FXML
    private Button addimg;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
  
    @FXML
    void ajouterprod(ActionEvent event) {
    Categorie c = list_categorie.getValue();
    String nomProd = txtnomprod.getText();
    String description = txtdescription.getText();
    String prixProdStr = txtprixprod.getText();
    String remiseStr = txtremise.getText();

    // Check if any of the required fields are empty
    if (nomProd.isEmpty() || description.isEmpty() || prixProdStr.isEmpty() || remiseStr.isEmpty() || c == null) {
        // Display an error alert
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please fill in all required fields.");
        alert.showAndWait();
        return; // Exit the method without proceeding
    }

    // Convert price and remise to integers
    int prixProd = Integer.parseInt(prixProdStr);
    int remise = Integer.parseInt(remiseStr);

    Produit p = new Produit(0, nomProd, description, prixProd, remise, "imageProd", c);

    ServiceProduit sp = new ServiceProduit();
    sp.ajouter(p);

    // Clear the table and refresh it with the updated data
    table.getItems().clear();
    List<Produit> all = new ArrayList<>(sp.afficher());
    for (Produit r : all) {
        table.getItems().add(r);
//    void ajouterprod(ActionEvent event) {
//        
//        
//       Categorie c = list_categorie.getValue();
//        Produit p = new Produit(0, txtnomprod.getText(), txtdescription.getText(),Integer.parseInt(txtprixprod.getText()), Integer.parseInt(txtremise.getText()), "imageProd", c);
//
//        ServiceProduit sp = new ServiceProduit();
//        sp.ajouter(p);
//            table.getItems().clear();
//           
//         List<Produit> all = new ArrayList<Produit>(sp.afficher());
//            for(Produit r : all)
//    {
//       
//        table.getItems().add(r);
//       
   }
    }



    @FXML
    void modifierprod(ActionEvent event) {
        Produit produitAModifier = table.getSelectionModel().getSelectedItem();

    if (produitAModifier != null) {
        try {
            // Récupérer les nouvelles valeurs depuis les champs de texte et le ComboBox
            String nouveauNom = txtnomprod.getText();
            String nouvelleDescription = txtdescription.getText();
            int nouveauPrix = Integer.parseInt(txtprixprod.getText());
            int nouveauRemise = Integer.parseInt(txtremise.getText());
            //int nouvelleRemise = Integer.parseInt(txtremise.getText());
            Categorie nouvelleCategorie = list_categorie.getValue();

            // Mettre à jour les valeurs du produit
            produitAModifier.setNomProd(nouveauNom);
            produitAModifier.setDescriptionProd(nouvelleDescription);
            produitAModifier.setPrixProd(nouveauPrix);
            produitAModifier.setRemise(nouveauRemise);
            produitAModifier.setCategories(nouvelleCategorie);

            // Appeler la fonction de service pour modifier le produit dans la base de données
            ServiceProduit sp = new ServiceProduit();
            sp.modifier(produitAModifier);

            // Mettre à jour l'affichage dans la TableView
            int index = table.getSelectionModel().getSelectedIndex();
            table.getItems().set(index, produitAModifier);
        } catch (NumberFormatException e) {
            // Gérer l'erreur de conversion des valeurs numériques (prix et remise)
            System.err.println("Erreur de conversion : " + e.getMessage());
        }
    } else {
        // Avertissement ou message d'erreur, car aucun produit n'a été sélectionné
        // Vous pouvez afficher un message à l'utilisateur pour lui demander de sélectionner un produit à modifier.
        System.err.println("Aucun produit sélectionné pour la modification.");
    }
        

    }

    @FXML
    void supprimerprod(ActionEvent event) {
        // Récupérez l'élément sélectionné dans la TableView
    Produit produitASupprimer = table.getSelectionModel().getSelectedItem();

    if (produitASupprimer != null) {
        // Appeler la fonction de service pour supprimer le produit de la base de données
        ServiceProduit sp = new ServiceProduit();
        sp.supprimer(produitASupprimer.getIdProduit());

        // Mettre à jour l'affichage en supprimant l'élément de la TableView
        table.getItems().remove(produitASupprimer);
    } else {
        // Avertissement ou message d'erreur, car aucun produit n'a été sélectionné
        // Vous pouvez afficher un message à l'utilisateur pour lui demander de sélectionner un produit à supprimer.
        
    }
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceCategorie sc = new ServiceCategorie();
        list_categorie.getItems().addAll(sc.getAll());
       btn_supprimer.setDisable(true);
        btn_modifier.setDisable(true);
        display();
        // TODO
    }    

    


    private void display() {
        
        table.getItems().clear();
         ServiceProduit sp = new ServiceProduit();
         List<Produit> all = new ArrayList<Produit>(sp.afficher());
         
          TableColumn<Produit, Hyperlink> column2 =
    new TableColumn<>("nomProd");
  column2.setCellValueFactory(
    new PropertyValueFactory<>("nomProd"));
 
         
             TableColumn<Produit, Hyperlink> column3 =
    new TableColumn<>("descriptionProd");
  column3.setCellValueFactory(
    new PropertyValueFactory<>("descriptionProd"));
        
  
       TableColumn<Produit, Hyperlink> column4 =
    new TableColumn<>("prixProd");
  column4.setCellValueFactory(
    new PropertyValueFactory<>("prixProd"));
  
  
       TableColumn<Produit, Hyperlink> column5 =
    new TableColumn<>("remise");
  column5.setCellValueFactory(
    new PropertyValueFactory<>("remise"));  
  
  
   TableColumn<Produit, Hyperlink> column6 =
    new TableColumn<>("Categories");
  column6.setCellValueFactory(
    new PropertyValueFactory<>("Categories"));
  
  
  
  
    table.getColumns().addAll(column5,column3,column4,column2,column6);
    for(Produit r : all)
    {
        System.out.println(r.getCategories().getNomCategorie());
       table.getItems().add(r);
       
    }
  
  
    }


    @FXML
    private void AddImage(ActionEvent event) {
      FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png")
    );

    File selectedImageFile = fileChooser.showOpenDialog(new Stage());

    if (selectedImageFile != null) {
        // Load the image and set it in the ImageView
        Image image = new Image(selectedImageFile.toURI().toString());
        imageView.setImage(image);
    }
    
}
    @FXML
    public void selectproduit(){

        int num = table.getSelectionModel().getSelectedIndex();

        Produit produit1 = table.getSelectionModel().getSelectedItem();

        if(num-1 < -1)
            return;

        txtnomprod.setText(String.valueOf(produit1.getNomProd()));
        txtdescription.setText(produit1.getDescriptionProd());
        txtprixprod.setText(String.valueOf(produit1.getPrixProd()));
        txtremise.setText(String.valueOf(produit1.getRemise()));
        
        list_categorie.getSelectionModel().clearSelection();
        
        
        btn_supprimer.setDisable(false);
        btn_modifier.setDisable(false);
       
        //file_path.setText(utilisateur1.getImage());
        //image = new Image("file:" + utilisateur1.getImage(), 110, 110, false, true);

        //imageview_back.setImage(image);
  
    } 


    //@FXML
   // private void AddImage(ActionEvent event) {
   // }
    
}
