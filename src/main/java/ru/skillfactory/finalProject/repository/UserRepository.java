package ru.skillfactory.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillfactory.finalProject.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}