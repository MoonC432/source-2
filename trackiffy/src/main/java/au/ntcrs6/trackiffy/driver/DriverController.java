package au.ntcrs6.trackiffy.driver;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DriverController {

    private DriverService driverService;

    @RequestMapping(path = "api/v1/driver")
    public String register(@RequestBody DriverRequest request) {

        System.out.println(request.toString());
        return null;
        // return driverService.register(request);

    }

}
