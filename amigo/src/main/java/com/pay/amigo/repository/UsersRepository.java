package com.pay.amigo.repository;

import com.pay.amigo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findUserById(Integer id);
}
