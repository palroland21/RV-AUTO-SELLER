package com.rv_auto_seller.config;

import com.rv_auto_seller.model.User;
import com.rv_auto_seller.model.enums.Role;
import com.rv_auto_seller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username:admin}")
    private String adminUsername;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    @Value("${app.admin.email:admin@rv.local}")
    private String adminEmail;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(adminUsername.toLowerCase().trim()).isPresent()) {
            return;
        }

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("System");
        admin.setUsername(adminUsername.toLowerCase().trim());
        admin.setTelephone("0000000000");
        admin.setEmail(adminEmail.toLowerCase().trim());
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        System.out.println("✅ Admin created: username=" + adminUsername + " password=" + adminPassword);
    }
}
