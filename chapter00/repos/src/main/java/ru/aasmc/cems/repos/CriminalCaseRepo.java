package ru.aasmc.cems.repos;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.util.CaseStatus;
import ru.aasmc.cems.util.CaseType;

import java.util.Optional;
import java.util.Set;

public interface CriminalCaseRepo extends AbstractRepo<CriminalCase> {

    Set<CriminalCase> findByLeadInvestigator(Detective detective);

    Optional<CriminalCase> findByNumber(String caseNumber);

    Set<CriminalCase> findByStatus(CaseStatus status);

    Set<CriminalCase> findByType(CaseType type);
}
