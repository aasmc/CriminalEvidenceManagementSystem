package ru.aasmc.cems.repos.util;

import org.springframework.jdbc.core.RowMapper;
import ru.aasmc.cems.dao.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageRowMapper implements RowMapper<Storage> {
    @Override
    public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("ID");
        var name = rs.getString("NAME");
        var location = rs.getString("LOCATION");

        var storage = new Storage();
        storage.setId(id);
        storage.setName(name);
        storage.setLocation(location);
        return storage;
    }
}
