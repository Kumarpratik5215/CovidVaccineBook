package com.example.covidsurvey.repository;

import com.example.covidsurvey.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUserEmail(String email);
     Optional<User> findByUserEmail(String email);

}
