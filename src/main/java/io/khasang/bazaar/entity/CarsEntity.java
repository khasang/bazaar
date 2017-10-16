package io.khasang.bazaar.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CarsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
    private String model;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<EmployeeEntity> employeeSet = new HashSet<>();

    public CarsEntity() {
    }

    public CarsEntity(Integer year, String model) {
        this.year = year;
        this.model = model;
    }

    public void addEmployees(EmployeeEntity employeeEntity) {
        employeeSet.add(employeeEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarsEntity that = (CarsEntity) o;

        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        return model != null ? model.equals(that.model) : that.model == null;
    }

    @Override
    public int hashCode() {
        int result = year != null ? year.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Set<EmployeeEntity> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<EmployeeEntity> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
