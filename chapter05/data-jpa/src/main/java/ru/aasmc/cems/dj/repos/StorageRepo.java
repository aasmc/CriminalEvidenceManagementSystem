package ru.aasmc.cems.dj.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.aasmc.cems.dao.Storage;

public interface StorageRepo extends PagingAndSortingRepository<Storage, Long> {
}