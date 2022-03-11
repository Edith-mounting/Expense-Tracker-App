package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetracker.pojo.Transaction;
import com.pairlearning.expensetracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Transaction> fetchAllTransactions(int userId, int categoryId) {
        List<Transaction> allTransaction = transactionRepository.fetchAllTransactionById(userId, categoryId);
        return allTransaction;
    }

    @Override
    public Transaction fetchTransactionById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException {
        try{
            return transactionRepository.findByTransactionId(userId,categoryId,transactionId);
        } catch (Exception e){
            throw new EtResourceNotFoundException("Transaction Not Found!");
        }
    }

    @Override
    public Transaction addTransaction(int userId, int categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException {
        try{
            Transaction transaction = new Transaction();
            transaction.setUserId(userId);
            transaction.setAmount(amount);
            transaction.setCategoryId(categoryId);
            transaction.setNote(note);
            transaction.setTransactionDate(transactionDate);
            return transactionRepository.save(transaction);
        } catch (Exception e){
            throw new EtBadRequestException("Invalid Request!");
        }
    }

    @Override
    public void updateTransaction(int userId, int categoryId, int transactionId, Transaction transaction) throws EtBadRequestException {
        try {
            transaction.setCategoryId(categoryId);
            transaction.setUserId(userId);
            transaction.setTransactionId(transactionId);
            transactionRepository.save(transaction);
        } catch (Exception e){
            throw new EtBadRequestException("Transaction Not Found!");
        }

    }

    @Override
    public void removeTransaction(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException {
        try {
            Transaction transaction =  transactionRepository.deleteTransactionById(userId, categoryId, transactionId);
        } catch (Exception e){
            throw new EtResourceNotFoundException("No Transaction Found!");
        }
    }
}
