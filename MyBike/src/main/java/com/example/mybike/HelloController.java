package com.example.mybike;

import entity.bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button reserver1;

    @FXML
    private Button reserver2;

    @FXML
    private Button reserver3;

    @FXML
    private AnchorPane page1;

    @FXML
    private AnchorPane page2;
    @FXML
    private Label description_label;




    public void changepages(){
        if(reserver1.isFocused()){
            page1.setVisible(false);
            page2.setVisible(true);
        }
        if(reserver2.isFocused()){
            page1.setVisible(false);
            page2.setVisible(true);
        }
        if(reserver3.isFocused()){
            page1.setVisible(false);
            page2.setVisible(true);
        }

    }
    public void account(){
        description_label.setText(bike.modele);

    }
    public void exit(){
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account();

    }
}