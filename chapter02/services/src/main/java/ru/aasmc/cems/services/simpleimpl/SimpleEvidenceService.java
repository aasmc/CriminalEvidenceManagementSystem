package ru.aasmc.cems.services.simpleimpl;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.EvidenceRepo;
import ru.aasmc.cems.services.EvidenceService;
import ru.aasmc.cems.util.NumberGenerator;

import java.util.Optional;
import java.util.Set;

public class SimpleEvidenceService extends SimpleAbstractService<Evidence> implements EvidenceService {
    private EvidenceRepo repo;

    public SimpleEvidenceService() {
    }

    public SimpleEvidenceService(EvidenceRepo repo) {
        this.repo = repo;
    }

    @Override
    public Evidence createEvidence(CriminalCase criminalCase, Storage storage, String itemName) {
        var evidence = new Evidence();
        evidence.setCriminalCase(criminalCase);
        evidence.setNumber(NumberGenerator.getEvidenceNumber());
        evidence.setItemName(itemName);
        evidence.setStorage(storage);
        repo.save(evidence);
        return evidence;
    }

    @Override
    public Set<Evidence> findByCriminalCase(CriminalCase criminalCase) {
        return repo.findByCriminalCase(criminalCase);
    }

    @Override
    public Optional<Evidence> findByNumber(String evidenceNumber) {
        return repo.findByNumber(evidenceNumber);
    }

    public void setRepo(EvidenceRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<Evidence> getRepo() {
        return repo;
    }
}