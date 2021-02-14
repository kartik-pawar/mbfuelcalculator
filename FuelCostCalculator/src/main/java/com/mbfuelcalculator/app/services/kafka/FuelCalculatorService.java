package com.mbfuelcalculator.app.services.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mbfuelcalculator.app.model.CarEvent;
import com.mbfuelcalculator.app.model.FuelPrice;
import com.mbfuelcalculator.app.repo.redis.CarEventRepository;
import com.mbfuelcalculator.app.repo.redis.FuelPriceRepository;

@Service
public class FuelCalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(FuelCalculatorService.class);
    
	@Value("${fuel.api}")
	private String fuelApi;
	
	@Autowired
	private FuelPriceRepository fuelPriceRepository;
	
	@Autowired
	private CarEventRepository carEventRepository;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public void calculateCost(CarEvent carEvent) {
		//Saving initial fueling event in redis
		if(carEvent.isFueling()) {
			carEventRepository.save(carEvent);
		}
		else {
			//Check redis for Fuel price of current city
			FuelPrice fp=fuelPriceRepository.findById(carEvent.getCity()).orElse(null);
			//fetching and adding fuel details to redis
			if(fp == null) {
				ResponseEntity<Integer[]> responseEntity = restTemplate.getForEntity(fuelApi, Integer[].class);
				int fuelPrice = responseEntity.getBody()[0];
				fp = new FuelPrice(carEvent.getCity(),fuelPrice);
				fuelPriceRepository.save(fp);
			}
			
			CarEvent initialEvent = carEventRepository.findById(carEvent.getUsername()).orElse(null);
			if(initialEvent !=null) {
			 	long lidOpenTime = (carEvent.getDate().getTime() - initialEvent.getDate().getTime())/1000;
			 	long fuelIntake = lidOpenTime/30;
			 	double totalCost = fuelIntake * fp.getPrice();
			 	logger.info("*******User: "+carEvent.getUsername()+" added fuel of Rs.: "+totalCost+"*******");
			 	carEventRepository.delete(initialEvent);
			}
		}
	}
}
