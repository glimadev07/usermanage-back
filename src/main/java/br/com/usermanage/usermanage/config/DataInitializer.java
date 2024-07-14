package br.com.usermanage.usermanage.config;

import br.com.usermanage.usermanage.entity.User;
import br.com.usermanage.usermanage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (userRepository.count() == 0) {
                User user1 = new User(null, "User 1", "user1", "user1@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 1", false);
                User user2 = new User(null, "User 2", "user2", "user2@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 2", false);
                User user3 = new User(null, "User 3", "user3", "user3@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 3", false);
                User user4 = new User(null, "User 4", "user4", "user4@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 4", false);
                User user5 = new User(null, "User 5", "user5", "user5@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 5", false);
                User user6 = new User(null, "User 6", "user6", "user6@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 6", false);
                User user7 = new User(null, "User 7", "user7", "user7@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 7", false);
                User user8 = new User(null, "User 8", "user8", "user8@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 8", false);
                User user9 = new User(null, "User Master", "userMaster", "userMaster9@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 9", true);
                User user10 = new User(null, "User 10", "user10", "user10@example.com", passwordEncoder.encode("password"), LocalDateTime.now(), true, "1234567890", "Address 10", false);

                userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
            }
        };
    }
}
