package com.mbfuelcalculator.app.repo.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mbfuelcalculator.app.model.CarEvent;

@Repository
public interface CarEventRepository extends CrudRepository<CarEvent, String>  {}
