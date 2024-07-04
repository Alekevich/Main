package com.example.SpringSecurity_3.repository;


import com.example.SpringSecurity_3.model.Permissions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerimissionsRepository  extends JpaRepository<Permissions, Integer> {

    @Query("SELECT p FROM Permissions p WHERE p.role = 'ROLE_STUDENT'")
    Permissions getStandartPermissions();

}
