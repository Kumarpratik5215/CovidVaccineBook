package com.example.covidsurvey.services;

import com.example.covidsurvey.entity.Reservation;
import com.example.covidsurvey.entity.User;
import com.example.covidsurvey.entity.VaccinationCentre;
import com.example.covidsurvey.model.Login;
import com.example.covidsurvey.model.Reserve;

import java.util.List;

public interface Services {
    String verify(Login login);

    String userRegister(User user);


    String addCentre(VaccinationCentre vc);

    List<VaccinationCentre> list();

    String add(Reserve reserve);

    List<Reservation> centreList(String name);

    String deleteCentre(Long id);
}
