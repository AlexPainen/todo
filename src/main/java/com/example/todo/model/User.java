package com.example.todo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID user_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;
}