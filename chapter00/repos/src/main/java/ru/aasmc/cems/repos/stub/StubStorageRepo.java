package ru.aasmc.cems.repos.stub;

import org.apache.commons.lang3.NotImplementedException;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.StorageRepo;

import java.util.Optional;

public class StubStorageRepo extends StubAbstractRepo<Storage> implements StorageRepo {
    @Override
    public Optional<Storage> findByName(String name) {
        switch (name) {
            case "Here":
                return Optional.of(records.get(2L));
            case "There":
                return Optional.of(records.get(3L));
        }
        return Optional.empty();
    }

    @Override
    public Storage update(Storage entity) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}

