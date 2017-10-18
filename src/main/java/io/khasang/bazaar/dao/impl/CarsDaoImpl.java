package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.CarsDao;
import io.khasang.bazaar.entity.CarsEntity;

public class CarsDaoImpl extends BasicDaoImpl<CarsEntity> implements CarsDao {
    public CarsDaoImpl(Class<CarsEntity> entityClass) {
        super(entityClass);
    }
}
