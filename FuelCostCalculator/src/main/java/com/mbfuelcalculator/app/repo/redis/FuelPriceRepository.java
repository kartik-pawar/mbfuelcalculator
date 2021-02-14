package com.mbfuelcalculator.app.repo.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mbfuelcalculator.app.model.FuelPrice;

@Repository
public interface FuelPriceRepository extends CrudRepository<FuelPrice, String>  {}