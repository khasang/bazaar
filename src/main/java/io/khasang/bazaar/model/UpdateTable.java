package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Zulfia Garifullina on 20.09.2017.
 */
public class UpdateTable {
    private JdbcTemplate jdbcTemplate;

    public UpdateTable() {
    }

    public UpdateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String updateStatus() {
        try {
            jdbcTemplate.execute("UPDATE films\n" +
                    "  SET release_year = release_year - 2\n" +
                    "  WHERE title = 'Titanic';");
            return "Table updated";
        } catch (Exception e) {
            return "Table update failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
