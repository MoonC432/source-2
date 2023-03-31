package au.ntcrs6.trackiffy.drivingSession;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "api/v1/session")
public class DrivingSessionController {

    @Autowired
    private DrivingSessionService drivingSessionService;

    @PostMapping("/issue")
    public ResponseEntity<DrivingSessionEntity> issueANewSession(@RequestBody Map<String, Object> sessionPayload,
            HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        DrivingSessionEntity session = mapper.convertValue(sessionPayload, DrivingSessionEntity.class);
        DrivingSessionEntity savedEntity = drivingSessionService.issueSession(session);
        if (savedEntity == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
        return ResponseEntity.ok(savedEntity);

    }

    @GetMapping("")
    public ResponseEntity<List<DrivingSessionEntity>> getAllSessionUnderDriver(
            @RequestParam(name = "driverId") Long driverId) {
        List<DrivingSessionEntity> sessions = drivingSessionService.getAllDrivingSessionUnderADriver(driverId);
        if (sessions == null || sessions.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sessions);
    }
}
