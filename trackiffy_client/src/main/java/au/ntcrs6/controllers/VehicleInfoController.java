package au.ntcrs6.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.ntcrs6.utils.ResponseHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehicleInfoController {
    @FXML
    private TableView<Vehicle> vehicleDetailsTable;

    @FXML
    private TableColumn<Vehicle, String> colorColumn;

    @FXML
    private TableColumn<Vehicle, String> licencePlateColumn;

    @FXML
    private TableColumn<Vehicle, String> makeColumn;

    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private TableColumn<Vehicle, String> registrationDateColumn;

    @FXML
    private TableColumn<Vehicle, String> vinColumn;
    @FXML
    private TableColumn<Vehicle, Integer> yearColumn;

    @FXML
    private TextField modelInput;

    @FXML
    private TextField makeInput;
    @FXML
    private Button registerVehicleButton;

    @FXML
    private TextField licencePlateInput;

    @FXML
    private TextField colorInput;

    @FXML
    private TextField yearInput;

    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Vehicle {
        public String make;
        public String model;
        public int year;
        public String licensePlate;
        public String color;
        public String registrationDate;
        public String vin;

        Vehicle() {
        };

        Vehicle(String make, String model, int year, String licencePlate, String color,
                String registrationDate, String vin) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.licensePlate = licencePlate;
            this.color = color;
            this.registrationDate = registrationDate.split("T")[0];
            this.vin = vin;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licencePlate) {
            this.licensePlate = licencePlate;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate.split("T")[0];
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

    }

    public void initialize() {

        makeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("year"));
        licencePlateColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("licensePlate"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("color"));
        registrationDateColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("registrationDate"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vin"));

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<JsonNode>> typeRef = new TypeReference<List<JsonNode>>() {
        };
        try {
            List<JsonNode> jsonNodes = objectMapper.readValue(ResponseHolder.getVehicles(), typeRef);
            ObservableList<VehicleInfoController.Vehicle> vehicles = FXCollections.observableArrayList();
            for (JsonNode jsonNode : jsonNodes) {
                VehicleInfoController.Vehicle vehicle = objectMapper.treeToValue(jsonNode,
                        VehicleInfoController.Vehicle.class);
                vehicles.add(vehicle);
            }
            vehicleDetailsTable.setItems(vehicles);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
