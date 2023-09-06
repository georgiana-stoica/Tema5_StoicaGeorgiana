package com.pay.amigo.repository;

import com.pay.amigo.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {
    @Query(value = "SELECT * FROM transactions WHERE source_id = ?1", nativeQuery = true)
    List<Transactions> findBySourceId(Integer source_id);

    @Query(value = "SELECT * FROM transactions WHERE destination_id = ?1", nativeQuery = true)
    List<Transactions> findByDestinationId(Integer destination_id);
}
