package ru.aasmc.cems.dj.services;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.dao.Storage;

import java.util.List;

public interface EvidenceService {

    List<Evidence> findAll();

    List<Evidence> findAllByStorage(Storage storage);

    List<Evidence> findAllByCriminalCase(CriminalCase criminalCase);

    Evidence save(Evidence evidence);
}

