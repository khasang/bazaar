package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Zulfia Garifullina on 20.09.2017.
 */
public class SubqueryTable {
    private JdbcTemplate jdbcTemplate;

    public SubqueryTable() {
    }

    public SubqueryTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String subqueryStatus() {
        try {
            jdbcTemplate.execute("SELECT title FROM films\n" +
                    "  WHERE director_l_name IN (SELECT l_name FROM directors WHERE id = 203);");
            return "Subquery successful";
        } catch (Exception e) {
            return "Subquery failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
