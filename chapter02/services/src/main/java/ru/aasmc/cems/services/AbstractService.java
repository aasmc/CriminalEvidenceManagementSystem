package ru.aasmc.cems.services;

import java.util.Optional;

public interface AbstractService<T> {
    void save(T entity);

    Optional<T> findById(Long entityId);

    void delete(T entity);

    int deleteById(Long entityId);
}
