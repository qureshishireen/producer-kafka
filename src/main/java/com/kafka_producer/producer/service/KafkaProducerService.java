package com.kafka_producer.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class KafkaProducerService {
    @Autowired
    public KafkaTemplate<String, Object> kafkaTemplate;

    public void sendTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("KafkaTest", message);
        future.whenComplete((result, exception) ->
        {
            if (exception == null) {
                System.out.println("Sent Message = [" + message + "] with offset = "
                        + result.getRecordMetadata().offset() + " ]"
                );
            } else {
                System.out.println("Unable to send message" + exception.getMessage());
            }
        });
    }



}
