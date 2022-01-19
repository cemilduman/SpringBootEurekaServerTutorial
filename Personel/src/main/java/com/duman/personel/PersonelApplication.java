package com.duman.personel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PersonelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonelApplication.class, args);
    }

}
