package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetracker.pojo.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> fetchAllTransactions(int userId, int categoryId);

    Transaction fetchTransactionById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;

    Transaction addTransaction(int userId, int categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;

    void updateTransaction(int userId, int categoryId, int transactionId, Transaction transaction) throws EtBadRequestException;

    void removeTransaction(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;
}
