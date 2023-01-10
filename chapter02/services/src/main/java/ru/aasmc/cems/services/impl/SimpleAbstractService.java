package ru.aasmc.cems.services.impl;

import ru.aasmc.cems.dao.AbstractEntity;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.services.AbstractService;

import java.util.Optional;

public abstract class SimpleAbstractService <T extends AbstractEntity> implements AbstractService<T> {
    abstract AbstractRepo<T> getRepo();

    public void save(T entity) {
        getRepo().save(entity);
    }

    @Override
    public Optional<T> findById(Long entityId){
        return getRepo().findById(entityId);
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
