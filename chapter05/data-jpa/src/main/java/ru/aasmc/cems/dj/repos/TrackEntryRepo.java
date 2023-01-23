package ru.aasmc.cems.dj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.TrackEntry;

import java.time.LocalDateTime;
import java.util.List;

public interface TrackEntryRepo extends JpaRepository<TrackEntry, Long> {
    List<TrackEntry> findByDate(LocalDateTime localDate);

    List<TrackEntry> findByEvidence(Evidence evidence);

    List<TrackEntry> findByDetective(Detective detective);
}

