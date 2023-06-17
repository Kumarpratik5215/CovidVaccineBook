package com.example.covidsurvey.services;

import com.example.covidsurvey.entity.Reservation;
import com.example.covidsurvey.model.Reserve;
import com.example.covidsurvey.repository.RegistrationRepository;
import com.example.covidsurvey.repository.UserRepository;
import com.example.covidsurvey.repository.VaccinationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceImplementationTest {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VaccinationRepository vaccinationRepository;

    @Test
    public void add() {
        Reserve reserve = Reserve.builder().email("aditya16@gmail.com").id(1l).build();
        if (reserve.getEmail() != "" && reserve.getId() != 0) {
            List<Reservation> vc = registrationRepository.findAllByVcCentreId(reserve.getId());
            if (vc.size() < 3) {
                Reservation reservation = Reservation.builder().vc(vaccinationRepository.findByCentreId(reserve.getId())).user(userRepository.findUserByUserEmail(reserve.getEmail())).build();
                registrationRepository.save(reservation);
                System.out.println("success");
            } else {
                System.out.println("Seats are full" + vc.size());
                for (Reservation r : vc) {
                    System.out.println(r);
                }
            }
            ;
        } else System.out.println("check details");
    }

    @Test
    public void centreList (){
        String name="centre1";
         List<Reservation> reg=registrationRepository.findAllByVcCentreId(vaccinationRepository.findByCentreName(name).getCentreId());
         for(Reservation r : reg) System.out.println(r);
    }
}
