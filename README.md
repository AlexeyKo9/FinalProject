# Веб-сервис банка
## Структура проекта

### controller
`UserController` - REST Controller

### entity
`User` - класс, предназначенный для хранения информации о пользователях в базе данных<br>
`Operation` - класс, предназначенный для хранения информации о совершенных операциях<br>

### exceptions
`ServiceHandler` - класс, обрабатывающий исключения<br>
`ErrorResponse` - класс, предназначенный для передачи информации об ошибках в REST API.<br>

### repository
`UserRepository` - репозиторий для работы с данными пользователя<br>
`OperationRepository` - репозиторий для работы с операциями<br>

### service
`UserService` - сервис для работы с пользователями<br>

### test
`FinalProjectApplicationTests` - класс для тестирования RestController

### application.property
Файл для хранения параметров конфигурации приложения<br>

#### Структура БД
![Screenshot](https://github.com/AlexeyKo9/FinalProject/blob/master/src/main/resources/images/Структура%20БД.png)
![Screenshot](https://github.com/AlexeyKo9/FinalProject/blob/master/src/main/resources/images/Структура%20БД%20operation.png)

## Функции приложения:

1. Проверка баланса пользователя;
2. Снятие средств со счёта;
3. Пополнение счёта;
4. Отобразить список операций за выбранный период;