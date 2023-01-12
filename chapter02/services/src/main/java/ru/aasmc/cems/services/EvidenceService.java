package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;

import java.util.Optional;
import java.util.Set;

public interface EvidenceService extends AbstractService<Evidence> {
    Evidence createEvidence(CriminalCase criminalCase, Storage storage, String itemName);

    Set<Evidence> findByCriminalCase(CriminalCase criminalCase);

    Optional<Evidence> findByNumber(String evidenceNumber);
}
