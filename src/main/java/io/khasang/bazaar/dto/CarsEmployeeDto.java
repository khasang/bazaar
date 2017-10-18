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
    private Set<EmployeeDTO> employeeEntities = new HashSet<>();

    public CarsEmployeeDto getCarsEmployeeDto(CarsEntity carsEntity) {
        CarsEmployeeDto carsEmployeeDto = new CarsEmployeeDto();
        carsEmployeeDto.setId(carsEntity.getId());
        carsEmployeeDto.setYear(carsEntity.getYear());
        carsEmployeeDto.setModel(carsEntity.getModel());

        for (EmployeeEntity employeeEntity : carsEntity.getEmployeeSet()) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setFirstName(employeeEntity.getFirstName());
            employeeDTO.setLastName(employeeEntity.getLastName());
            employeeEntities.add(employeeDTO);
        }

        carsEmployeeDto.setEmployeeEntities(employeeEntities);

        return carsEmployeeDto;
    }

    public Set<EmployeeDTO> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(Set<EmployeeDTO> employeeEntities) {
        this.employeeEntities = employeeEntities;
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

}
