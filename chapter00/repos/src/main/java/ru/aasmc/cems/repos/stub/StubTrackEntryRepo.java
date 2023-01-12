package ru.aasmc.cems.repos.stub;

import org.apache.commons.lang3.NotImplementedException;
import ru.aasmc.cems.dao.TrackEntry;
import ru.aasmc.cems.repos.TrackEntryRepo;
import ru.aasmc.cems.util.TrackAction;

import java.util.Date;
import java.util.Set;

public class StubTrackEntryRepo extends StubAbstractRepo<TrackEntry> implements TrackEntryRepo {

    @Override
    public Set<TrackEntry> findByDetectiveId(Long detectiveId) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<TrackEntry> findByEvidenceId(Long evidenceId) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<TrackEntry> findByDate(Date date) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public TrackEntry update(TrackEntry entity)  {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<TrackEntry> findByDateAndAction(Date date, TrackAction action) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
