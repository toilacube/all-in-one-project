package com.example.crud.Address;

import com.example.crud.Entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer>{
    //@Query("SELECT ua FROM UserAddress ua WHERE ua.user.id = :id")
    List<UserAddress> findAllByUserId(int id);
}
