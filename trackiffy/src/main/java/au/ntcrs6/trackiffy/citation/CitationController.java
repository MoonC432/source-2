package au.ntcrs6.trackiffy.citation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "api/v1/citation")
public class CitationController {

    @Autowired
    private CitationService citationService;

    @PostMapping("/issue")
    public ResponseEntity<Object> issueCitation(@RequestBody Map<String, Object> citationPayload,
            HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        CitationEntity citation = mapper.convertValue(citationPayload, CitationEntity.class);
        return citationService.issueCitation(citation);
    }

    @GetMapping("")
    public ResponseEntity<List<CitationEntity>> getCitaionsUnderADriver(
            @RequestParam(name = "driverId", required = true) Long driverId) {
        List<CitationEntity> citations = citationService.getCitaionsByDriverId(driverId);
        if (citations == null || citations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(citations);
    }
}
