package com.example.covidsurvey.repository;

import com.example.covidsurvey.entity.VaccinationCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<VaccinationCentre,Long> {
    public VaccinationCentre findByCentreId(Long id);
    public VaccinationCentre findByCentreName(String s);
    public VaccinationCentre deleteByCentreId(Long id);
}
