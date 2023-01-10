package ru.aasmc.cems.repos;

import ru.aasmc.cems.dao.Storage;

import java.util.Optional;

public interface StorageRepo extends AbstractRepo<Storage> {

    Optional<Storage> findByName(String name);

    Optional<Storage> findByLocation(String location);
}
