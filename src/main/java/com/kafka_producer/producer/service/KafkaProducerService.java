package com.kafka_producer.producer.service;

import com.kafka_producer.producer.dto.Employee;
import com.kafka_producer.producer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class KafkaProducerService {
    @Autowired
    public KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.topic.name}")
    private String topicName;


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


    public void sendToTopic1(Employee employee) {

        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("consumerTopic", employee);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + employee.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            employee.toString() + "] due to : " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }
    }


    //
    public void sendEvents(User user) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, user);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + user.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            user.toString() + "] due to : " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }





}
