package ru.aasmc.cems.repos.jdbcimpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.aasmc.cems.dao.TrackEntry;
import ru.aasmc.cems.repos.TrackEntryRepo;
import ru.aasmc.cems.util.TrackAction;

import java.util.Date;
import java.util.Set;

@Repository
public class JdbcTrackEntryRepo extends JdbcAbstractRepo<TrackEntry> implements TrackEntryRepo {

    public JdbcTrackEntryRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Set<TrackEntry> findByDetectiveId(Long detectiveId) {
        return null;
    }

    @Override
    public Set<TrackEntry> findByEvidenceId(Long evidenceId) {
        return null;
    }

    @Override
    public Set<TrackEntry> findByDate(Date date) {
        return null;
    }

    @Override
    public Set<TrackEntry> findByDateAndAction(Date date, TrackAction action) {
        return null;
    }
}
