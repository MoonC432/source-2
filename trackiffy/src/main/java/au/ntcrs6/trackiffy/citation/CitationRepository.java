package au.ntcrs6.trackiffy.citation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CitationRepository extends JpaRepository<CitationEntity, Long> {
    @Query("SELECT c FROM CitationEntity c WHERE c.driverId = :driverId")
    List<CitationEntity> findCitationsByDriverId(@Param("driverId") Long driverId);
}
