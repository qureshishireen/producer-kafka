package com.kafka_producer.producer.controller;

import com.kafka_producer.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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







}
