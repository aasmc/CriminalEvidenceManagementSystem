package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.TrackEntry;
import ru.aasmc.cems.util.TrackAction;

import java.time.LocalDateTime;

public interface TrackEntryService extends AbstractService<TrackEntry> {
    TrackEntry createTrackEntry(Evidence evidence,
                                Detective detective,
                                LocalDateTime date,
                                TrackAction action,
                                String reason);
}
