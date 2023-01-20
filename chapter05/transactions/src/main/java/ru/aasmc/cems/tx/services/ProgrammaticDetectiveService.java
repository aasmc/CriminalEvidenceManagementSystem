package ru.aasmc.cems.tx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.DetectiveRepo;
import ru.aasmc.cems.services.DetectiveService;
import ru.aasmc.cems.services.simpleimpl.SimpleAbstractService;
import ru.aasmc.cems.util.EmploymentStatus;
import ru.aasmc.cems.util.NumberGenerator;
import ru.aasmc.cems.util.Rank;

import java.util.Optional;
import java.util.Set;

//@Service("programmaticDetectiveService")
public class ProgrammaticDetectiveService extends SimpleAbstractService<Detective> implements DetectiveService {
    private DetectiveRepo detectiveRepo;
    private TransactionTemplate txTemplate;

    @Autowired
    public ProgrammaticDetectiveService(DetectiveRepo detectiveRepo, TransactionTemplate txTemplate) {
        this.detectiveRepo = detectiveRepo;
        this.txTemplate = txTemplate;
    }

    @Override
    public Detective findById(Long entityId) {
        return txTemplate.execute(status -> {
            Optional<Detective> opt = null;
            try {
                opt = detectiveRepo.findById(entityId);
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return opt.orElse(null);
        });
    }

    @Override
    public Detective createDetective(Person person, Rank rank) {
        Detective detective = new Detective();
        detective.setPerson(person);
        detective.setRank(rank);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        detective.setArmed(false);
        detective.setStatus(EmploymentStatus.ACTIVE);

        return txTemplate.execute(status -> {
            try {
                detectiveRepo.save(detective);
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return detective;
        });
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return txTemplate.execute(status -> detectiveRepo.findByBadgeNumber(badgeNumber));
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        return txTemplate.execute(status -> detectiveRepo.findByRank(rank));
    }

    @Override
    protected AbstractRepo<Detective> getRepo() {
        return detectiveRepo;
    }
}
