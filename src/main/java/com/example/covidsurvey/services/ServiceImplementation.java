package com.example.covidsurvey.services;

import com.example.covidsurvey.entity.Reservation;
import com.example.covidsurvey.entity.User;
import com.example.covidsurvey.entity.VaccinationCentre;
import com.example.covidsurvey.model.Login;
import com.example.covidsurvey.model.Reserve;
import com.example.covidsurvey.repository.RegistrationRepository;
import com.example.covidsurvey.repository.UserRepository;
import com.example.covidsurvey.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ServiceImplementation implements Services{

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VaccinationRepository vaccinationRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String verify(Login login) {
        if(Objects.nonNull(userRepository.findUserByUserEmail(login.getEmail()))) return "success";
        else
        return "Check your email and password";
    }

    @Override
    public String userRegister(User user) {
        if(Objects.nonNull(user) && user.getUserEmail()!=""
                && user.getUserName()!="" && user.getUserPass()!="" &&
                user.getUserNumber()>1000000000)
        {user.setUserPass(bCryptPasswordEncoder.encode(user.getUserPass()));
            userRepository.save(user); return "Success";}
        else
            return "Check your details";
        }



    @Override
    public String addCentre(VaccinationCentre vc) {
        if (Objects.nonNull(vc) && vc.getCentreName()!="" && vc.getCentreAddress()!=""
                && vc.getContact()!=0 && vc.getWorkingHours()!= "")
        { vaccinationRepository.save(vc);
        return "Success";}
        else
            return "Check your details";
    }

    @Override
    public List<VaccinationCentre> list() {
        return vaccinationRepository.findAll();
    }

    @Override
    public String add(Reserve reserve) {
        if(reserve.getEmail()!="" && reserve.getId()!=0){
            List<Reservation> vc =registrationRepository.findAllByVcCentreId(reserve.getId());
            if(vc.size()<11){
            Reservation reservation = Reservation.builder().vc(vaccinationRepository.findByCentreId(reserve.getId())).user(userRepository.findUserByUserEmail(reserve.getEmail())).build();
            registrationRepository.save(reservation);
            return "success";}
            else return "Seats are full";
        }else return "check details" ;
    }

    @Override
    public List<Reservation> centreList(String name) {
        return registrationRepository.findAllByVcCentreId(vaccinationRepository.findByCentreName(name).getCentreId());
    }

    @Override
    public String deleteCentre(Long id) {
        if(id!=0 && Objects.nonNull(vaccinationRepository.findByCentreId(id))){
            vaccinationRepository.deleteByCentreId(id);
            return "deleted";
        }else
        return "centre no exist";
    }


}
