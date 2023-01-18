package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.repos.CriminalCaseRepo;
import ru.aasmc.cems.repos.DetectiveRepo;
import ru.aasmc.cems.repos.EvidenceRepo;
import ru.aasmc.cems.repos.StorageRepo;
import ru.aasmc.cems.util.CaseType;
import ru.aasmc.cems.util.Rank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface OperationsService {

    Detective createDetective(String firstName, String lastName, LocalDateTime hiringDate, Rank rank);

    CriminalCase createCriminalCase(CaseType caseType, String shortDescription, String badgeNo, Map<Evidence, String> evidenceAndLocations);

    Optional<CriminalCase> assignLeadInvestigator(String caseNumber, String leadDetectiveBadgeNo);

    Optional<CriminalCase> linkEvidence(String caseNumber, List<Evidence> evidenceList);

    boolean solveCase(String caseNumber, String reason);

    Set<Detective> getAssignedTeam(String caseNumber);

}
