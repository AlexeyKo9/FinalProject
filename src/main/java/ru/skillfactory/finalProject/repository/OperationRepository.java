package ru.skillfactory.finalProject.repository;

import ru.skillfactory.finalProject.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillfactory.finalProject.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT o FROM Operation o WHERE o.user = :user AND o.timeOperation between :beginDate AND :endDate")
    List<Operation> findOperationsByUserIdAndDateRange(@Param("user") User user,
                                                       @Param("beginDate") LocalDateTime beginDate,
                                                       @Param("endDate") LocalDateTime endDate);

    @Query("SELECT o FROM Operation o WHERE o.user = :user AND o.timeOperation >= :beginDate")
    List<Operation> findOperationsByUserIdAndBeginDate(@Param("user") User user,
                                                       @Param("beginDate") LocalDateTime beginDate);

    @Query("SELECT o FROM Operation o WHERE o.user = :user AND o.timeOperation <= :endDate")
    List<Operation> findOperationsByUserIdAndEndDate(@Param("user") User user,
                                                     @Param("endDate") LocalDateTime endDate);

    List<Operation> findOperationsByUserId(@Param("id") long id);
}