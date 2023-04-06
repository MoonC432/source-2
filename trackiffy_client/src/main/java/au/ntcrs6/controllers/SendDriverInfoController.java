package au.ntcrs6.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.ntcrs6.utils.HttpRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SendDriverInfoController {

    @FXML
    private TextField addressInput;

    @FXML
    private DatePicker dateOfBirthInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField heightInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private ComboBox<String> licenceClassComboBox;

    @FXML
    private ComboBox<String> provinceComboBox;

    @FXML
    private ComboBox<String> sexComboBox;

    @FXML
    private DatePicker startDateInput; // yyyy/mm/dd format

    @FXML
    private Button submitButton;

    public void initialize() {

        ObservableList<String> provinceList = FXCollections.observableArrayList();
        provinceList.addAll("Alberta", "British Columbia", "Manitoba",
                "New Brunswick",
                "Newfoundland and Labrador",
                "Northwest Territories",
                "Nova Scotia",
                "Nunavut",
                "Ontario",
                "Prince Edward Island",
                "Quebec",
                "Saskatchewan",
                "Yukon");
        ObservableList<String> licenceClassList = FXCollections.observableArrayList();
        licenceClassList.addAll("A", "B", "C", "D", "E", "F", "G", "G1", "G2", "M", "M1", "M2");
        ObservableList<String> sexList = FXCollections.observableArrayList();
        sexList.addAll("M", "F", "Other");

        provinceComboBox.setItems(provinceList);
        licenceClassComboBox.setItems(licenceClassList);
        sexComboBox.setItems(sexList);

    }

    public void registerANewDriver() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> driverDetails = new HashMap<>();

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

        Date startDate = null;
        Date dateofBirth = null;
        try {
            startDate = inputFormat.parse(startDateInput.getValue().toString());
            dateofBirth = inputFormat.parse(startDateInput.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        driverDetails.put("firstName", firstNameInput.getText());
        driverDetails.put("lastName", lastNameInput.getText());
        driverDetails.put("startDate", outputFormat.format(startDate));
        driverDetails.put("dateOfBirth", outputFormat.format(dateofBirth));
        driverDetails.put("address", addressInput.getText());
        driverDetails.put("sex", sexComboBox.getValue());
        driverDetails.put("licenceClass", licenceClassComboBox.getValue());
        driverDetails.put("province", provinceComboBox.getValue());
        driverDetails.put("heightInCm", Integer.parseInt(heightInput.getText()));
        String jsonPayload = objectMapper.writeValueAsString(driverDetails);

        HttpRequest request = new HttpRequest();
        request.sendPostRequest("/api/v1/driver/register", jsonPayload);

        System.out.println();

    }

}
