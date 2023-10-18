package resources;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import stationm.entities.Station;
import stationm.services.ServiceStation;
import stationm.tools.DataSource;

public class StationM extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ajoutstation.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("mybike!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //Connection cnx;
        //cnx=DataSource.getInstance().getConnection();
        //ServiceStation sr = new ServiceStation();
        //Station S=new Station(5,"ouiii","nabeul");
        //sr.ajouter(S);
        launch(args);
    }
}
