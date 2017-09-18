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
//            jdbcTemplate.execute("CREATE TABLE films (\n" +
//                    "code char(5) CONSTRAINT firstkey PRIMARY KEY, \n" +
//                    "title varchar(255) NOT NULL , \n" +
//                    "id integer NOT NULL);");
            jdbcTemplate.execute("CREATE TABLE public.films\n" +
                    "(\n" +
                    "  code character(5) NOT NULL,\n" +
                    "  title character varying(255) NOT NULL,\n" +
                    "  id integer NOT NULL,\n" +
                    "  CONSTRAINT firstkey PRIMARY KEY (code)\n" +
                    ");");
            return "Table created";
        } catch (Exception e) {
            return "Table creation failed" + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
