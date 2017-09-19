package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class JoinTable {
    public JdbcTemplate jdbcTemplate;

    public JoinTable() {
    }

    public JoinTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createJoin() {
        try {
            jdbcTemplate.execute("SELECT t1.key1, \n" +
                    "       t1.name as Employee, \n" +
                    "       t2.key2, \n" +
                    "       t2.cityname as City,\n" +
                    "       t3.key3, \n" +
                    "       t3.namespecialty as Specialty\n" +
                    "  FROM Human t1\n" +
                    "    INNER JOIN City t2 ON t1.key2 = t2.key2\n" +
                    "    INNER JOIN Specialty t3 ON t1.key3 = t3.key3;\n");
            return "Select JOIN access.";
        } catch (Exception e) {
            return "Select JOIN failed" + e;
        }
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
