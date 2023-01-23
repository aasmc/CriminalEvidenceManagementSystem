package ru.aasmc.cems.dj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;

import java.util.List;

public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
    List<CriminalCase> findByLeadInvestigator(Detective detective);
}
