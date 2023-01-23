package ru.aasmc.cems.dj.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.TrackEntry;
import ru.aasmc.cems.dj.repos.TrackEntryRepo;
import ru.aasmc.cems.dj.services.TrackEntryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TrackEntryServiceImpl implements TrackEntryService {

    private final TrackEntryRepo trackEntryRepo;

    @Autowired
    public TrackEntryServiceImpl(TrackEntryRepo trackEntryRepo) {
        this.trackEntryRepo = trackEntryRepo;
    }

    @Override
    public List<TrackEntry> findByDate(LocalDateTime localDate) {
        return trackEntryRepo.findByDate(localDate);
    }

    @Override
    public List<TrackEntry> findByEvidence(Evidence evidence) {
        return trackEntryRepo.findByEvidence(evidence);
    }

    @Override
    public List<TrackEntry> findByDetective(Detective detective) {
        return trackEntryRepo.findByDetective(detective);
    }

    @Override
    public TrackEntry save(TrackEntry entry) {
        return trackEntryRepo.save(entry);
    }
}
