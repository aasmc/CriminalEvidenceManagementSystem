package ru.aasmc.cems.services.simpleimpl;

import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.TrackEntry;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.TrackEntryRepo;
import ru.aasmc.cems.services.TrackEntryService;
import ru.aasmc.cems.util.TrackAction;

import java.time.LocalDateTime;

public class SimpleTrackEntryService extends SimpleAbstractService<TrackEntry> implements TrackEntryService {
    private TrackEntryRepo repo;

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

    public void setRepo(TrackEntryRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<TrackEntry> getRepo() {
        return null;
    }
}

