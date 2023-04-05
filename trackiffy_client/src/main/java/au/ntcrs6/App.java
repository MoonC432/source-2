package au.ntcrs6;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene landingScene;
    private static Scene driverInfoScene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("N-TCRS-6 Trackiffy");

        Parent driverEntryForm = FXMLLoader.load(getClass().getResource("MainDriver.fxml"));

        landingScene = new Scene(driverEntryForm);
        stage.setScene(landingScene);

        stage.show();

    }

    public static void goToDriverInfoScene() {
        Stage stage = (Stage) landingScene.getWindow();

        stage.setScene(driverInfoScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}