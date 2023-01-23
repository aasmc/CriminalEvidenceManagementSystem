package ru.aasmc.cems.dj.services;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;

import java.util.List;

public interface CriminalCaseService {
    List<CriminalCase> findAll();

    List<CriminalCase> findAllByLeadInvestigator(Detective detective);

    CriminalCase save(CriminalCase criminalCase);
}

