package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Zulfia Garifullina on 20.09.2017.
 */
public class CaseWhenTable {
    private JdbcTemplate jdbcTemplate;

    public CaseWhenTable() {
    }

    public CaseWhenTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String caseWhenStatus() {
        try {
            jdbcTemplate.execute("SELECT title, release_year,\n " +
                    " CASE WHEN release_year > 2000 THEN 'Meh' ELSE 'Brilliant' END AS review\n" +
                    " FROM films;");
            return "Case when successful";
        } catch (Exception e) {
            return "Case when failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
