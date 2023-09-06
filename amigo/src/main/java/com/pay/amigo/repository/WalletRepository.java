package com.pay.amigo.repository;

import com.pay.amigo.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    @Query(value = "SELECT balance FROM wallet WHERE user_id = ?1", nativeQuery = true)
    Double findBalanceByUserId(Integer user_id);

}