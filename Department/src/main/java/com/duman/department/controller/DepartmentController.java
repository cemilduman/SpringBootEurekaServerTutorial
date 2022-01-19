package com.duman.department.controller;

import com.duman.department.entity.Department;
import com.duman.department.repository.DepartmentRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/*
Kendime küçük not, Entity class ve RestTemplate ne yazık ki autowired kullanılamıyor. Bean oluşturamıyor Spring. Sad, but true :(
 */

@RestController

public class DepartmentController {

    @Autowired
    DepartmentRepository repository;

    @Autowired
    EurekaClient client;

    @GetMapping("/department/{id}")
    public String getDepartmentName(@PathVariable("id") Long id){
        RestTemplate restTemplate = new RestTemplate();
        Optional<Department> departmentOpt = repository.findById(id);
        Department department = new Department();
        if(departmentOpt.isPresent()){
            department = departmentOpt.get();
        }
        InstanceInfo info = client.getNextServerFromEureka("duman-city-app", false);
        String cityUrl = info.getHomePageUrl() + "/city/" + department.getCityId();
        String cityName = restTemplate.getForObject(cityUrl,String.class);
        return " Department Name: " + department.getName() + " City Name: " + cityName;
    }
}
