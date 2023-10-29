package com.invertorsoft.academy.myjdbchomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MyJdbcHomeWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyJdbcHomeWorkApplication.class, args);
    }
}