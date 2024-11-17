package ru.skillfactory.finalProject.service;

import ru.skillfactory.finalProject.entity.Operation;
import ru.skillfactory.finalProject.entity.User;
import ru.skillfactory.finalProject.repository.OperationRepository;
import ru.skillfactory.finalProject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;

    public UserService(UserRepository userRepository, OperationRepository operationRepository) {
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
    }

    @Transactional
    public BigDecimal getBalance(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return user.getBalance();
    }

    @Transactional
    public User takeMoney(long id, BigDecimal money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (user.getBalance().compareTo(money) >= 0) {
            user.setBalance(user.getBalance().subtract(money));

        } else {
            throw new IllegalArgumentException("Недостаточно средств на счету");
        }
        Operation operations = new Operation();
        operations.setUser(user);
        operations.setType_operation(1);
        operations.setAmount(money);
        operations.setTimeOperation(LocalDateTime.now());
        operationRepository.save(operations);
        userRepository.save(user);
        return user;
    }
    @Transactional
    public String putMoney(long id, BigDecimal money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        user.setBalance(user.getBalance().add(money));
        Operation operations = new Operation();
        operations.setUser(user);
        operations.setType_operation(2);
        operations.setAmount(money);
        operations.setTimeOperation(LocalDateTime.now());
        operationRepository.save(operations);
        userRepository.save(user);
        return "Успех (1)";
    }
    @Transactional
    public List<String> getOperationList(long id, LocalDateTime beginDate, LocalDateTime endDate) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        List<String> result = new ArrayList<>();

        if (beginDate == null || endDate == null) {
            List<Operation> list = operationRepository.findOperationsByUserId(user.getId());
            for (Operation e : list) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + String.valueOf(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        } else {
            List<Operation> listRange =
                    operationRepository.findOperationsByUserIdAndDateRange(user.getId(), beginDate, endDate);
            for (Operation e : listRange) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + String.valueOf(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        }
    }
}