package au.ntcrs6.controllers;

import java.io.IOException;

import au.ntcrs6.utils.HttpRequest;
import au.ntcrs6.utils.ResponseHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchDriverController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField licenceNumberInput;

    @FXML
    public void searchByLicenceNumber(ActionEvent event) throws IOException {

        HttpRequest requestPayload = new HttpRequest();
        String response = requestPayload.sendGetRequest("/api/v1/driver?licenceNumber=", licenceNumberInput.getText());
        ResponseHolder.setDriverDetails(response);
        switchToDriverInfo(event);

    }

    @FXML
    public void switchToDriverInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/au/ntcrs6/DriverInfo.fxml"));

        scene = new Scene(root);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
