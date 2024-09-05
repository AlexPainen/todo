package com.example.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Task {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private boolean status;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @ManyToOne
    private User user;
}