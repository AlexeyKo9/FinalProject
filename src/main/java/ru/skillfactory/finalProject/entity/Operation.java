package ru.skillfactory.finalProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_operation;
    @ManyToOne
    private User user;
    @Column
    private int type_operation;
    @Column
    private BigDecimal amount;
    @Column
    private LocalDateTime timeOperation;
}