package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Zulfia Garifullina on 20.09.2017.
 */
public class DeleteFromTable {
    private JdbcTemplate jdbcTemplate;

    public DeleteFromTable() {
    }

    public DeleteFromTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String deleteStatus() {
        try {
            jdbcTemplate.execute("DELETE FROM films\n" +
                    "  WHERE title = 'Wonder Woman' AND release_year = 2017;");
            return "Table record deleted";
        } catch (Exception e) {
            return "Table record deletion failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
