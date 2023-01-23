package ru.aasmc.cems.dj.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dj.repos.CriminalCaseRepo;
import ru.aasmc.cems.dj.services.CriminalCaseService;

import java.util.List;

@Service
@Transactional
public class CriminalCaseServiceImpl implements CriminalCaseService {

    private final CriminalCaseRepo criminalCaseRepo;

    @Autowired
    public CriminalCaseServiceImpl(CriminalCaseRepo criminalCaseRepo) {
        this.criminalCaseRepo = criminalCaseRepo;
    }

    @Override
    public List<CriminalCase> findAll() {
        return criminalCaseRepo.findAll();
    }

    @Override
    public List<CriminalCase> findAllByLeadInvestigator(Detective detective) {
        return criminalCaseRepo.findByLeadInvestigator(detective);
    }

    @Override
    public CriminalCase save(CriminalCase criminalCase) {
        return criminalCaseRepo.save(criminalCase);
    }
}
