package com.example.crud.User;

import com.example.crud.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByEmail(String email);
   boolean existsUserByEmail(String email);
}
