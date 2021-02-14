package com.mbfuelcalculator.app.services.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mbfuelcalculator.app.model.CarEvent;

@Service
public class Producer {

    private static final String TOPIC = "car_event";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //Kafka producer pushes car events to car_event queue
    public void sendMessage(CarEvent message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
}
