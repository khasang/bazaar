package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class InsertTable {
    private JdbcTemplate jdbcTemplate;

    public InsertTable() {
    }

    public InsertTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createInsert() {
        try {
            jdbcTemplate.execute("INSERT INTO films (code,title,id) VALUES (3,'Terminator 3',3);");
            return " Insert table created";
        } catch (Exception e) {
            return "Insert table creation failed" + e;
        }

    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
