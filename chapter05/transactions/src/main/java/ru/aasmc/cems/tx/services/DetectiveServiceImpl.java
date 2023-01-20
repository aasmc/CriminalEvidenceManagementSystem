package ru.aasmc.cems.tx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.DetectiveRepo;
import ru.aasmc.cems.services.DetectiveService;
import ru.aasmc.cems.services.simpleimpl.SimpleAbstractService;
import ru.aasmc.cems.util.NumberGenerator;
import ru.aasmc.cems.util.Rank;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DetectiveServiceImpl extends SimpleAbstractService<Detective> implements DetectiveService {
    private final DetectiveRepo repo;

    @Autowired
    public DetectiveServiceImpl(DetectiveRepo repo) {
        this.repo = repo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Detective findById(Long entityId) {
        return repo.findById(entityId).orElse(null);
    }

    @Override
    public Detective createDetective(Person person, Rank rank) {
        var detective = new Detective();
        detective.setPerson(person);
        detective.setRank(rank);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        repo.save(detective);
        return detective;
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return repo.findByBadgeNumber(badgeNumber);
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        return repo.findByRank(rank);
    }

    @Override
    public AbstractRepo<Detective> getRepo() {
        return repo;
    }
}
