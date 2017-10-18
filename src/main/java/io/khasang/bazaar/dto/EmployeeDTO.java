package io.khasang.bazaar.dto;

import io.khasang.bazaar.entity.CarsEntity;

import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private Set<CarsEntity> cars = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<CarsEntity> getCars() {
        return cars;

    }

    public void setCars(Set<CarsEntity> cars) {
        this.cars = cars;
    }
}
