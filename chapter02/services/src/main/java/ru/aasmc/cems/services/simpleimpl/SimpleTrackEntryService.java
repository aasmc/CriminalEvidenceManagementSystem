package ru.aasmc.cems.services.simpleimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.TrackEntry;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.TrackEntryRepo;
import ru.aasmc.cems.services.TrackEntryService;
import ru.aasmc.cems.util.TrackAction;

import java.time.LocalDateTime;

@Service
public class SimpleTrackEntryService extends SimpleAbstractService<TrackEntry> implements TrackEntryService {
    private final TrackEntryRepo repo;

    @Autowired
    public SimpleTrackEntryService(TrackEntryRepo repo) {
        this.repo = repo;
    }

    @Override
    public TrackEntry createTrackEntry(Evidence evidence, Detective detective, LocalDateTime date, TrackAction action, String reason) {
        var trackEntry = new TrackEntry();
        trackEntry.setEvidence(evidence);
        trackEntry.setDetective(detective);
        trackEntry.setDate(date);
        trackEntry.setAction(action);
        trackEntry.setReason(reason);
        repo.save(trackEntry);
        return trackEntry;
    }

    @Override
    public AbstractRepo<TrackEntry> getRepo() {
        return null;
    }
}

