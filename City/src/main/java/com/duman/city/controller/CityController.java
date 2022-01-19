package com.duman.city.controller;

import com.duman.city.entity.City;
import com.duman.city.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CityController {

    @Autowired
    CityRepository repository;

    @GetMapping("/city/{id}")
    public String getCityName(@PathVariable("id") Long id){
        Optional<City> cityOpt = repository.findById(id);
        City city = new City();
        if(cityOpt.isPresent()){
            city = cityOpt.get();
        }
        return city.name;
    }
}
