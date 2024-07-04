package com.example.SpringSecurity_3.repository;


import com.example.SpringSecurity_3.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);




}
