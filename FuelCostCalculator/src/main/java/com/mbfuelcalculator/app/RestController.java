package com.mbfuelcalculator.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbfuelcalculator.app.model.CarEvent;
import com.mbfuelcalculator.app.services.kafka.FuelCalculatorService;
import com.mbfuelcalculator.app.services.kafka.Producer;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private FuelCalculatorService fuelCalculatorService;
	
    @PostMapping(value = "/fuelevent")
    public void produceCarEvent(@RequestBody CarEvent carEvent, Authentication auth) {
    	carEvent.setUsername(auth.getName());
        this.producer.sendMessage(carEvent);
    }
    
    @PostMapping(value = "/directfuelevent")
    public void callFuelCalculator(@RequestBody CarEvent carEvent, Authentication auth) {
    	carEvent.setUsername(auth.getName());
    	fuelCalculatorService.calculateCost(carEvent);
    }
}
