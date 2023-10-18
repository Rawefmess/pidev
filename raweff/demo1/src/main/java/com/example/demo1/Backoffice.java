package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Backoffice implements Initializable {

    @FXML
    private Circle circle_back;
    @FXML
    private Label hello;
    @FXML
    private Button home_back;
    @FXML
    private Button edit_back;
    @FXML
    private AnchorPane admin_page;
    @FXML
    private Label file_path;
    @FXML
    private ImageView imageview_back;

    @FXML
    private AnchorPane backoffice1;

    @FXML
    private Button clear_back;

    @FXML
    private DatePicker date_back;

    @FXML
    private TableColumn<utilisateur, String> date_col;

    @FXML
    private Button delete_back;

    @FXML
    private TextField email_back;

    @FXML
    private TableColumn<utilisateur, String> email_col;

    @FXML
    private ComboBox<?> gender_back;

    @FXML
    private TableColumn<utilisateur, String> gender_col;

    @FXML
    private TextField id_back;

    @FXML
    private TableColumn<utilisateur, Integer> id_col;

    @FXML
    private Button image_back;

    @FXML
    private TableColumn<utilisateur, String> image_col;

    @FXML
    private Button insert_back;

    @FXML
    private TextField password_back;

    @FXML
    private TableColumn<utilisateur, String> password_col;

    @FXML
    private ComboBox<String> role_back;

    @FXML
    private TableColumn<utilisateur, String> role_col;

    @FXML
    private TableView<utilisateur> table_view;

    @FXML
    private Button update_back;

    @FXML
    private TextField username_back;

    @FXML
    private TableColumn<utilisateur, String> username_col;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private Image image;

    private String genderData[] = {"Male", "Female", "Others"};

    public void comboBox(){

        List<String> list = new ArrayList<>();

        for(String data1: genderData){

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        gender_back.setItems(dataList);

    }

    private String role[] = {"admin", "client", "employee"};

    public void comboBox1(){

        List<String> list = new ArrayList<>();

        for(String data1: role){

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        role_back.setItems(dataList);

    }

    public void insert() {
        connect = database.connectDb();

        String sql = "INSERT INTO utilisateur (username, password, email, image, id, gender, date,role) VALUES (?,?,?,?,?,?,?,?)";

        try {
            if (id_back.getText().isEmpty() || username_back.getText().isEmpty()
                    || password_back.getText().isEmpty()
                    || email_back.getText().isEmpty()
                    || gender_back.getSelectionModel().isEmpty()
                    || role_back.getSelectionModel().isEmpty()
                    || date_back.getValue() == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();

            } else {
                String file = file_path.getText().replace("\\", "\\\\");

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username_back.getText());
                prepare.setString(2, password_back.getText());
                prepare.setString(3, email_back.getText());
                prepare.setString(4, file);
                prepare.setString(5, id_back.getText());
                prepare.setString(6, (String)gender_back.getSelectionModel().getSelectedItem()); // Utilisez getValue() pour obtenir la valeur sélectionnée du DatePicker
                prepare.setString(7, date_back.getValue().toString());
                prepare.setString(8, (String)role_back.getSelectionModel().getSelectedItem());// Utilisez getValue() pour obtenir la date depuis DatePicker

                prepare.executeUpdate();

                // Pour afficher immédiatement les données actuelles
                showData();
                clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear(){

        id_back.setText("");
        username_back.setText("");
        password_back.setText("");
        email_back.setText("");

        gender_back.getSelectionModel().clearSelection();
        role_back.getSelectionModel().clearSelection();
        file_path.setText("");
        imageview_back.setImage(null);
        date_back.setValue(null);

    }

    public void selectutilisateur(){

        int num = table_view.getSelectionModel().getSelectedIndex();

        utilisateur utilisateur1 = table_view.getSelectionModel().getSelectedItem();

        if(num-1 < -1)
            return;

        id_back.setText(String.valueOf(utilisateur1.getId()));
        username_back.setText(utilisateur1.getUsername());
        password_back.setText(utilisateur1.getPassword());
        email_back.setText(utilisateur1.getEmail());
        gender_back.getSelectionModel().clearSelection();
        role_back.getSelectionModel().clearSelection();
        file_path.setText(utilisateur1.getImage());
        image = new Image("file:" + utilisateur1.getImage(), 110, 110, false, true);

        imageview_back.setImage(image);

    }
    public void exit() {

        System.exit(0);

    }
    public void update(){

        connect = database.connectDb();

        String replace = file_path.getText().replace("\\","\\\\");

        String sql = "UPDATE utilisateur SET `username` = '" + username_back.getText()
                + "', `password` = '" + password_back.getText()
                + "', `email` = '" + email_back.getText()
                + "', `image` = '" + replace
                + "', `gender` = '"
                + gender_back.getSelectionModel().getSelectedItem()
                + "', `date` = '"
                +date_back.getValue().toString()
                + "', `role` = '"
                + role_back.getSelectionModel().getSelectedItem()

                + "' WHERE `id` ='" + id_back.getText() + "'";

        try{
            if(id_back.getText().isEmpty() | username_back.getText().isEmpty()
                    | email_back.getText().isEmpty()
                    | password_back.getText().isEmpty()

                    | gender_back.getSelectionModel().isEmpty()
                    | role_back.getSelectionModel().isEmpty())
                    {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();

            }else{

                statement = connect.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("rawef Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                showData();
                clear();

            }
        }catch(Exception e){}

    }
    public void delete(){

        connect = database.connectDb();

        String sql = "DELETE FROM utilisateur WHERE `id` ='" + id_back.getText() + "'";

        try{

            if(id_back.getText().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter your Student ID");
                alert.showAndWait();

            }else{

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");

                Optional<ButtonType> option = alert.showAndWait();

                if(option.get() != ButtonType.OK)
                    return;
                else{

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                }

                showData();
                clear();

            }

        }catch(Exception e){}

    }

    public ObservableList<utilisateur> listutilisateur(){
        ObservableList<utilisateur> utilisateurList = FXCollections.observableArrayList();
        connect = database.connectDb();
        String sql = "SELECT * FROM utilisateur";

        try{
            statement = connect.createStatement();
            result = statement.executeQuery(sql);
            utilisateur utilisateur1;

            while(result.next()){
                utilisateur1 = new utilisateur(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role"),
                        result.getString("gender"),
                        result.getString("image"),
                        result.getString("date")
                );

                utilisateurList.add(utilisateur1);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return utilisateurList;
    }

    //    INSERT IMAGE


    public void showData(){

        ObservableList<utilisateur> show = listutilisateur();

        username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        password_col.setCellValueFactory(new PropertyValueFactory<>("password"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        image_col.setCellValueFactory(new PropertyValueFactory<>("image"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("role"));

        table_view.setItems(show);

    }

    public void changePage(){

        if(home_back.isFocused()) {

            backoffice1.setVisible(true);
            admin_page.setVisible(false);

        }
        if(edit_back.isFocused()) {
            backoffice1.setVisible(false);
            admin_page.setVisible(true);

        }}
    public void account() {

        hello.setText(user.username);

    }
    public void displayImage() {

//        MAKE SURE THAT YOU DIDNT FORGET THE "file:"
        String uri = "file:" + user.path;

        image = new Image(uri, 150, 150, false, true);

        circle_back.setFill(new ImagePattern(image));



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData();
        comboBox();
        comboBox1();
        account();
        displayImage();

    }
}
