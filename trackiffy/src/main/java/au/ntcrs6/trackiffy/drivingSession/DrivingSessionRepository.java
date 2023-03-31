package au.ntcrs6.trackiffy.drivingSession;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DrivingSessionRepository extends JpaRepository<DrivingSessionEntity, Long> {
    @Query("select ds from DrivingSessionEntity ds where ds.driverId = :driverId")
    List<DrivingSessionEntity> findSessionsByDriverId(@Param("driverId") Long driverId);
}
