package au.ntcrs6.trackiffy.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/driver")

public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerANewDriver(@RequestBody DriverEntity request) {
        System.out.println("\u001B[32m" + "Controller reached" + "\u001B[0m");
        request.toString();

        return driverService.register(request);

    }

}
