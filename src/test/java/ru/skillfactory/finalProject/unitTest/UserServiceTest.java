package ru.skillfactory.finalProject.unitTest;

import ru.skillfactory.finalProject.entity.Operation;
import ru.skillfactory.finalProject.entity.User;
import ru.skillfactory.finalProject.repository.OperationRepository;
import ru.skillfactory.finalProject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skillfactory.finalProject.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    OperationRepository operationRepository;


    @Test
    public void testGetBalance() {
        // Arrange
        long userId = 1L;
        BigDecimal expectedBalance = new BigDecimal(1000);
        User user = new User();
        user.setId(userId);
        user.setBalance(expectedBalance);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        // Act
        BigDecimal actualBalance = userService.getBalance(userId);
        // Assert
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testTakeMoney() {
        long userId = 1L;
        BigDecimal expectedBalance = new BigDecimal(1000);
        BigDecimal money = BigDecimal.valueOf(500);

        User user = new User();
        user.setId(userId);
        user.setBalance(expectedBalance);

        Operation operation = Operation.builder()
                .id_operation(1)
                .user(user)
                .type_operation(1)
                .amount(money)
                .timeOperation(LocalDateTime.now())
                .build();

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(operation);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User result = userService.takeMoney(userId, money);

        Assertions.assertEquals(user, result);
        Assertions.assertEquals(BigDecimal.valueOf(500), result.getBalance());
        Mockito.verify(operationRepository, Mockito.times(1)).save(Mockito.any(Operation.class));
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testPutMoney() {
        long userId = 1L;
        BigDecimal money = BigDecimal.valueOf(500);

        User user = new User();
        user.setId(userId);
        user.setBalance(money);

        Operation operation = Operation.builder()
                .id_operation(1)
                .user(user)
                .type_operation(1)
                .amount(money)
                .timeOperation(LocalDateTime.now())
                .build();

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(operation);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        String result = userService.putMoney(userId, money);

        Assertions.assertEquals("Успех (1)", result);
        Assertions.assertEquals(BigDecimal.valueOf(1000), user.getBalance());
        Mockito.verify(operationRepository, Mockito.times(1)).save(Mockito.any(Operation.class));
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testGetOperationList() {
        long userId = 1L;
        LocalDateTime beginDate = null;
        LocalDateTime endDate = null;
        BigDecimal money = BigDecimal.valueOf(1000);

        User user = new User();
        user.setId(userId);
        user.setBalance(money);

        Operation operation1 = Operation.builder()
                .id_operation(1)
                .user(user)
                .type_operation(1)
                .amount(BigDecimal.valueOf(500))
                .timeOperation(LocalDateTime.now())
                .build();

        Operation operation2 = Operation.builder()
                .id_operation(2)
                .user(user)
                .type_operation(2)
                .amount(BigDecimal.valueOf(1000))
                .timeOperation(LocalDateTime.now())
                .build();

        List<Operation> listOperations = new ArrayList<>();
        listOperations.add(operation1);
        listOperations.add(operation2);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(operationRepository.findOperationsByUserId(user.getId())).thenReturn(listOperations);

        List<String> expected = new ArrayList<>();
        expected.add("Cумма: 500");
        expected.add("Тип операции: Снятие средств со счета");
        expected.add("Время операции: " + operation1.getTimeOperation().toString());
        expected.add("Cумма: 1000");
        expected.add("Тип операции: Пополнение средств на счет");
        expected.add("Время операции: " + operation2.getTimeOperation().toString());

        List<String> result = userService.getOperationList(userId, beginDate, endDate);

        Assertions.assertEquals(expected, result);
        Mockito.verify(operationRepository, Mockito.times(1)).findOperationsByUserId(user.getId());
    }

    @Test
    public void testTransferMoneySufficientFunds() {
        long senderId = 1L;
        long recipientId = 2L;
        BigDecimal senderBalance = new BigDecimal(1000);
        BigDecimal recipientBalance = new BigDecimal(500);
        BigDecimal money = BigDecimal.valueOf(500);

        User sender = new User();
        sender.setId(senderId);
        sender.setBalance(senderBalance);

        User recipient = new User();
        recipient.setId(recipientId);
        recipient.setBalance(recipientBalance);

        Mockito.when(userRepository.findById(senderId)).thenReturn(Optional.of(sender));
        Mockito.when(userRepository.findById(recipientId)).thenReturn(Optional.of(recipient));

        String result = userService.transferMoney(senderId, recipientId, money);

        Assertions.assertEquals("Успех (1)", result);
        Assertions.assertEquals(BigDecimal.valueOf(500), sender.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(1000), recipient.getBalance());
        Mockito.verify(userRepository, Mockito.times(1)).save(sender);
        Mockito.verify(userRepository, Mockito.times(1)).save(recipient);
    }
}