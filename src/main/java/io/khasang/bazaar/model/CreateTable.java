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
                    "code CHAR (5) CONSTRAINT films_pkey PRIMARY KEY, \n" +
                    "title VARCHAR (255) NOT NULL , \n" +
                    "director_l_name VARCHAR (255) NOT NULL , \n" +
                    "release_year INTEGER NOT NULL);");
            jdbcTemplate.execute("DROP TABLE IF EXISTS directors");
            jdbcTemplate.execute("CREATE TABLE directors (\n" +
                    "id INTEGER CONSTRAINT directors_pkey PRIMARY KEY, \n" +
                    "f_name VARCHAR(255) NOT NULL , \n" +
                    "l_name VARCHAR(255) NOT NULL);");
/*            jdbcTemplate.execute("CREATE TABLE public.films\n" +
                    "(\n" +
                    "  code character(5) NOT NULL,\n" +
                    "  title character varying(255) NOT NULL,\n" +
                    "  year integer NOT NULL,\n" +
                    "  CONSTRAINT firstkey PRIMARY KEY (code)\n" +
                    ");");*/
            return "Table created";
        } catch (Exception e) {
            return "Table creation failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
