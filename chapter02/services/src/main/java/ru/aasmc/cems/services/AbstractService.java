package ru.aasmc.cems.services;

public interface AbstractService<T> {
    void save(T entity);

    T findById(Long entityId);

    void delete(T entity);

    int deleteById(Long entityId);
}
