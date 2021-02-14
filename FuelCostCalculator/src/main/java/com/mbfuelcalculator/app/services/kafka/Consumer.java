package com.mbfuelcalculator.app.services.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mbfuelcalculator.app.model.CarEvent;

@Service
public class Consumer {
	
	@Autowired
	FuelCalculatorService fuelCalculatorService;

	//Consumes car events from car_event queue and passes it to fuelcalculatorservice to calculatecost
    @KafkaListener(topics = "car_event", groupId = "group_id")
    public void consume(CarEvent carEvent) throws IOException {
    	fuelCalculatorService.calculateCost(carEvent);
    }
}