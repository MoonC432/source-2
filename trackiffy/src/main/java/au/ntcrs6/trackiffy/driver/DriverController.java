package au.ntcrs6.trackiffy.driver;

//nine inch cock up ass barfing on pussy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DriverController {
    // you gay as shit;
    private DriverService driverService;

    @RequestMapping(path = "api/v1/driver")
    public String register(@RequestBody DriverEntity request) {
        System.out.println("DATA ENDPOINT REACHED");
        System.out.println("\u001B[32m" + request.toString() + "\u001B[0m");

        return driverService.register(request);

    }

}
