package com.kafka_producer.producer.util;

import com.kafka_producer.producer.dto.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class CsvReaderUtils {


    public static List<User> readDataFromCsvFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new ClassPathResource("user.csv").getInputStream()))) {
            CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(bufferedReader).withType(User.class).build();
            return csvToBean.parse();
        } catch (Exception exception) {
            exception.printStackTrace();
            // Handle the exception as needed
            return null;
        }
    }


}
