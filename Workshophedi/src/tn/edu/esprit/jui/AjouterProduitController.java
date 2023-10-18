/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.jui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private AnchorPane card1;
    @FXML
    private Button ajouterpanier;
    @FXML
    private AnchorPane card11;
    @FXML
    private Button ouvrurpanier;
    @FXML
    private Hyperlink hyper1;
    @FXML
    private Hyperlink nextpage;
    @FXML
    private Button ajouteraupanier;
    @FXML
    private AnchorPane card111;
    @FXML
    private Button ouvrirpanier;
    @FXML
    private Hyperlink hyper11;
    @FXML
    private Hyperlink retourpage;
    @FXML
    private AnchorPane card112;
    @FXML
    private Hyperlink hyper12;
    @FXML
    private Hyperlink retour;
    @FXML
    private AnchorPane card1121;
    @FXML
    private Button ajouteraupanier1;
    @FXML
    private Button ouvrirpanier1;
    @FXML
    private Hyperlink hyper121;
    @FXML
    private Hyperlink nextpage1;
    @FXML
    private Hyperlink retour1;
     
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void ajouterprod(ActionEvent event) {
         try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("Registre.fxml"
                    ));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }

    }


    

