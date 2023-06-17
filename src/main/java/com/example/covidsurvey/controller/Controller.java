package com.example.covidsurvey.controller;


import com.example.covidsurvey.entity.Reservation;
import com.example.covidsurvey.entity.User;
import com.example.covidsurvey.entity.VaccinationCentre;
import com.example.covidsurvey.model.Login;
import com.example.covidsurvey.model.Reserve;
import com.example.covidsurvey.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Services services;

    @GetMapping("/user/list")
    @PreAuthorize("hasAuthority('USER')")
    public List<VaccinationCentre> list(){
        return services.list();
    }

    @GetMapping("/admin/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Reservation> listFromName(String name){
        return services.centreList(name);
    }
    @PostMapping("/admin/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String RemoveCentre(Long id){
        return services.deleteCentre(id);
    }

    @PostMapping("/user/reservation")
    @PreAuthorize("hasAuthority('USER')")
    public String reserved(Reserve reserve){
        return services.add(reserve);
    }

    @PostMapping("/registration")
    public String userRegistration(@RequestBody User user){
        return services.userRegister(user);
    }

    @PostMapping("/admin/addcentre")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCentre(@RequestBody VaccinationCentre vc){
        return services.addCentre(vc);
    }
}
