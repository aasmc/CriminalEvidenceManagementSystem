package ru.aasmc.cems.services.simpleimpl;

import ru.aasmc.cems.dao.AbstractEntity;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.services.AbstractService;

public abstract class SimpleAbstractService <T extends AbstractEntity> implements AbstractService<T> {
    protected abstract AbstractRepo<T> getRepo();

    public T save(T entity) {
        return getRepo().save(entity);
    }

    @Override
    public T findById(Long entityId){
        return getRepo().findById(entityId).orElse(null);
    }

    @Override
    public void delete(T entity) {
        getRepo().delete(entity);
    }

    @Override
    public int deleteById(Long entityId) {
        return getRepo().deleteById(entityId);
    }
}
