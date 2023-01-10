package ru.aasmc.cems.repos.util;

import org.springframework.jdbc.core.RowMapper;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.util.EmploymentStatus;
import ru.aasmc.cems.util.Rank;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetectiveRowMapper implements RowMapper<Detective> {
    @Override
    public Detective mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("ID");
        var badgeNumber = rs.getString("BADGE_NUMBER");
        var rank = rs.getString("RANK");
        var armed = rs.getBoolean("ARMED");
        var status = rs.getString("STATUS");
        var personId = rs.getLong("PERSON_ID");

        var person = new Person();
        person.setId(personId);
        person.setUsername(rs.getString("USERNAME"));
        person.setFirstName(rs.getString("FIRSTNAME"));
        person.setLastName(rs.getString("LASTNAME"));
        person.setHiringDate(rs.getTimestamp("HIRINGDATE").toLocalDateTime());

        var detective = new Detective();
        detective.setId(id);
        detective.setPerson(person);
        detective.setBadgeNumber(badgeNumber);
        detective.setRank(Rank.valueOf(rank));
        detective.setArmed(armed);
        detective.setStatus(EmploymentStatus.valueOf(status));

        return detective;
    }
}
