package io.khasang.bazaar.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class PopulateTable {
    private JdbcTemplate jdbcTemplate;

    public PopulateTable() {
    }

    public PopulateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String populateStatus() {
        try {
            jdbcTemplate.execute("DELETE FROM films;");
            jdbcTemplate.execute("INSERT INTO films\n" +
                    "(code, title, director_l_name, release_year)\n" +
                    "  VALUES ('HR814', 'Pulp Fiction', 'Tarantino', 1994);");
            jdbcTemplate.execute("INSERT INTO films\n" +
                    "(code, title, director_l_name, release_year)\n" +
                    "  VALUES ('KW872', 'The Fifth Element', 'Besson', 1997);");
            jdbcTemplate.execute("INSERT INTO films\n" +
                    "(code, title, director_l_name, release_year)\n" +
                    "  VALUES ('TE271', 'Titanic', 'Cameron', 1999);");
            jdbcTemplate.execute("INSERT INTO films\n" +
                    "(code, title, director_l_name, release_year)\n" +
                    "  VALUES ('WT732', 'Wonder Woman', 'Jenkins', 2017);");
            jdbcTemplate.execute("DELETE FROM directors;");
            jdbcTemplate.execute("INSERT INTO directors\n" +
                    "(id, f_name, l_name)\n" +
                    "  VALUES ('201', 'Quentin', 'Tarantino');");
            jdbcTemplate.execute("INSERT INTO directors\n" +
                    "(id, f_name, l_name)\n" +
                    "  VALUES ('202', 'Luc', 'Besson');");
            jdbcTemplate.execute("INSERT INTO directors\n" +
                    "(id, f_name, l_name)\n" +
                    "  VALUES ('203', 'James', 'Cameron');");
            jdbcTemplate.execute("INSERT INTO directors\n" +
                    "(id, f_name, l_name)\n" +
                    "  VALUES ('204', 'Patty', 'Jenkins');");
            return "Table populated";
        } catch (Exception e) {
            return "Table population failed " + e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
