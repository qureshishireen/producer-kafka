package com.kafka_producer.producer.dto;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class Employee {


    // to send the object  to via kafka
     private String id ;
      private  String name ;
     private  String email ;
     private  String contactNo ;
     private  String designation;
}
