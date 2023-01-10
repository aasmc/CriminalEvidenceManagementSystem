package ru.aasmc.cems.repos.util;

import org.springframework.jdbc.core.RowMapper;
import ru.aasmc.cems.dao.CriminalCase;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.util.CaseStatus;
import ru.aasmc.cems.util.CaseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CriminalCaseRowMapper implements RowMapper<CriminalCase> {
    @Override
    public CriminalCase mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("ID");
        var number = rs.getString("CASE_NUMBER");
        var type = rs.getString("CASE_TYPE");
        var status = rs.getString("STATUS");
        var shortDescription = rs.getString("SHORT_DESCRIPTION");
        var detectiveId = rs.getLong("LEAD_INVESTIGATOR_ID");

        var cc = new CriminalCase();
        cc.setId(id);
        cc.setNumber(number);
        cc.setType(CaseType.valueOf(type));
        cc.setStatus(CaseStatus.valueOf(status));
        cc.setShortDescription(shortDescription);

        var detective = new Detective();
        detective.setId(detectiveId);
        cc.setLeadInvestigator(detective);
        return cc;
    }
}
