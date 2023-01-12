package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.Storage;

import java.util.Optional;

public interface StorageService extends AbstractService<Storage> {

    Storage createStorage(String name, String location);

    Optional<Storage> findByName(String name);

    Optional<Storage> findByLocation(String location);
}
