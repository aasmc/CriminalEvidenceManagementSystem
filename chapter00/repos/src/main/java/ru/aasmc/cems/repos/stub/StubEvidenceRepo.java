package ru.aasmc.cems.repos.stub;

import org.apache.commons.lang3.NotImplementedException;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.EvidenceRepo;

import java.util.Optional;
import java.util.Set;

public class StubEvidenceRepo extends StubAbstractRepo<Evidence> implements EvidenceRepo {
    @Override
    public Set<Evidence> findByCriminalCase(CriminalCase criminalCase) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Optional<Evidence> findByNumber(String evidenceNumber) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public boolean isInStorage(Storage storage) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Evidence update(Evidence entity)  {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
