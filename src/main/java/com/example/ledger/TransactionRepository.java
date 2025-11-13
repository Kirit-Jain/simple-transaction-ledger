package com.example.ledger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderAccountId(String accountId);

    List<Transaction> findByReceiverAccountId(String accountId);
}
