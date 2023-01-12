package ru.aasmc.cems.repos.stub;

import org.apache.commons.lang3.NotImplementedException;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.PersonRepo;

import java.util.Optional;
import java.util.Set;

public class StubPersonRepo extends StubAbstractRepo<Person> implements PersonRepo {
    @Override
    public Optional<Person> findByUsername(String username) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Person> findAll() {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Person update(Person entity)  {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public long count() {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public int updatePassword(Long personId, String newPass) {
        throw new NotImplementedException("Not needed for this example");
    }
}

