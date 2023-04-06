package au.ntcrs6.trackiffy.driver;

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
@RequestMapping(path = "api/v1/driver")

public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerDriver(@RequestBody Map<String, Object> driverPayload,
            HttpServletRequest request,
            HttpServletResponse reponse) {

        ObjectMapper mapper = new ObjectMapper();

        DriverEntity driver = mapper.convertValue(driverPayload, DriverEntity.class);

        return driverService.register(driver);

    }

    @GetMapping("")
    public ResponseEntity<List<DriverEntity>> findDriver(
            @RequestParam(name = "licenceNumber", required = false) String licenceNumber) {

        List<DriverEntity> drivers = driverService.findByLicenceNumber(licenceNumber);

        if (drivers == null || drivers.isEmpty()) {
            drivers = new ArrayList<>();
        }

        return ResponseEntity.ok(drivers);

    }

}
