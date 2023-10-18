package com.example.demo1;
import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {

    @FXML
    private CheckBox show_password;
    @FXML
    private Hyperlink create_account;

    @FXML
    private TextField email;

    @FXML
    private Hyperlink login;

    @FXML
    private Button login_btn;

    @FXML
    private ImageView mybike;

    @FXML
    private ImageView mybike1;

    @FXML
    private AnchorPane verificationbikeform;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private Button signup_bttn;

    @FXML
    private TextField username;

    @FXML
    private TextField username1;

    @FXML
    private AnchorPane login_form;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private AnchorPane signup_page;

    @FXML
    private TextField code;

    @FXML
    private Button confirm;

    @FXML
    private AnchorPane verificationform;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;


    public void exit(){

        System.exit(0);

    }

    public void textfieldDesign(){
        if(username.isFocused()){

            username.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

            password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        }else if(password.isFocused()){

            username.setStyle("-fx-background-color:transparent;"
                    + "-f-border-width:1px");

            password.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

        }

    }
    public void textfieldDesign1(){

        if(username1.isFocused()){

            username1.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

            password1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            password1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        }else if(email.isFocused()){

            username1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            password1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            email.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

        }else if(password1.isFocused()){

            username1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            password1.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

            email.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        }

    }
    public void dropShadowEffect(){

        DropShadow original = new DropShadow(30, Color.valueOf("#ae44a5"));

        original.setRadius(30);

        mybike.setEffect(original);

        mybike1.setEffect(original);

        mybike.setOnMouseEntered((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));

            mybike.setCursor(Cursor.HAND);
            mybike.setStyle("-fx-text-fill:#ee5fe4");
            mybike.setEffect(shadow);

        });

        mybike.setOnMouseExited((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(20, Color.valueOf("#ae44a5"));

            shadow.setRadius(30);

            mybike.setStyle("-fx-text-fill:#000");
            mybike.setEffect(shadow);

        });

        mybike1.setOnMouseEntered((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));

            mybike1.setCursor(Cursor.HAND);
            mybike1.setStyle("-fx-text-fill:#ee5fe4");
            mybike1.setEffect(shadow);

        });

        mybike1.setOnMouseExited((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(20, Color.valueOf("#ae44a5"));

            shadow.setRadius(30);

            mybike1.setStyle("-fx-text-fill:#000");
            mybike1.setEffect(shadow);

        });

    }
    public void changeForm(ActionEvent event){

        if(event.getSource() == create_account){

            signup_form.setVisible(true);
            login_form.setVisible(false);

            username1.setText("");
            password1.setText("");
            email.setText("");

        }else if(event.getSource() == login){

            login_form.setVisible(true);
            signup_form.setVisible(false);

            username.setText("");
            password.setText("");

        }else if(event.getSource() == confirm){

            login_form.setVisible(true);
            verificationbikeform.setVisible(false);
            verificationform.setVisible(false);

            username.setText("");
            password.setText("");

        }


    }


    public void login() {
        connect = database.connectDb();
        String sql = "SELECT * FROM utilisateur WHERE username = ? and password = ?";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            if (result.next()) {
                user.username = result.getString("username");
                user.path = result.getString("image");
                user.date = result.getString("date");
                user.role = result.getString("role");
                user.email = result.getString("email");
                user.gender = result.getString("gender");

                login_btn.getScene().getWindow().hide();

                if (isAdminEmail(user.email)) {
                    loadScene("backoffice.fxml");
                } else {
                    loadScene("dashboard.fxml");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Nom d'utilisateur/mot de passe incorrect !");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadScene(String fxmlFileName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
                stage.setOpacity(0.8);
            });
            root.setOnMouseReleased(event -> stage.setOpacity(1));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private String codeDeVerification;
    public String genererCodeVerification() {
        // Définir la longueur du code de vérification (par exemple, 6 chiffres)
        int longueurCode = 6;

        String caracteresPossibles = "0123456789";

        // Créer un objet Random
        Random random = new Random();

        StringBuilder codeVerification = new StringBuilder();


        for (int i = 0; i < longueurCode; i++) {

            int indiceAleatoire = random.nextInt(caracteresPossibles.length());

            codeVerification.append(caracteresPossibles.charAt(indiceAleatoire));
        }

        // Convertir le code de vérification en chaîne et le renvoyer
         codeDeVerification=codeVerification.toString();
        return codeDeVerification;
    }
    public void sendVerificationCodeByEmail(String destinataire, String codeDeVerification, String usernameGmail, String passwordGmail) {
        final String username = usernameGmail; // Utilisez votre adresse e-mail Gmail ici
        final String password = passwordGmail; // Utilisez votre mot de passe Gmail ici
        String host = "smtp.gmail.com"; // Serveur SMTP de Gmail
        int port = 587; // Port SMTP de Gmail (587 est le port TLS)

        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            });

            // Créez le contenu HTML de l'e-mail
            String htmlContent = "<html><body>";
            htmlContent += "<table style=\"width:538px;background-color:#393836\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\">";
            htmlContent +="<tbody><tr>";
            htmlContent +="<td style=\"height:65px;background-color:#171a21;border-bottom:1px solid #4d4b48\">\n" +
                    "\t\t\t<img src=\"\" width=\"538\" height=\"65\" alt=\"Steam\" class=\"CToWUd\" data-bit=\"iit\">\n" +
                    "\t\t</td>";
            htmlContent += "</tr>";
            htmlContent +="<tr>";
            htmlContent +="<td bgcolor=\"#17212e\">\n" +
                    "\t\t\t<table width=\"470\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left:5px;padding-right:5px;padding-bottom:10px\">\n" +
                    "\n" +
                    "\t\t\t\t<tbody><tr bgcolor=\"#17212e\">\n" +
                    "\t\t\t\t\t<td style=\"padding-top:32px\">\n" +
                    "\t\t\t\t\t<span style=\"font-size:24px;color:#66c0f4;font-family:Arial,Helvetica,sans-serif;font-weight:bold\">\n" +
                    "\t\t\t\t\t\tHello \t\t\t\t\t</span><br>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t</tr>\n" +
                    "\n" +
                    "\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t<td style=\"padding-top:12px;font-size:17px;color:#c6d4df;font-family:Arial,Helvetica,sans-serif;font-weight:bold\">\n" +
                    "\t\t\t\t\t\tHere is the <span class=\"il\">code</span> you need to sign up \t\t\t\t\t</td>\n" +
                    "\t\t\t\t</tr>\n" +
                    "\n" +
                    "\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t<td style=\"padding-top:24px;padding-bottom:24px\">\n" +
                    "\t\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t\t<span style=\"font-size:24px;color:#66c0f4;font-family:Arial,Helvetica,sans-serif;font-weight:bold\">"+codeDeVerification+"</span>\n" +
                    "\t\t\t\t\t\t\t\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t</tr>\n" +
                    "\n" +
                    "\t\t\t\t<tr bgcolor=\"#121a25\">\n" +
                    "\t\t\t\t\t<td style=\"padding:20px;font-size:12px;line-height:17px;color:#c6d4df;font-family:Arial,Helvetica,sans-serif\">\n" +
                    "\t\t\t\t\t\tIf you are not trying to change your <span class=\"il\">BIKE</span> login credentials, please ignore this email. It is possible that another user entered their login information incorrectly.\t\t\t\t\t</td>\n" +
                    "\t\t\t\t</tr>\n" +
                    "\n" +
                    "\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t<td style=\"font-size:12px;color:#6d7880;padding-top:16px;padding-bottom:60px\">\n" +
                    "\t\t\t\t\t\tThe <span class=\"il\">Bikee</span> Support Team<br>\n" +
                    "\t\t\t\t\t\t<a style=\"color:#8f98a0\" href=\"bike.esprit@esprit.tn/\" target=\"_blank\" ;/</a><br>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t</tr>\n" +
                    "\n" +
                    "\t\t\t</tbody></table>\n" +
                    "\t\t</td>\n" +
                    "\t</tr>";
            htmlContent += "</body></html>";


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
            message.setSubject("SIGN UP TO MYBIKE");
            message.setContent(htmlContent, "text/html"); // Définir le contenu comme HTML

            Transport.send(message);
            System.out.println("E-mail de vérification envoyé avec succès à : " + destinataire);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi de l'e-mail de vérification : " + e.getMessage());
        }
    }

    public void signup() {


        String usernameText = username1.getText();
        String passwordText = password1.getText();
        String emailText = email.getText();

        if (usernameText.isEmpty() || passwordText.isEmpty() || emailText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else if (passwordText.length() < 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe invalide (doit avoir au moins 8 caractères) !");
            alert.showAndWait();
        } else {
            if (validationEmail()) {

                signup_page.setVisible(false);
                verificationform.setVisible(true);

                codeDeVerification = genererCodeVerification();

                // Utilisez votre nom d'utilisateur Gmail et votre mot de passe Gmail ici
                String usernameGmail = "bike.esprit@gmail.com";
                String passwordGmail = "zkhd ivmz qysp tuwi";

                sendVerificationCodeByEmail(emailText, codeDeVerification, usernameGmail, passwordGmail);
                this.codeDeVerification = codeDeVerification;

            }
        }
    }
    public void confirmVerificationCode() {
        connect = database.connectDb();

        String sql = "INSERT INTO utilisateur (username, password, email, image) VALUES (?, ?, ?, ?)";
        String codeSaisi = code.getText();

        if (codeSaisi.equals(codeDeVerification) && validationEmail()) {
            String uri = "C:\\Users\\rawef\\OneDrive\\Desktop\\demo1\\src\\main\\resources\\com\\example\\demo1\\rawef.jpg";
            user.path = uri;

            String usernameText = username1.getText();
            String passwordText = password1.getText();
            String emailText = email.getText();

            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, usernameText);
                prepare.setString(2, passwordText);
                prepare.setString(3, emailText);
                prepare.setString(4, user.path);

                prepare.executeUpdate();

                email.setText("");
                username1.setText("");
                password1.setText("");

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Message de réussite");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Compte créé avec succès !");
                successAlert.showAndWait();

                signup_page.setVisible(true);
                verificationform.setVisible(false);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            // Le code saisi est incorrect
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Message d'erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Code de vérification incorrect. Veuillez réessayer.");
            errorAlert.showAndWait();
        }
    }



    public void shownpass(){

       show_password.setOnAction(event -> {
           // Modifiez le type de champ de mot de passe en fonction de l'état de la case à cocher
           if (show_password.isSelected()) {
               password.setDisable(true);
               password.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black;");
               password.setPromptText(password.getText());
               password.setText("");
               password.setAccessibleText("text");
           } else {
               password.setDisable(false);
               password.setStyle("");
               password.setPromptText("");
               password.setText(password.getPromptText());
               password.setAccessibleText("password");
           }
       });
    }

    public boolean isAdminEmail(String email) {

        String emailAdministrateur = "rawef31@gmail.com";

        return email.equals(emailAdministrateur);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropShadowEffect();
        shownpass();



    }
}