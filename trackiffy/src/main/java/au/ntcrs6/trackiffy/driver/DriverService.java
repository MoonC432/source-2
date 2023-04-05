package au.ntcrs6.trackiffy.driver;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import au.ntcrs6.trackiffy.utilities.Enumerator;
import au.ntcrs6.trackiffy.utilities.RandomStringGenerator;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public ResponseEntity<Object> register(DriverEntity driverInstance) {
        driverInstance.setLicenceNumber(RandomStringGenerator.generateLicenseNumber());
        driverInstance.setDocumentNumber(RandomStringGenerator.generateDocumentNumber());
        driverInstance.setIssueDate(new Date());

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, Enumerator.DRIVER_LICENCE_EXPIRATION_DURATION);
        driverInstance.setExpireDate(new Date());

        driverInstance.setStatus(Enumerator.DRIVER_DEFAULT_STATUS);
        driverInstance.setRecord(Enumerator.DRIVER_DEFAULT_RECORD);

        driverRepository.save(driverInstance);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<DriverEntity> findByLicenceNumber(String licenceNumber) {
        List<DriverEntity> driver = driverRepository.findByLicenceNumber(licenceNumber);
        return driver;
    }

    public DriverEntity findDriverById(int id) {

        DriverEntity driver = driverRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));
        driver.toString();
        return driver;
    }

}
