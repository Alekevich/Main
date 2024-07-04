package com.example.SpringSecurity_3.service;


import com.example.SpringSecurity_3.model.Permissions;
import com.example.SpringSecurity_3.model.User;
import com.example.SpringSecurity_3.repository.UserRepository;
import com.example.SpringSecurity_3.repository.PerimissionsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUser {

    private final PerimissionsRepository perimissionsRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public String addUser(User user, String rePassword) {

        User user1 = userRepository.findByEmail(user.getEmail());

        if(user1 != null){
            return "EmailNotValid";
        }

        if(!user.getPassword().equals(rePassword)){
            return "IncorrectPassword";
        }


        user.setPassword(bCryptPasswordEncoder.encode(rePassword));

        Permissions permissions = perimissionsRepository.getStandartPermissions();

        user.setPermissions(List.of(permissions));

        userRepository.save(user);

        return "SuccessAddUser";


    }

    public String changePass(String currentPass, String newPass, String reNewPass) {

        if(!passwordEncoder.matches(currentPass,getCurrentUser().getPassword())){
            return "IncorrectPassword";
        }

        if(!newPass.equals(reNewPass)){
            return "IncorrectPassword";
        }

        getCurrentUser().setPassword(passwordEncoder.encode(newPass));

        userRepository.save(getCurrentUser());

        return "Success";
        
    }
}
