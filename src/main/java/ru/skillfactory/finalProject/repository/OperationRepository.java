package ru.skillfactory.finalProject.repository;

import ru.skillfactory.finalProject.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT b FROM Operation b WHERE b.user = :id AND b.timeOperation between :beginDate AND :endDate")
    List<Operation> findOperationsByUserIdAndDateRange(@Param("id") long id,
                                                       @Param("beginDate") LocalDateTime beginDate,
                                                       @Param("endDate") LocalDateTime endDate);
    List<Operation> findOperationsByUserId(@Param("id") long id);
}