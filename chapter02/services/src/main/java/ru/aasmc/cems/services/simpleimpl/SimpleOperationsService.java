package ru.aasmc.cems.services.simpleimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.CriminalCaseRepo;
import ru.aasmc.cems.repos.DetectiveRepo;
import ru.aasmc.cems.repos.EvidenceRepo;
import ru.aasmc.cems.repos.StorageRepo;
import ru.aasmc.cems.services.OperationsService;
import ru.aasmc.cems.services.ServiceException;
import ru.aasmc.cems.util.CaseStatus;
import ru.aasmc.cems.util.CaseType;
import ru.aasmc.cems.util.NumberGenerator;
import ru.aasmc.cems.util.Rank;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SimpleOperationsService implements OperationsService {

    private final CriminalCaseRepo criminalCaseRepo;
    private final EvidenceRepo evidenceRepo;
    private final DetectiveRepo detectiveRepo;
    private final StorageRepo storageRepo;

    @Autowired
    public SimpleOperationsService(CriminalCaseRepo criminalCaseRepo,
                                   EvidenceRepo evidenceRepo,
                                   DetectiveRepo detectiveRepo,
                                   StorageRepo storageRepo) {
        this.criminalCaseRepo = criminalCaseRepo;
        this.evidenceRepo = evidenceRepo;
        this.detectiveRepo = detectiveRepo;
        this.storageRepo = storageRepo;
    }

    @Override
    public Detective createDetective(String firstName, String lastName, LocalDateTime hiringDate, Rank rank) {
        var person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setHiringDate(hiringDate);
        person.setPassword(NumberGenerator.getPassword());
        var detective = new Detective();
        detective.setPerson(person);
        detective.setRank(rank);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        detectiveRepo.save(detective);
        return detective;
    }

    @Override
    public CriminalCase createCriminalCase(CaseType caseType, String shortDescription, String badgeNo, Map<Evidence, String> evidenceMap) {
        var detectiveOpt = detectiveRepo.findByBadgeNumber(badgeNo);
        var criminalCase = new CriminalCase();
        criminalCase.setType(caseType);
        criminalCase.setShortDescription(shortDescription);
        detectiveOpt.ifPresent(criminalCase::setLeadInvestigator);
        criminalCaseRepo.save(criminalCase);

        evidenceMap.forEach((ev, storageName) -> {
            var storageOpt = storageRepo.findByName(storageName);
            if (storageOpt.isPresent()) {
                ev.setStorage(storageOpt.get());
                criminalCase.addEvidence(ev);
                evidenceRepo.save(ev);
            } else {
                throw new ServiceException("Evidence Storage not present in the system");
            }
        });
        return criminalCase;
    }

    @Override
    public Optional<CriminalCase> assignLeadInvestigator(String caseNumber, String leadDetectiveBadgeNo) {
        var opt = criminalCaseRepo.findByNumber(caseNumber);
        var detectiveOpt = detectiveRepo.findByBadgeNumber(leadDetectiveBadgeNo);
        if (opt.isPresent()) {
            CriminalCase criminalCase = opt.get();
            if (detectiveOpt.isPresent()) {
                criminalCase.setLeadInvestigator(detectiveOpt.get());
                criminalCaseRepo.save(criminalCase);
            }
            return opt;
        }
        return Optional.empty();
    }

    @Override
    public Optional<CriminalCase> linkEvidence(String caseNumber, List<Evidence> evidenceList) {
        var opt = criminalCaseRepo.findByNumber(caseNumber);
        if (opt.isPresent()) {
            CriminalCase criminalCase = opt.get();
            criminalCase.getEvidenceSet().forEach(evidence -> {
                evidence.setCriminalCase(criminalCase);
                evidenceRepo.save(evidence);
            });
            return opt;
        }
        return Optional.empty();
    }

    @Override
    public boolean solveCase(String caseNumber, String reason) {
        criminalCaseRepo.findByNumber(caseNumber).ifPresent(criminalCase -> {
            criminalCase.setStatus(CaseStatus.CLOSED);
            criminalCase.getEvidenceSet().forEach(evidence -> {
                evidence.setArchived(true);
            });
            criminalCaseRepo.save(criminalCase);
        });
        return true;
    }

    @Override
    public Set<Detective> getAssignedTeam(String caseNumber) {
        var opt = criminalCaseRepo.findByNumber(caseNumber);
        if (opt.isPresent()) {
            return opt.get().getAssigned();
        }
        return new HashSet<>();
    }
}
