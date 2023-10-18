import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class FXMLDocumentController {

    @FXML
    private Button controleE;

    @FXML
    void ChangerpageE(ActionEvent event) throws IOException {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader();

            // Adjust the path to load from the JAR
            URL fxmlUrl = getClass().getResource("/FXML2.fxml");
            loader.setLocation(fxmlUrl);

            Parent root = loader.load();

            // Create a new scene using the loaded FXML
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) controleE.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        }
    }

    

