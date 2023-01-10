package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.util.Rank;

public interface DetectiveService extends  AbstractService<Detective> {

    Detective createDetective(Person person, Rank rank);
}
