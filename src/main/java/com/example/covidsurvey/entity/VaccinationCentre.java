package com.example.covidsurvey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long centreId;
    private String centreName;
    private String centreAddress;
    private int contact;
    private String workingHours;
}
