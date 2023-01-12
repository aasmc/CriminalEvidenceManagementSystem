package ru.aasmc.cems.services.simpleimpl;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.repos.CriminalCaseRepo;
import ru.aasmc.cems.services.CriminalCaseService;
import ru.aasmc.cems.util.CaseStatus;
import ru.aasmc.cems.util.CaseType;

import java.util.Optional;
import java.util.Set;

public class SimpleCriminalCaseService extends SimpleAbstractService<CriminalCase> implements CriminalCaseService {

    private CriminalCaseRepo repo;

    public void setRepo(CriminalCaseRepo repo) {
        this.repo = repo;
    }

    @Override
    public CriminalCaseRepo getRepo() {
        return repo;
    }

    @Override
    public CriminalCase createCriminalCase(CaseType type,
                                           String shortDescription,
                                           String detailedDescription,
                                           CaseStatus caseStatus,
                                           String notes,
                                           Set<Evidence> evidenceSet,
                                           Detective leadInvestigator) {
        var criminalCase = new CriminalCase();
        criminalCase.setType(type);
        criminalCase.setShortDescription(shortDescription);
        criminalCase.setDetailedDescription(detailedDescription);
        criminalCase.setStatus(caseStatus);
        criminalCase.setNotes(notes);
        criminalCase.setEvidenceSet(evidenceSet);
        criminalCase.setLeadInvestigator(leadInvestigator);
        repo.save(criminalCase);
        return criminalCase;
    }

    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        return repo.findByLeadInvestigator(detective);
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        return repo.findByNumber(caseNumber);
    }

    @Override
    public Set<CriminalCase> findByStatus(CaseStatus status) {
        return repo.findByStatus(status);
    }

    @Override
    public Set<CriminalCase> findByType(CaseType type) {
        return repo.findByType(type);
    }

}
