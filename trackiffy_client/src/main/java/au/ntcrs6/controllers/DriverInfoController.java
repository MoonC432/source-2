package au.ntcrs6.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.ntcrs6.utils.HttpRequest;
import au.ntcrs6.utils.ResponseHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DriverInfoController {
    @FXML
    private Label firstNameText;
    @FXML
    private Label lastNameText;
    @FXML
    private Label experienceText;
    @FXML
    private Label dateOfBirthText;
    @FXML
    private Label addressText;
    @FXML
    private Label licenceClassText;
    @FXML
    private Label provinceText;
    @FXML
    private Label heightText;
    @FXML
    private Label sexText;
    @FXML
    private Label licenceNumberText;
    @FXML
    private Label documentNumberText;
    @FXML
    private Label issueDateText;
    @FXML
    private Label expireDateText;
    @FXML
    private Label statusText;
    @FXML
    private Label recordText;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private JsonNode driver;

    public void initialize() throws IOException, ParseException {

        // parse response into json and replace text with response payload
        ObjectMapper objectMapper = new ObjectMapper();
        driver = objectMapper.readTree(ResponseHolder.getDriverDetails()).get(0); // getting first value
                                                                                  // because response was in
                                                                                  // the form of a List<Driver>

        if (driver == null) {
            switchToMainDriver(stage);
        } else {
            firstNameText.setText(driver.get("firstName").asText());
            lastNameText.setText(driver.get("lastName").asText());
            addressText.setText(driver.get("address").asText());
            licenceClassText.setText(driver.get("licenceClass").asText());
            provinceText.setText(driver.get("province").asText());
            heightText.setText(driver.get("heightInCm").asText());
            sexText.setText(driver.get("sex").asText());
            licenceNumberText.setText(driver.get("licenceNumber").asText());
            documentNumberText.setText(driver.get("documentNumber").asText());
            issueDateText.setText(driver.get("issueDate").asText().split("T")[0]);
            expireDateText.setText(driver.get("expireDate").asText().split("T")[0]);
            statusText.setText(driver.get("status").asText());
            recordText.setText(driver.get("record").asText());
            experienceText.setText(getExperience(driver.get("startDate").asText().split("T")[0]));
            dateOfBirthText.setText(driver.get("dateOfBirth").asText().split("T")[0]);
        }

    }

    // implement other mothods for bttons
    @FXML
    public void switchToMainDriver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/au/ntcrs6/MainDriver.fxml"));

        scene = new Scene(root);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainDriver(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/au/ntcrs6/MainDriver.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public String getExperience(String startDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(startDate);

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        long difference = ChronoUnit.YEARS.between(localDate, today);

        return Long.toString(difference) + " years";
    }

    public void seeVehiclesAction(ActionEvent event) throws IOException {
        HttpRequest request = new HttpRequest();
        String vehiclesResponse = request.sendGetRequest("/api/v1/vehicle?driverId=", driver.get("id").asText());
        ResponseHolder.setVehicles(vehiclesResponse);
        showVehicleInfo();

    }

    public void showVehicleInfo() throws IOException {
        Parent vehicleRoot = FXMLLoader.load(getClass().getResource("/au/ntcrs6/VehicleInfo.fxml"));

        Scene vehicleScene = new Scene(vehicleRoot);

        Stage vehicleStage = new Stage();
        vehicleStage.setScene(vehicleScene);
        vehicleStage.show();

    }

    public void seeCitationsAndSessionsAction() throws IOException {
        HttpRequest request = new HttpRequest();
        String citationsResponse = request.sendGetRequest("/api/v1/citation?driverId=", driver.get("id").asText());
        ResponseHolder.setCitations(citationsResponse);
        String sessionsResponse = request.sendGetRequest("/api/v1/session?driverId=", driver.get("id").asText());
        ResponseHolder.setSessions(sessionsResponse);
        String vehiclesResponse = request.sendGetRequest("/api/v1/vehicle?driverId=", driver.get("id").asText());
        ResponseHolder.setVehicles(vehiclesResponse);
        showCitaionsAndSessionsInfo();
    }

    public void showCitaionsAndSessionsInfo() throws IOException {
        // citaions And Sessions
        Parent cASRoot = FXMLLoader.load(getClass().getResource("/au/ntcrs6/CitationsAndSessions.fxml"));

        Scene cASScene = new Scene(cASRoot);

        Stage cASStage = new Stage();
        cASStage.setScene(cASScene);
        cASStage.show();
    }

}
