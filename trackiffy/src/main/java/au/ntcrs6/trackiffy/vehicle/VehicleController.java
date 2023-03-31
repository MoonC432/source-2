package au.ntcrs6.trackiffy.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
}
