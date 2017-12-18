package com.boot.dao.impl;

import com.boot.dao.CarDao;
import com.boot.entity.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<Car> implements CarDao {

    @Override
    protected Class<Car> getEntityClass() {
        return Car.class;
    }
}