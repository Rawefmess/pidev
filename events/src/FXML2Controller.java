


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import entity.Evenement;
import service.Serviceevenement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FXML2Controller {

    @FXML
    private Button ajouterE;

    @FXML
    private DatePicker date_picker_ajout;

    @FXML
    private DatePicker date_picker_modifier;

    @FXML
    private Button modifierE;

    @FXML
    private Button supprimerE;

    @FXML
    private Button supprimerE1;

    @FXML
    private TextArea text_field_discription_ajout;

    @FXML
    private TextArea text_field_discription_modifier;

    @FXML
    private TextField textfield_ajout_image;

    @FXML
    private TextField textfield_ajout_name;

    @FXML
    private TextField textfield_ajout_price;

    @FXML
    private TextField textfield_modifier_id;

    @FXML
    private TextField textfield_modifier_image;

    @FXML
    private TextField textfield_modifier_name;

    @FXML
    private TextField textfield_modifier_price;

    @FXML
    void ActivateSupprimerE(ActionEvent event) {

    }

    @FXML

void activateAjouterE(ActionEvent event) {
    String name = textfield_ajout_name.getText().trim();
    String priceText = textfield_ajout_price.getText().trim();
    String date = date_picker_ajout.getValue() != null ? date_picker_ajout.getValue().toString() : "";
    String description = text_field_discription_ajout.getText().trim();
    String image = textfield_ajout_image.getText().trim();

    if (name.isEmpty() || priceText.isEmpty() || date.isEmpty() || description.isEmpty() || image.isEmpty()) {
        showAlert(AlertType.ERROR, "Erreur", "Champs incomplets", "Veuillez remplir tous les champs.");
        return;
    }

    double price;
    try {
        price = Double.parseDouble(priceText);
    } catch (NumberFormatException e) {
        showAlert(AlertType.ERROR, "Erreur", "Format du prix incorrect", "Le prix doit être un nombre.");
        return;
    }

    Evenement newEvent = new Evenement(name, price, date, description, image);

    Serviceevenement sp = new Serviceevenement();
    sp.ajouterevenement(newEvent);

    showAlert(AlertType.INFORMATION, "Succès", "Ajout réussi", "Événement ajouté avec succès!");
}

private void showAlert(AlertType alertType, String title, String header, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}



    @FXML
void activatemodifierE(ActionEvent event) {
    // Create an Evenement instance based on the FXML input fields
    Evenement modifiedEvent = new Evenement(
            Integer.parseInt(textfield_modifier_id.getText()),
            textfield_modifier_name.getText(),
            Double.parseDouble(textfield_modifier_price.getText()),
            date_picker_modifier.getValue().toString(),
            text_field_discription_modifier.getText(),
            textfield_modifier_image.getText()
    );

    Serviceevenement sp = new Serviceevenement();
    sp.modifierevenement(modifiedEvent);
}
}
