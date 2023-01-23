package ru.aasmc.cems.dj.services;

import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dj.services.wrappers.DetectiveWrapper;

import java.util.List;

public interface DetectiveService {
    List<Detective> findAll();

    DetectiveWrapper findById(Long id);

    Detective save(Detective detective);
}
