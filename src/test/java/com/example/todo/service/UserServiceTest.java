package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(passwordEncoder.encode("testpassword")).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("encodedpassword", savedUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername("testuser");

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testFindById() {
        User user = new User();
        user.setId(UUID.randomUUID());
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
        verify(userRepository, times(1)).findById(user.getId());
    }
}