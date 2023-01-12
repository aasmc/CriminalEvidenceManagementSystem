package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.util.CaseStatus;
import ru.aasmc.cems.util.CaseType;

import java.util.Optional;
import java.util.Set;

public interface CriminalCaseService extends  AbstractService<CriminalCase> {
    CriminalCase createCriminalCase(CaseType type,
                                    String shortDescription,
                                    String detailedDescription,
                                    CaseStatus caseStatus,
                                    String notes,
                                    Set<Evidence> evidenceSet,
                                    Detective leadInvestigator);

    Set<CriminalCase> findByLeadInvestigator(Detective detective);

    Optional<CriminalCase> findByNumber(String caseNumber);

    Set<CriminalCase> findByStatus(CaseStatus status);

    Set<CriminalCase> findByType(CaseType type);
}
