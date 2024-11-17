package ru.skillfactory.finalProject.controller;

import ru.skillfactory.finalProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    ResponseEntity getBalanceById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getBalance(id));
    }
    @Transactional
    @GetMapping("/takeMoney/{id}")
    ResponseEntity getTakeMoneyByBalance(@PathVariable long id, @RequestParam BigDecimal money) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.takeMoney(id, money));
    }

    @Transactional
    @GetMapping("/putMoney/{id}")
    ResponseEntity putMoneyByBalance(@PathVariable long id, @RequestParam BigDecimal money) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.putMoney(id, money));
    }

    @Transactional
    @GetMapping("/listOperation/{id}")
    ResponseEntity getListOperations(
            @PathVariable long id,
            @RequestParam(value = "beginDate", required = false) LocalDateTime beginDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOperationList(id, beginDate, endDate));
    }
}