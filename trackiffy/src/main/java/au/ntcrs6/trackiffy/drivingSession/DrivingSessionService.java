package au.ntcrs6.trackiffy.drivingSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrivingSessionService {

    @Autowired
    private DrivingSessionRepository drivingSessionRepository;

    public DrivingSessionEntity issueSession(DrivingSessionEntity sessionInstance) {
        DrivingSessionEntity savedEntity = drivingSessionRepository.save(sessionInstance);
        return savedEntity;
    }

    public List<DrivingSessionEntity> getAllDrivingSessionUnderADriver(Long driverId) {
        List<DrivingSessionEntity> sessions = drivingSessionRepository.findSessionsByDriverId(driverId);
        return sessions;
    }

}
