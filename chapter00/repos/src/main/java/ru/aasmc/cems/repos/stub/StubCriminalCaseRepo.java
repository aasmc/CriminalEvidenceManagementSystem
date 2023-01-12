package ru.aasmc.cems.repos.stub;

import org.apache.commons.lang3.NotImplementedException;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.repos.CriminalCaseRepo;
import ru.aasmc.cems.util.CaseStatus;
import ru.aasmc.cems.util.CaseType;
import ru.aasmc.cems.util.Rank;

import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static ru.aasmc.cems.repos.util.TestObjectsBuilder.buildCase;
import static ru.aasmc.cems.repos.util.TestObjectsBuilder.buildDetective;

public class StubCriminalCaseRepo extends StubAbstractRepo<CriminalCase> implements CriminalCaseRepo {

    Map<Detective, Set<CriminalCase>> records2 = new HashMap<>();
    Detective detective;

    @PostConstruct
    public void init() {
        detective = buildDetective("Sherlock",
                "Holmes",
                Rank.INSPECTOR,
                "TS1234");
        // create a few entries to play with
        this.save(buildCase(detective, CaseType.FELONY, CaseStatus.UNDER_INVESTIGATION));
        this.save(buildCase(detective, CaseType.MISDEMEANOR, CaseStatus.SUBMITTED));
    }

    @Override
    public CriminalCase update(CriminalCase entity) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public void save(CriminalCase criminalCase) {
        super.save(criminalCase);
        addWithLeadInvestigator(criminalCase);
    }

    private void addWithLeadInvestigator(CriminalCase criminalCase){
        if (criminalCase.getLeadInvestigator()!= null) {
            var lead = criminalCase.getLeadInvestigator();
            if (records2.containsKey(lead)) {
                records2.get(lead).add(criminalCase);
            } else {
                Set<CriminalCase> ccSet = new HashSet<>();
                ccSet.add(criminalCase);
                records2.put(lead, ccSet);
            }
        }
    }
    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        return records2.get(detective);
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        final var result = new CriminalCase[1];
        records2.values().forEach(set -> set.stream()
                .filter(c -> c.getNumber().equalsIgnoreCase(caseNumber))
                .findFirst().ifPresent(c -> result[0] = c)
        );
        return Optional.of(result[0]);
    }

    @Override
    public Set<CriminalCase> findByStatus(CaseStatus status) {
        final Set<CriminalCase> resultSet = new HashSet<>();
        records2.values().forEach(set ->
                resultSet.addAll(set.stream()
                        .filter(c -> c.getStatus().equals(status))
                        .collect(Collectors.toSet()))
        );
        return resultSet;
    }

    @Override
    public Set<CriminalCase> findByType(CaseType type) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @PreDestroy
    public void clear(){
        records2.entrySet().removeIf(e ->true);
    }
}
