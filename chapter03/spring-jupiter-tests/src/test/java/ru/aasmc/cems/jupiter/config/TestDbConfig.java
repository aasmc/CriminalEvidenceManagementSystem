package ru.aasmc.cems.jupiter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;

@PropertySource("classpath:/db/test-datasource.properties")
public class TestDbConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("db.driverClassName"));
        ds.setUrl(environment.getProperty("db.url"));
        ds.setUsername(environment.getProperty("db.username"));
        ds.setPassword(environment.getProperty("db.password"));
        DatabasePopulatorUtils.execute(databasePopulator(), ds);
        return ds;
    }

    @Value("classpath:db/schema.sql")
    private Resource schemaScript;

    @Value("classpath:db/test-data.sql")
    private Resource dataScript;

    private DatabasePopulator databasePopulator() {
        final var populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        populator.addScript(dataScript);
        return populator;
    }

    /**
     * Needed because temporary db file need to be deleted.
     */
    @PostConstruct
    void discardDatabase() {
        final String currentDir = System.getProperty("user.dir");
        int start = environment.getProperty("db.url").indexOf("./")+ 2;
        String dbName =  environment.getProperty("db.url").substring(start);
        File one  = new File(currentDir.concat(File.separator).concat(dbName).concat(".mv.db"));
        one.deleteOnExit();
        File two  = new File(currentDir.concat(File.separator).concat(dbName).concat(".trace.db"));
        two.deleteOnExit();
    }
}






























