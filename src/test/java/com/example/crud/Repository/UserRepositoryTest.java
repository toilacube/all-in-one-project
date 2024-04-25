package com.example.crud.Repository;

import com.example.crud.Entity.User;
import com.example.crud.User.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void UserRepo_SaveNewUser(){
        User user = User.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepo_FindUserByEmail() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("password")
                .build();
        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

        User foundUser = userRepository.findByEmail("test@gmail.com").orElse(null);

        Assertions.assertThat(foundUser).isNotNull();
    }
}
