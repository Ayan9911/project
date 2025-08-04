package com.service.car_spark.service;


import org.springframework.stereotype.Service;

@Service  // Tells Spring this is a service class to manage
public class CarServiceImpl implements CarService {

    @Override
    public void processCarData(String data) {
        // Add your business logic here
        System.out.println("Processing car data: " + data);
    }
}
