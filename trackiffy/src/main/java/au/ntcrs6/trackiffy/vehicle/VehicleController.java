package au.ntcrs6.trackiffy.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerVehicle(@RequestBody Map<String, Object> vehiclePayload,
            HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        VehicleEntity vehicle = mapper.convertValue(vehiclePayload, VehicleEntity.class);
        return vehicleService.register(vehicle);

    }

    @GetMapping("")
    public ResponseEntity<List<VehicleEntity>> findVehiclesByDriverId(
            @RequestParam(name = "driverId", required = true) Long driverId) {
        List<VehicleEntity> vehicles = vehicleService.findVehiclesByDriverId(driverId);
        if (vehicles == null || vehicles.isEmpty()) {
            vehicles = new ArrayList<>();
        }
        return ResponseEntity.ok(vehicles);

    }
}
