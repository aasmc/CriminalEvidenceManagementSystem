package ru.aasmc.cems.repos.jdbcimpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.repos.DetectiveRepo;
import ru.aasmc.cems.repos.util.DetectiveRowMapper;
import ru.aasmc.cems.util.Rank;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public class JdbcDetectiveRepo extends JdbcAbstractRepo<Detective> implements DetectiveRepo {

    private RowMapper<Detective> rowMapper = new DetectiveRowMapper();

    public JdbcDetectiveRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<Detective> findById(Long entityId) {
        var sql = "select d.ID, d.BADGE_NUMBER, d.RANK, d.ARMED, d.STATUS,d.PERSON_ID, " +
                "p.USERNAME, p.FIRSTNAME, p.LASTNAME, p.HIRINGDATE "+
                "from DETECTIVE d, PERSON p where d.ID= ? and d.PERSON_ID=p.ID";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, entityId));
    }

    @Override
    public Set<Detective> findAll() {
        var sql = "select ID, BADGE_NUMBER, RANK, ARMED, STATUS, PERSON_ID from DETECTIVE";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper));
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        var sql = "select ID, BADGE_NUMBER, RANK, ARMED, STATUS,PERSON_ID " +
                "from DETECTIVE where BADGE_NUMBER= ?";
        var detective = jdbcTemplate.queryForObject(sql, rowMapper, badgeNumber);
        return Optional.ofNullable(detective);
    }

    @Override
    public Detective save(Detective detective) {
        jdbcTemplate.update(
                "insert into DETECTIVE(ID, BADGE_NUMBER, RANK, ARMED, STATUS,PERSON_ID) " +
                        "values(?,?,?,?,?,?)",
                detective.getId(), detective.getBadgeNumber(),
                detective.getRank(), detective.getStatus(),
                detective.getPerson().getId()
        );
        return detective;
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        var sql = "select ID, BADGE_NUMBER, RANK, ARMED, STATUS,PERSON_ID from DETECTIVE " +
                "where RANK= ?";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper, rank));
    }

    @Override
    public void delete(Detective entity) {
        jdbcTemplate.update("delete from DETECTIVE " +
                "where ID =? ", entity.getId());
    }

    @Override
    public int deleteById(Long entityId) {
        return jdbcTemplate.update("delete from DETECTIVE" +
                " where ID =? ", entityId);
    }
}


















