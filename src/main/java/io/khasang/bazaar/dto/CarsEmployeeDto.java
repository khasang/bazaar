package io.khasang.bazaar.dto;

import io.khasang.bazaar.entity.CarsEntity;
import io.khasang.bazaar.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CarsEmployeeDto {
    private Long id;
    private Integer year;
    private String model;
    private Set<EmployeeEntity> set = new HashSet<>();

    public CarsEmployeeDto getCarsEmployeeDto(CarsEntity carsEntity) {
        CarsEmployeeDto carsEmployeeDto = new CarsEmployeeDto();
        carsEmployeeDto.setId(carsEntity.getId());
        carsEmployeeDto.setYear(carsEntity.getYear());
        carsEmployeeDto.setModel(carsEntity.getModel());
        carsEmployeeDto.setSet(carsEntity.getEmployeeSet());
        return carsEmployeeDto;
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

    public Set<EmployeeEntity> getSet() {
        return set;
    }

    public void setSet(Set<EmployeeEntity> set) {
        this.set = set;
    }
}
