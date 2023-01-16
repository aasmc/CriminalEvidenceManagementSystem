package ru.aasmc.repos;

import org.springframework.data.repository.CrudRepository;
import ru.aasmc.entities.Person;

public interface PersonRepo extends CrudRepository<Person, Long> {
}
