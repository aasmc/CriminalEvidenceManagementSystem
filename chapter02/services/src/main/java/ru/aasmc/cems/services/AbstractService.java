package ru.aasmc.cems.services;

public interface AbstractService<T> {
    T save(T entity);

    T findById(Long entityId);

    void delete(T entity);

    int deleteById(Long entityId);
}
