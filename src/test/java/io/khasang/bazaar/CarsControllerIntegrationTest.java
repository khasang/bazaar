package io.khasang.bazaar;

import io.khasang.bazaar.dto.CarsEmployeeDto;
import io.khasang.bazaar.entity.CarsEntity;
import io.khasang.bazaar.entity.EmployeeEntity;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CarsControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/cars";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/all";

    @Test
    public void addCars() {
        CarsEntity carsEntity = createCars();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CarsEmployeeDto> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                CarsEmployeeDto.class,
                carsEntity.getId()
        );
        CarsEmployeeDto receivedCat = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

    }

    @Test
    public void getAllCats() {
        RestTemplate restTemplate = new RestTemplate();
        createCars();
        createCars();

        ResponseEntity<List<CarsEntity>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarsEntity>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    private CarsEntity createCars() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        CarsEntity carsEntity = prefillCarsEntity();
        HttpEntity<CarsEntity> httpEntity = new HttpEntity<>(carsEntity, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        CarsEntity createdCars = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                CarsEntity.class).getBody();
        assertNotNull(createdCars);

        return createdCars;
    }

    private CarsEntity prefillCarsEntity() {
        CarsEntity carsEntity = new CarsEntity();
        carsEntity.setModel("Lada");
        carsEntity.setYear(2010);

        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setFirstName("Riska");
        employeeEntity1.setLastName("asd");

        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setFirstName("murs");
        employeeEntity2.setLastName("asasdad");

        carsEntity.addEmployees(employeeEntity1);
        carsEntity.addEmployees(employeeEntity2);

        return carsEntity;
    }
}
