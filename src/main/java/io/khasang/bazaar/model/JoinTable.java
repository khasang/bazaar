package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Zulfia Garifullina on 20.09.2017.
 */
public class JoinTable {
    private JdbcTemplate jdbcTemplate;

    public JoinTable() {
    }

    public JoinTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String joinStatus() {
        try {
            jdbcTemplate.execute("SELECT title, f_name, l_name FROM films f JOIN directors d\n" +
                    "  ON f.director_l_name = d.l_name WHERE release_year < 2000;");
            return "Join successful";
        } catch (Exception e) {
            return "Join failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
