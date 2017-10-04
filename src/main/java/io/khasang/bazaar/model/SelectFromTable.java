package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Zulfia Garifullina on 20.09.2017.
 */
public class SelectFromTable {
    private JdbcTemplate jdbcTemplate;

    public SelectFromTable() {
    }

    public SelectFromTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String selectStatus() {
        try {
            jdbcTemplate.execute("SELECT title FROM films\n" +
                    "  WHERE release_year = 1994;");
            return "Select successful";
        } catch (Exception e) {
            return "Select failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
