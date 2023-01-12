package ru.aasmc.cems.repos.stub;

import ru.aasmc.cems.dao.AbstractEntity;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.NotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class StubAbstractRepo <T extends AbstractEntity> implements AbstractRepo<T> {
    protected Map<Long, T> records = new HashMap<>();

    @Override
    public void save(T entity) {
        if (entity.getId() == null) {
            var id = (long) records.size() + 1;
            entity.setId(id);
        }
        records.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        findById(entity.getId()).ifPresent(r -> records.remove(r.getId()));
    }

    @Override
    public int  deleteById(Long entityId) throws NotFoundException {
        findById(entityId).ifPresent(r -> records.remove(r.getId()));
        return 1;
    }

    @Override
    public Optional<T> findById(Long entityId) {
        if(records.containsKey(entityId)) {
            return Optional.of(records.get(entityId));
        } else {
            throw new NotFoundException("Entity with id "
                    + entityId + " could not be processed because it does not exist.");
        }
    }
}
