package ru.aasmc.cems.repos;

import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.util.Rank;

import java.util.Optional;
import java.util.Set;

public interface DetectiveRepo extends AbstractRepo<Detective> {

    Set<Detective> findAll();

    Optional<Detective> findByBadgeNumber(String badgeNumber);

    Set<Detective> findByRank(Rank rank);

    default Optional<Detective> findByIdWithPersonDetails(Long id) {
        return Optional.empty();
    }
}
