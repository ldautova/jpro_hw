package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ApplicationContext.
 *
 * @author Lina_Dautova
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public HikariDataSource getDataSource() {
        return new HikariDataSource(getHikariConfig());
    }

    private HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/javapro");
        config.setUsername("test");
        config.setPassword("test");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return config;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
