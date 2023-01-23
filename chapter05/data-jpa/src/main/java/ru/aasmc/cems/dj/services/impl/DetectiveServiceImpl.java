package ru.aasmc.cems.dj.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dj.repos.DetectiveRepo;
import ru.aasmc.cems.dj.services.DetectiveService;
import ru.aasmc.cems.dj.services.wrappers.DetectiveWrapper;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetectiveServiceImpl implements DetectiveService {
    private Logger logger = LoggerFactory.getLogger(DetectiveServiceImpl.class);

    private final DetectiveRepo detectiveRepo;

    @Autowired
    public DetectiveServiceImpl(DetectiveRepo detectiveRepo) {
        this.detectiveRepo = detectiveRepo;
    }

    @Override
    public List<Detective> findAll() {
        return detectiveRepo.findAll();
    }

    @Override
    public DetectiveWrapper findById(Long id) {
        Optional<Detective> detectiveOpt = detectiveRepo.findByIdWithCriminalCases(id);
        return detectiveOpt.map(DetectiveWrapper::new).orElseGet(DetectiveWrapper::new);
    }

    @Override
    public Detective save(Detective detective) {
        return detectiveRepo.save(detective);
    }
}
