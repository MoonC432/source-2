package au.ntcrs6.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.ntcrs6.utils.HttpRequest;
import au.ntcrs6.utils.ResponseHolder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CitationsAndSessionsController {

    @FXML
    private TableView<Citation> citationsTable;

    @FXML
    private TableColumn<Citation, String> descriptionCol;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private Button drivingSessionButton;

    @FXML
    private TableView<DrivingSession> drivingSessionTable;

    @FXML
    private TableColumn<DrivingSession, Integer> durationCol;

    @FXML
    private TextField durationInput;

    @FXML
    private Button issueCitationButton;

    @FXML
    private TableColumn<Citation, String> issueDateCol;

    @FXML
    private TableColumn<Citation, String> locationCol;

    @FXML
    private TextField locationInput;

    @FXML
    private TableColumn<DrivingSession, String> numberCol; // vehicle number

    @FXML
    private TableColumn<DrivingSession, String> startDateCol;

    @FXML
    private DatePicker startDateInput;

    @FXML
    private ComboBox<String> vehicleInput;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Citation {
        private Long id;
        private String issueDate;
        private String location;
        private String description;
        private Long driverId;

        public Citation() {
        }

        public Citation(Long id, String issueDate, String location, String description, Long driverId) {
            this.id = id;
            this.issueDate = issueDate;
            this.location = location;
            this.description = description;
            this.driverId = driverId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(String issueDate) {
            this.issueDate = issueDate.split("T")[0];
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getDriverId() {
            return driverId;
        }

        public void setDriverId(Long driverId) {
            this.driverId = driverId;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DrivingSession {
        private Long id;
        private Long driverId;
        private Long citationId;
        private String number; // vehicle Number
        private String startDate;
        private int durationInHours;

        public DrivingSession() {
        }

        public DrivingSession(Long id, Long driverId, Long citationId, String vehicleNumber, String startDate,
                int durationInHours) throws JsonMappingException, JsonProcessingException {
            this.id = id;
            this.driverId = driverId;
            this.citationId = citationId;
            this.number = vehicleNumber;
            this.startDate = startDate;
            this.durationInHours = durationInHours;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getDriverId() {
            return driverId;
        }

        public void setDriverId(Long driverId) {
            this.driverId = driverId;
        }

        public Long getCitationId() {
            return citationId;
        }

        public void setCitationId(Long citationId) {
            this.citationId = citationId;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String vehicleNumber) throws JsonMappingException, JsonProcessingException {

            this.number = vehicleNumber;

        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate.split("T")[0];
        }

        public int getDurationInHours() {
            return durationInHours;
        }

        public void setDurationInHours(int durationInHours) {
            this.durationInHours = durationInHours;
        }

    }

    private Long selectedCitationId;
    ObjectMapper mapper = new ObjectMapper();

    public void createCitation() throws IOException {
        Map<String, Object> citationDetails = new HashMap<>();
        JsonNode driver = mapper.readTree(ResponseHolder.getDriverDetails()).get(0);

        citationDetails.put("location", locationInput.getText());
        citationDetails.put("description", descriptionInput.getText());
        citationDetails.put("driverId", driver.get("id").asLong());
        String jsonPayload = mapper.writeValueAsString(citationDetails);
        HttpRequest request = new HttpRequest();
        request.sendPostRequest("/api/v1/citation/issue", jsonPayload);

        locationInput.clear();
        descriptionInput.clear();

    }

    public void createDrivingSession() throws IOException {
        Map<String, Object> sessionDetails = new HashMap<>();
        JsonNode driver = mapper.readTree(ResponseHolder.getDriverDetails()).get(0);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

        Date startDate = null;

        try {
            startDate = inputFormat.parse(startDateInput.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sessionDetails.put("driverId", driver.get("id").asLong());
        sessionDetails.put("citationId", selectedCitationId);
        sessionDetails.put("startDate", outputFormat.format(startDate));
        sessionDetails.put("durationInHours", Integer.parseInt(durationInput.getText()));
        // vehicleId
        sessionDetails.put("vehicleId", getVehicleIdFromLicensePlate(vehicleInput.getValue()));
        String jsonPayload = mapper.writeValueAsString(sessionDetails);

        HttpRequest request = new HttpRequest();
        request.sendPostRequest("/api/v1/session/issue", jsonPayload);
        ;

        startDateInput.setValue(null);
        durationInput.clear();
        vehicleInput.setValue(null);

    }

    public Long getVehicleIdFromLicensePlate(String number) throws JsonMappingException, JsonProcessingException {
        JsonNode vehicleList = mapper.readTree(ResponseHolder.getVehicles());
        Long id = null;
        for (JsonNode vehicle : vehicleList) {
            if (vehicle.get("licensePlate").asText().equals(number)) {
                id = vehicle.get("id").asLong();
            }
        }
        return id;
    }

    public void initialize() throws JsonMappingException, JsonProcessingException {
        JsonNode vehicleList = mapper.readTree(ResponseHolder.getVehicles());
        ObservableList<String> vehicleNumbers = FXCollections.observableArrayList();
        for (JsonNode vehicle : vehicleList) {
            vehicleNumbers.add(vehicle.get("licensePlate").asText());
        }
        vehicleInput.setItems(vehicleNumbers);

        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("durationInHours"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonNode>> typeRef = new TypeReference<List<JsonNode>>() {
        };
        try {
            List<JsonNode> citationsJson = mapper.readValue(ResponseHolder.getCitations(), typeRef);

            ObservableList<CitationsAndSessionsController.Citation> citations = FXCollections.observableArrayList();
            for (JsonNode c : citationsJson) {
                CitationsAndSessionsController.Citation citation = mapper.treeToValue(c,
                        CitationsAndSessionsController.Citation.class);
                citations.add(citation);
            }

            citationsTable.setItems(citations);

        } catch (Exception e) {
            e.printStackTrace();
        }

        citationsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Single click
                Citation selectedCitation = citationsTable.getSelectionModel().getSelectedItem();
                if (selectedCitation != null) {
                    // Retrieve driving sessions associated with selected citation
                    selectedCitationId = selectedCitation.getId();
                    List<DrivingSession> drivingSessions = retrieveDrivingSessions(selectedCitationId);

                    // Populate driving sessions table view with retrieved data
                    drivingSessionTable.setItems(FXCollections.observableArrayList(drivingSessions));
                }
            }
        });
    }

    public List<DrivingSession> retrieveDrivingSessions(Long citationId) {

        List<DrivingSession> sessions = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonNode>> typeRef = new TypeReference<List<JsonNode>>() {
        };
        try {
            List<JsonNode> sessionsJson = mapper.readValue(ResponseHolder.getSessions(), typeRef);
            for (JsonNode session : sessionsJson) {
                if (session.get("citationId").asLong() == citationId) {
                    // add it to the result list
                    DrivingSession drivingSession = new DrivingSession();

                    drivingSession.setStartDate(session.get("startDate").asText());
                    drivingSession.setDurationInHours((session.get("durationInHours").asInt()));
                    drivingSession.setNumber(getVehicleNumber(session.get("vehicleId").asText())); // extract vehicle

                    sessions.add(drivingSession);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return sessions;
    }

    public String getVehicleNumber(String vehicleId) throws JsonMappingException, JsonProcessingException {
        String vehicleNumber = "NULL";

        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<JsonNode>> typeRef = new TypeReference<List<JsonNode>>() {
            };
            List<JsonNode> vehicles = mapper.readValue(ResponseHolder.getVehicles(), typeRef);

            for (JsonNode v : vehicles) {
                if (v.get("id").asText().equals(vehicleId)) {
                    vehicleNumber = v.get("licensePlate").asText();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicleNumber;

    }

}
