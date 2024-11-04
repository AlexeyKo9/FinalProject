package ru.skillfactory.finalProject.service;

import jakarta.persistence.EntityNotFoundException;
import ru.skillfactory.finalProject.entity.User;
import ru.skillfactory.finalProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getBalance(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public String takeMoney(long id, BigDecimal money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (user.getBalance().compareTo(money) >= 0) {
            user.setBalance(user.getBalance().subtract(money));
        } else {
            throw new IllegalArgumentException("Недостаточно средств на счету");
        }
        userRepository.save(user);
        return "Успех (1)";
    }

    public String putMoney(long id, BigDecimal money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        user.setBalance(user.getBalance().add(money));
        userRepository.save(user);
        return "Успех (1)";
    }
}