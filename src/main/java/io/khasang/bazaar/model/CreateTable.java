package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;


public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable() {
    }

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createStatus() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS films");
            jdbcTemplate.execute("CREATE TABLE films (\n" +
                    "code CHAR(5) CONSTRAINT firstkey PRIMARY KEY, \n" +
                    "title VARCHAR(255) NOT NULL , \n" +
                    "id INTEGER NOT NULL);");
            return "Table created";
        } catch (Exception e) {
            return "Teable creation failed" + e;
        }
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {

        return jdbcTemplate;
    }

}
