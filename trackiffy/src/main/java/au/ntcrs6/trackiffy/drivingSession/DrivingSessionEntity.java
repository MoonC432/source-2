package au.ntcrs6.trackiffy.drivingSession;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DrivingSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long driverId;
    private Long VehicleId;
    private Long citationId; // the citation that was prevented
    private LocalDateTime startDate;
    private int durationInHours;

    public DrivingSessionEntity(Long driverId, Long vehicleId, Long citationId, String startDate,

            int durationInHours) throws ParseException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        this.driverId = driverId;
        this.VehicleId = vehicleId;
        this.citationId = citationId;
        this.startDate = LocalDateTime.parse(startDate, dateTimeFormat);
        this.durationInHours = durationInHours;
    }

    public DrivingSessionEntity() {
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

    public Long getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        VehicleId = vehicleId;
    }

    public Long getCitationId() {
        return citationId;
    }

    public void setCitationId(Long citationId) {
        this.citationId = citationId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        this.startDate = LocalDateTime.parse(startDate, dateTimeFormat);
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

}
