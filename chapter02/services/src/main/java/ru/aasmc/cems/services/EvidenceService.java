package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;

public interface EvidenceService extends AbstractService<Evidence> {
    Evidence createEvidence(CriminalCase criminalCase, Storage storage, String itemName);
}
