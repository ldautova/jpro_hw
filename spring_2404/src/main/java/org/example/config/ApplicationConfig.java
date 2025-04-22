package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * ApplicationContext.
 *
 * @author Lina_Dautova
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public HikariDataSource getDataSource(Environment env) {
        return new HikariDataSource(getHikariConfig(env));
    }

    private HikariConfig getHikariConfig(Environment env) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("datasource.url"));
        config.setUsername(env.getProperty("datasource.username"));
        config.setPassword(env.getProperty("datasource.password"));
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
