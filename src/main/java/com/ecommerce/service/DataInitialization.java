package com.ecommerce.service;

import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.modal.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitialization implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeAdminUser();
    }
    private void initializeAdminUser() {
        String adminUsername = "milan123@gmail.com";

        if(userRepository.findByEmail(adminUsername) == null){
            User  adminUser = new User();
            adminUser.setPassword(passwordEncoder.encode("abcd@123"));
            adminUser.setFirstName("Milan");
            adminUser.setLastName("Juneja");
            adminUser.setEmail(adminUsername);
            adminUser.setRole(USER_ROLE.ROLE_ADMIN);
            userRepository.save(adminUser);

        }
    }
}
