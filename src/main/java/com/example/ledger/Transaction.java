package com.example.ledger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.Instant;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderAccountId;
    private String receiverAccountId;
    private Double amount;
    private Instant timestamp;

    public Transaction()
    {
        // Default constructor
    }

    public Transaction(String senderAccountId, String reciverAccountId, Double amount)
    {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = reciverAccountId;
        this.amount = amount;

        this.timestamp = Instant.now();
    }
}
