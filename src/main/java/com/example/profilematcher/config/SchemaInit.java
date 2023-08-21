package com.example.profilematcher.config;

import com.example.profilematcher.exception.handler.SqlInitializationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class SchemaInit implements BeanPostProcessor {

    @Value("${spring.liquibase.liquibase-schema}")
    private String schemaName;

    @Value("${spring.liquibase.enabled}")
    private boolean liquibaseEnabled;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (liquibaseEnabled && !schemaName.isEmpty() && bean instanceof DataSource) {
            DataSource dataSource = (DataSource) bean;
            try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement()) {
                log.info("Going to create DB schema '{}' if not exists.", schemaName);
                statement.execute("create schema if not exists " + schemaName +"; commit;");
            } catch (SQLException e) {
                throw new SqlInitializationException("Failed to create schema '" + schemaName + "'"+ e);
            }
        }
        return bean;
    }
}
