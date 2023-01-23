package ru.aasmc.cems.dj.services;

import ru.aasmc.cems.dao.Storage;

import java.util.List;

public interface StorageService {

    List<Storage> findAll();

    Storage findById(Long id);

    Storage save(Storage storage);
}
