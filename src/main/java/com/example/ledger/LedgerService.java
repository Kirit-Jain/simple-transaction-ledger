package com.example.ledger;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LedgerService {
    @Autowired
    private TransactionRepository transactionRepository;


    public Transaction createTransaction(String sender, String receiver, Double amount)
    {
        Transaction newTransaction = new Transaction(sender, receiver, amount);
        newTransaction.setTimestamp(Instant.now());

        return transactionRepository.save(newTransaction);
    }

    public List<Transaction> getTransactionsForAccount(String accountId)
    {
        List<Transaction> sent = transactionRepository.findBySenderAccountId(accountId);
        List<Transaction> received = transactionRepository.findByReceiverAccountId(accountId);

        return Stream.concat(sent.stream(), received.stream()).toList();
    }

    public Double getBalance(String accountId)
    {
        double totalSent = transactionRepository.findBySenderAccountId(accountId)
            .stream()
            .mapToDouble(Transaction::getAmount)
            .sum();

        double totalReceived = transactionRepository.findByReceiverAccountId(accountId)
            .stream()
            .mapToDouble(Transaction::getAmount)
            .sum();

        return totalReceived - totalSent;
    }
}
