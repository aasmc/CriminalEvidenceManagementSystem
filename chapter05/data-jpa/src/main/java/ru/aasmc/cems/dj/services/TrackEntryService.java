package ru.aasmc.cems.dj.services;

import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.TrackEntry;

import java.time.LocalDateTime;
import java.util.List;

public interface TrackEntryService {

    List<TrackEntry> findByDate(LocalDateTime localDate);

    List<TrackEntry> findByEvidence(Evidence evidence);

    List<TrackEntry> findByDetective(Detective detective);

    TrackEntry save(TrackEntry entry);
}

