package ru.aasmc.cems.services.simpleimpl;

import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.StorageRepo;
import ru.aasmc.cems.services.StorageService;

import java.util.Optional;

public class SimpleStorageService extends SimpleAbstractService<Storage> implements StorageService {
    private StorageRepo repo;

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

    public void setRepo(StorageRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<Storage> getRepo() {
        return repo;
    }
}

