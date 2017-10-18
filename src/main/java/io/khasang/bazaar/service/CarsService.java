package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.CarsEntity;

import java.util.List;

public interface CarsService {
    CarsEntity getById(Long id);

    CarsEntity addCarsEntity(CarsEntity carsEntity);

    CarsEntity updateCarsEntity(CarsEntity carsEntity);

    List<CarsEntity> getList();
}
