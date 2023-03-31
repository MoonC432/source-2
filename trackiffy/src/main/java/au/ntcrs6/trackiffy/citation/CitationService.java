package au.ntcrs6.trackiffy.citation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CitationService {

    @Autowired
    private CitationRepository citationRepository;

    // issue a new citation
    public ResponseEntity<Object> issueCitation(CitationEntity citationInstance) {
        citationInstance.setIssueDate(new Date());
        citationRepository.save(citationInstance);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    // get all citation under a driver
    public List<CitationEntity> getCitaionsByDriverId(Long id) {
        List<CitationEntity> citations = citationRepository.findCitationsByDriverId(id);
        return citations;
    }
}
