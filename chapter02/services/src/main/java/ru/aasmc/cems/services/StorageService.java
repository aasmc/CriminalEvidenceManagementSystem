package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.Storage;

public interface StorageService extends AbstractService<Storage> {

    Storage createStorage(String name, String location);
}
