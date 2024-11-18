package ru.skillfactory.finalProject.integrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skillfactory.finalProject.entity.User;
import ru.skillfactory.finalProject.exceptions.ErrorResponse;
import ru.skillfactory.finalProject.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setBalance(new BigDecimal("500"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setBalance(new BigDecimal("0"));
        userRepository.save(user2);
    }

    @Test
    public void testGetBalanceSuccess() {
        long userId = 1L;

        String url = String.format("/getBalance/%d", userId);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("500.00");
    }

    @Test
    public void testTakeMoneySuccess() {
        long userId = 1L;
        BigDecimal money = new BigDecimal("300");

        String url = String.format("/takeMoney/%d?money=%s", userId, money);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("{\"id\":1,\"balance\":200.00}");

        Optional<User> user = userRepository.findById(userId);
        assertThat(user).isPresent();
        assertThat(user.get().getBalance()).isEqualByComparingTo(new BigDecimal("200"));
    }

    @Test
    public void testPutMoneySuccess() {
        long userId = 1L;
        BigDecimal money = new BigDecimal("300");

        String url = String.format("/putMoney/%d?money=%s", userId, money);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Успех (1)");

        Optional<User> user = userRepository.findById(userId);
        assertThat(user).isPresent();
        assertThat(user.get().getBalance()).isEqualByComparingTo(new BigDecimal("500"));
    }

    @Test
    public void testTransferMoneySuccess() {
        long senderId = 1L;
        long recipientId = 2L;
        BigDecimal money = new BigDecimal("100");

        String url = String.format("/transferMoney/%d/%d?money=%s", senderId, recipientId, money);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Успех (1)");

        Optional<User> user1 = userRepository.findById(senderId);
        assertThat(user1).isPresent();
        assertThat(user1.get().getBalance()).isEqualByComparingTo(new BigDecimal("400"));
        Optional<User> user2 = userRepository.findById(recipientId);
        assertThat(user2).isPresent();
        assertThat(user2.get().getBalance()).isEqualByComparingTo(new BigDecimal("100"));
    }

    @Test
    public void testTransferMoneyInsufficientFunds() throws JsonProcessingException {
        long senderId = 1L;
        long recipientId = 2L;
        BigDecimal money = new BigDecimal("10000");

        String url = String.format("/transferMoney/%d/%d?money=%s", senderId, recipientId, money);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ErrorResponse errorResponse = objectMapper.readValue(response.getBody(), ErrorResponse.class);
        assertThat(errorResponse.getCode()).isEqualTo("Ошибка при выполнении операции (0)");
        assertThat(errorResponse.getMessage()).isEqualTo("Недостаточно средств на счету");
    }
}