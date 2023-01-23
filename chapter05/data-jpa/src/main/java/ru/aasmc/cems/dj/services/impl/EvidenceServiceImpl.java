package ru.aasmc.cems.dj.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.dj.repos.EvidenceRepo;
import ru.aasmc.cems.dj.services.EvidenceService;

import java.util.List;

@Service
@Transactional
public class EvidenceServiceImpl implements EvidenceService {
    private final EvidenceRepo evidenceRepo;

    @Autowired
    public EvidenceServiceImpl(EvidenceRepo evidenceRepo) {
        this.evidenceRepo = evidenceRepo;
    }

    @Override
    public List<Evidence> findAll() {
        return evidenceRepo.findAll();
    }

    @Override
    public List<Evidence> findAllByStorage(Storage storage) {
        return evidenceRepo.findAllByStorage(storage);
    }

    @Override
    public List<Evidence> findAllByCriminalCase(CriminalCase criminalCase) {
        return evidenceRepo.findAllByCriminalCase(criminalCase);
    }

    @Override
    public Evidence save(Evidence evidence) {
        return evidenceRepo.save(evidence);
    }
}
