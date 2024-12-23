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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;

    public UserService(UserRepository userRepository, OperationRepository operationRepository) {
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
    }

    private static final Map<Integer, String> operationTypeMap = new HashMap<>();
    static {
        operationTypeMap.put(1, "Снятие средств со счета");
        operationTypeMap.put(2, "Пополнение средств на счет");
        operationTypeMap.put(3, "Перевод другому пользователю");
        operationTypeMap.put(4, "Зачисление от другого пользователя");
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

        if (beginDate == null && endDate == null) {
            List<Operation> list = operationRepository.findOperationsByUserId(id);
            for (Operation e : list) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + operationTypeMap.get(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        } else if (endDate == null && beginDate != null) {
            List<Operation> listRange = operationRepository.findOperationsByUserIdAndBeginDate(user, beginDate);
            for (Operation e : listRange) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + operationTypeMap.get(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        } else if (endDate != null && beginDate == null) {
            List<Operation> listRange = operationRepository.findOperationsByUserIdAndEndDate(user, endDate);
            for (Operation e : listRange) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + operationTypeMap.get(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        } else {
            List<Operation> listRange = operationRepository.findOperationsByUserIdAndDateRange(user, beginDate, endDate);
            for (Operation e : listRange) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + operationTypeMap.get(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        }
    }

    @Transactional
    public String transferMoney(long sender_id, long recipient_id, BigDecimal money) {
        User sender = userRepository.findById(sender_id).orElseThrow(() -> new EntityNotFoundException());
        User recipient = userRepository.findById(recipient_id).orElseThrow(() -> new EntityNotFoundException());
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Вы ввели сумму меньше 0");
        } else if (sender.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счету");
        } else{
            sender.setBalance(sender.getBalance().subtract(money));
            recipient.setBalance(recipient.getBalance().add(money));

            Operation transfer = new Operation();
            Operation receipt = new Operation();
            transfer.setAmount(money);
            transfer.setTimeOperation(LocalDateTime.now());
            transfer.setType_operation(3);
            transfer.setUser(sender);
            transfer.setUser_transfer(recipient);

            receipt.setAmount(money);
            receipt.setTimeOperation(LocalDateTime.now());
            receipt.setType_operation(4);
            receipt.setUser(recipient);
            receipt.setUser_transfer(sender);

            operationRepository.save(transfer);
            operationRepository.save(receipt);

            userRepository.save(sender);
            userRepository.save(recipient);

            return "Успех (1)";
        }
    }
}