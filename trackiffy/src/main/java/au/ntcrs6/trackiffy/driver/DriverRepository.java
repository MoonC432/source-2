package au.ntcrs6.trackiffy.driver;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
    List<DriverEntity> findByLicenceNumber(String licenceNumber);

    Optional<DriverEntity> findById(Long id);
}
