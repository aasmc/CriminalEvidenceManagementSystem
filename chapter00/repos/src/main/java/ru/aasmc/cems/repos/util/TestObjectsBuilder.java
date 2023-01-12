package ru.aasmc.cems.repos.util;

import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.util.*;

import java.time.LocalDateTime;

public class TestObjectsBuilder {

    public static Detective buildDetective(String firstName, String lastName, Rank rank, String badgeNumber) {
        var  detective = new Detective();
        var person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setHiringDate(LocalDateTime.now());
        person.setUsername(firstName.concat(lastName));
        person.setPassword("whatever");
        detective.setPerson(person);
        detective.setBadgeNumber(badgeNumber);
        detective.setArmed(true);
        detective.setStatus(EmploymentStatus.ACTIVE);
        detective.setRank(rank);
        return detective;
    }


    public static CriminalCase buildCase(Detective leadInvestigator, CaseType caseType, CaseStatus status){
        var criminalCase = new CriminalCase();
        criminalCase.setLeadInvestigator(leadInvestigator);
        criminalCase.setNumber(NumberGenerator.getEvidenceNumber());
        criminalCase.setType(caseType);
        criminalCase.setStatus(status);
        return criminalCase;
    }

}
