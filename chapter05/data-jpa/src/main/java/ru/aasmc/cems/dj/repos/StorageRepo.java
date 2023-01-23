package ru.aasmc.cems.dj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aasmc.cems.dao.Storage;

public interface StorageRepo extends JpaRepository<Storage, Long> {
}