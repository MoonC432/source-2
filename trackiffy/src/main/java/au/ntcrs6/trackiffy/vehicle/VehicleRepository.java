package au.ntcrs6.trackiffy.vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.ntcrs6.trackiffy.driver.DriverEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    @Query("SELECT v FROM VehicleEntity v WHERE v.driverId = :driverId")
    List<VehicleEntity> findVehiclesByDriverId(@Param("driverId") Long driverId);
}
