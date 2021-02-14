package com.mbfuelcalculator.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbfuelcalculator.app.model.CarEvent;
import com.mbfuelcalculator.app.services.kafka.FuelCalculatorService;
import com.mbfuelcalculator.app.services.kafka.Producer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private FuelCalculatorService fuelCalculatorService;
	
	@ApiOperation(value = "Submit fuel event to queue",response = String.class)
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Successfully submitted Fuel Event to kafka Queue")
	})
    @PostMapping(value = "/fuelevent")
    public String produceCarEvent(@RequestBody CarEvent carEvent, Authentication auth) {
    	carEvent.setUsername(auth.getName());
        this.producer.sendMessage(carEvent);
        return "Event submitted successfully";
    }

	@ApiOperation(value = "Submit fuel event to directly to service class",response = String.class)
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Successfully submitted Fuel Event to fuelCalculatorService")
	})
    @PostMapping(value = "/directfuelevent")
    public String callFuelCalculator(@RequestBody CarEvent carEvent, Authentication auth) {
    	carEvent.setUsername(auth.getName());
    	fuelCalculatorService.calculateCost(carEvent);
        return "Event submitted successfully";
    }
}
