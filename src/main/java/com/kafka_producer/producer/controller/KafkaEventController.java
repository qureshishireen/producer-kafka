package com.kafka_producer.producer.controller;

import com.kafka_producer.producer.dto.Employee;
import com.kafka_producer.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/producer")
public class KafkaEventController {


    @Autowired
    private KafkaProducerService kafkaProducerService;


    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publisher(@PathVariable String message) {
        try {
            kafkaProducerService.sendTopic(message);
            return ResponseEntity.ok("message sends successfully");

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/publish")
    public void sendEmployeeObjectToTopic(@RequestBody Employee employee) {
        kafkaProducerService.sendToTopic1(employee);


    }







}
