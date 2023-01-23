package ru.aasmc.cems.dj.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.dj.repos.StorageRepo;
import ru.aasmc.cems.dj.services.StorageService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    private final StorageRepo storageRepo;

    @Autowired
    public StorageServiceImpl(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Override
    public List<Storage> findAll() {
        Iterable<Storage> all = storageRepo.findAll();
        Iterator<Storage> iterator = all.iterator();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(Collectors.toList());
    }

    @Override
    public Storage findById(Long id) {
        Optional<Storage> storageOptional =  storageRepo.findById(id);
        return storageOptional.orElseGet(Storage::new);
    }

    @Override
    public Storage save(Storage storage) {
        return storageRepo.save(storage);
    }
}

