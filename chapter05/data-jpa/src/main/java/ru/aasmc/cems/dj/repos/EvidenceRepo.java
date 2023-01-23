package ru.aasmc.cems.dj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;

import java.util.List;

public interface EvidenceRepo extends JpaRepository<Evidence, Long> {
    List<Evidence> findAllByStorage(Storage storage);

    List<Evidence> findAllByCriminalCase(CriminalCase criminalCase);
}
