package ru.aasmc.cems.services.simpleimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.StorageRepo;
import ru.aasmc.cems.services.CemsService;
import ru.aasmc.cems.services.StorageService;

import java.util.Optional;

@CemsService
@Service
public class SimpleStorageService extends SimpleAbstractService<Storage> implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(SimpleStorageService.class);
    private final StorageRepo repo;

    @Autowired
    public SimpleStorageService(StorageRepo repo) {
        this.repo = repo;
    }

    @Override
    public Storage createStorage(String name, String location) {
        var storage = new Storage();
        storage.setName(name);
        storage.setLocation(location);
        repo.save(storage);
        return storage;
    }

    @Override
    public Optional<Storage> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        return repo.findByLocation(location);
    }

    @Override
    public void saveEvidenceSet(Storage storage) {
        storage.getEvidenceSet().forEach(ev -> {
            logger.info(" ---> Pretending to save evidence with number {}" , ev.getNumber());
        });
    }

    @Override
    public AbstractRepo<Storage> getRepo() {
        return repo;
    }
}

