package au.ntcrs6.trackiffy.driver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
    List<DriverEntity> findByLicenceNumber(String licenceNumber);

}
