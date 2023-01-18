package ru.aasmc.cems.repos.jdbcimpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.EvidenceRepo;

import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcEvidenceRepo extends JdbcAbstractRepo<Evidence> implements EvidenceRepo {

    public JdbcEvidenceRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Set<Evidence> findByCriminalCase(CriminalCase criminalCase) {
        return null;
    }

    @Override
    public Optional<Evidence> findByNumber(String evidenceNumber) {
        return Optional.empty();
    }

    @Override
    public boolean isInStorage(Storage storage) {
        return false;
    }
}
