package au.ntcrs6.trackiffy.drivingSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

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
    private Date startDate;
    private int durationInHours;

    public DrivingSessionEntity(Long driverId, Long vehicleId, Long citationId, String startDate,

            int durationInHours) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

        this.driverId = driverId;
        this.VehicleId = vehicleId;
        this.citationId = citationId;
        this.startDate = dateFormat.parse(startDate);
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

        this.startDate = dateFormat.parse(startDate);
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

}
