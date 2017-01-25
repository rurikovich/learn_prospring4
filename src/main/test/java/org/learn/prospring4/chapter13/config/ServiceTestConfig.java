package org.learn.prospring4.chapter13.config;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by rurik on 25.01.2017.
 */
@Configuration
@ImportResource("classpath:META-INF/spring/persistence-app-context.xml")
@ComponentScan(basePackages = {"org.learn.prospring4.chapter13","org.learn.prospring4.chapter12.rest"})
@Profile("test")
public class ServiceTestConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:META-INF/sql/schema.sql").build();
    }

    @Bean(name = "databaseTester")
    public DataSourceDatabaseTester dataSourceDatabaseTester() {
        return new DataSourceDatabaseTester(dataSource());
    }

    @Bean(name = "xlsDataFileLoader")
    public XlsDataFileLoader xlsDataFileLoader() {
        return new XlsDataFileLoader();
    }

}
