package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.util.Rank;

import java.util.Optional;
import java.util.Set;

public interface DetectiveService extends  AbstractService<Detective> {

    Detective createDetective(Person person, Rank rank);

    Optional<Detective> findByBadgeNumber(String badgeNumber);

    Set<Detective> findByRank(Rank rank);
}
