package ru.aasmc.cems.tx.services;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.StorageRepo;
import ru.aasmc.cems.services.StorageService;
import ru.aasmc.cems.services.simpleimpl.SimpleAbstractService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StorageServiceImpl extends SimpleAbstractService<Storage> implements StorageService {
    private Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    private StorageRepo storageRepo;

    public StorageServiceImpl(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Storage findById(Long entityId) {
        logger.debug(">>> Preparing to execute REPO.findById");
        Optional<Storage> opt =  storageRepo.findById(entityId);
        logger.debug(">>> Done executing REPO.findById");
        return opt.orElse(null);
    }

    @Override
    public Storage createStorage(String name, String location) {
        var storage = new Storage();
        storage.setName(name);
        storage.setLocation(location);
        storageRepo.save(storage);
        return storage;
    }

    @Override
    public Optional<Storage> findByName(String name) {
        return storageRepo.findByName(name);
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        return storageRepo.findByLocation(location);
    }

    @Override
    public void saveEvidenceSet(Storage storage) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    protected AbstractRepo<Storage> getRepo() {
        return storageRepo;
    }
}
