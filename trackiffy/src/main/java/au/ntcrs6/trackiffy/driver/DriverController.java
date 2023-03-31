package au.ntcrs6.trackiffy.driver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<Object> registerANewDriver(@RequestBody Map<String, Object> driverPayload,
            HttpServletRequest request,
            HttpServletResponse reponse) {
        System.out.println("\u001B[32m" + "Controller reached" + "\u001B[0m");

        System.out.println(driverPayload);
        ObjectMapper mapper = new ObjectMapper();

        DriverEntity driver = mapper.convertValue(driverPayload, DriverEntity.class);
        // driver.toString();

        return driverService.register(driver);

    }

    @GetMapping("")
    public ResponseEntity<List<DriverEntity>> findDriver(
            @RequestParam(name = "licenceNumber", required = false) String licenceNumber) {

        System.out.println("\u001B[32m" + licenceNumber + "\u001B[0m");
        List<DriverEntity> drivers = driverService.findByLicenceNumber(licenceNumber);

        if (drivers == null || drivers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(drivers);

    }

}
