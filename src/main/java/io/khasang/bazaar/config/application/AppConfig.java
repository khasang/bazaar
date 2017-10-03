package io.khasang.bazaar.config.application;

import io.khasang.bazaar.dao.CatDao;
import io.khasang.bazaar.dao.GoodsCategoryDao;
import io.khasang.bazaar.dao.impl.CatDaoImpl;
import io.khasang.bazaar.dao.impl.GoodsCategoryDaoImpl;
import io.khasang.bazaar.entity.Cat;
import io.khasang.bazaar.entity.GoodsCategory;
import io.khasang.bazaar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value = "classpath:util.properties")
@PropertySource(value = "classpath:auth.properties")
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());
        jdbcDao.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcDao.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcDao;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.name"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public CreateTable createTable(){
        return new CreateTable(jdbcTemplate());
    }

    @Bean
    public PopulateTable populateTable(){
        return new PopulateTable(jdbcTemplate());
    }

    @Bean
    public SelectFromTable selectFromTable(){
        return new SelectFromTable(jdbcTemplate());
    }

    @Bean
    public UpdateTable updateTable(){
        return new UpdateTable(jdbcTemplate());
    }

    @Bean
    public DeleteFromTable deleteFromTable(){
        return new DeleteFromTable(jdbcTemplate());
    }

    @Bean
    public JoinTable joinTable(){
        return new JoinTable(jdbcTemplate());
    }

    @Bean
    public SubqueryTable subqueryTable(){
        return new SubqueryTable(jdbcTemplate());
    }

    @Bean
    public CaseWhenTable caseWhenTable(){
        return new CaseWhenTable(jdbcTemplate());
    }

    @Bean
    public CatDao catDao() {
        return new CatDaoImpl(Cat.class);
    }

    @Bean
    public GoodsCategoryDao goodsCategoryDao() {
        return new GoodsCategoryDaoImpl(GoodsCategory.class);
    }
}
