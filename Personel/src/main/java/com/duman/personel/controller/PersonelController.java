package com.duman.personel.controller;

import com.duman.personel.entity.Personel;
import com.duman.personel.repository.PersonelRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonelController {

    @Autowired
    PersonelRepository personelRepository;

    @Autowired
    EurekaClient client;

    @GetMapping
    public String welcome(){
        return "Welcome to Personel Infotmation System";
    }

    @GetMapping("/personel")
    public List<Personel> getPersonelList(){
        return personelRepository.findAll();
    }

    @GetMapping("/personel/{id}")
    public String getPersonelById(@PathVariable("id") Long id){
        RestTemplate restTemplate = new RestTemplate();
        Optional<Personel> personelOpt = personelRepository.findById(id);
        Personel personel = new Personel();
        if(personelOpt.isPresent()){
            personel = personelOpt.get();
        }
        InstanceInfo info = client.getNextServerFromEureka("duman-department-app", false);
        String departmentUrl = info.getHomePageUrl() + "/department/" + personel.getDepartmentId();
        String personelFullName = personel.toString() + restTemplate.getForObject(departmentUrl, String.class);
        return personelFullName;
    }
}
