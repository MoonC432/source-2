package au.ntcrs6.trackiffy.vehicle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import au.ntcrs6.trackiffy.driver.DriverEntity;
import au.ntcrs6.trackiffy.driver.DriverRepository;
import au.ntcrs6.trackiffy.utilities.RandomStringGenerator;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DriverRepository driverRepository;

    public ResponseEntity<Object> register(VehicleEntity vehicleInstance) {
        vehicleInstance.setVin(RandomStringGenerator.generateVIN());
        vehicleInstance.setRegistrationDate(new Date());
        vehicleRepository.save(vehicleInstance);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<VehicleEntity> findVehiclesByDriverId(Long id) {
        // DriverEntity driver = driverRepository.findById(id)
        // .orElseThrow(() -> new EntityNotFoundException("Invalid Driver Id"));
        List<VehicleEntity> vehicles = vehicleRepository.findVehiclesByDriverId(id);
        return vehicles;
    }
}
