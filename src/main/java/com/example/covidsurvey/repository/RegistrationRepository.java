package com.example.covidsurvey.repository;

import com.example.covidsurvey.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Reservation,Long> {
    public  List<Reservation> findAllByVcCentreId(Long id);
}
