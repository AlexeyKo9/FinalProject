package ru.skillfactory.finalProject;

import org.springframework.boot.CommandLineRunner;
import ru.skillfactory.finalProject.entity.User;
import ru.skillfactory.finalProject.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static UserRepository userRepository;

    public Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        User user1 = new User();
        user1.setBalance(BigDecimal.valueOf(1000));
        User user2 = new User();
        user2.setBalance(BigDecimal.valueOf(3000));
        User user3 = new User();
        user3.setBalance(BigDecimal.valueOf(5000));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
