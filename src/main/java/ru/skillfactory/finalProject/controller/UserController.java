package ru.skillfactory.finalProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skillfactory.finalProject.entity.User;
import ru.skillfactory.finalProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> helloGreeting() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Hello World! Это ответ на тестовый запрос веб-сервиса.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBalance/{id}")
    ResponseEntity getBalanceById(@PathVariable Long id) {
        User user = userService.getBalance(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/takeMoney/{id}")
    ResponseEntity getTakeMoneyByBalance(@PathVariable Long id, @RequestParam BigDecimal money) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.takeMoney(id, money));
    }

    @GetMapping("/putMoney/{id}")
    ResponseEntity putMoneyByBalance(@PathVariable Long id, @RequestParam BigDecimal money) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.putMoney(id, money));
    }
}