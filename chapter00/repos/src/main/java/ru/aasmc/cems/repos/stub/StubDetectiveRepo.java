package ru.aasmc.cems.repos.stub;

import org.apache.commons.lang3.NotImplementedException;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.repos.DetectiveRepo;
import ru.aasmc.cems.util.Rank;

import java.util.Optional;
import java.util.Set;

public class StubDetectiveRepo extends StubAbstractRepo<Detective> implements DetectiveRepo {

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return Optional.of(records.get(1L));
    }

    @Override
    public Set<Detective> findAll() {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Detective update(Detective entity)  {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
