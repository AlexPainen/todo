package com.example.todo.model.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {
    HttpStatus httpStatus;
    String message;
}
