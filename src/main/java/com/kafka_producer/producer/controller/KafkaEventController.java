package com.kafka_producer.producer.controller;

import com.kafka_producer.producer.dto.Employee;
import com.kafka_producer.producer.dto.User;
import com.kafka_producer.producer.service.KafkaProducerService;
import com.kafka_producer.producer.util.CsvReaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


    // kafka error handling  code

    @PostMapping("/publish/msg")
    public ResponseEntity<?> publishEvent(@RequestBody User user) {
        try {
            List<User> userList = CsvReaderUtils.readDataFromCsvFile();
            userList.forEach(user1 -> kafkaProducerService.sendEvents(user));
            return ResponseEntity.ok("Message published successfully");
        } catch (Exception exception) {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
