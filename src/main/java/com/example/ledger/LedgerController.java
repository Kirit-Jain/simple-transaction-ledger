package com.example.ledger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LedgerController {
    @Autowired
    private LedgerService ledgerService;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction)
    {
        Transaction createdTransaction = ledgerService.createTransaction(
            transaction.getSenderAccountId(), 
            transaction.getReceiverAccountId(), 
            transaction.getAmount()
        );

        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String accountId)
    {
        List<Transaction> transactions = ledgerService.getTransactionsForAccount(accountId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/accounts/{accountId}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable String accountId)
    {
        Double balance = ledgerService.getBalance(accountId);
        return ResponseEntity.ok(balance);
    }
}
