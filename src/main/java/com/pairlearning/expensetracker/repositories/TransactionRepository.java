package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.pojo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("select tr from Et_Transaction tr where tr.transactionId =?3 and tr.userId =?1 and tr.categoryId =?2")
    Transaction findByTransactionId(int userId,int categoryId,int transactionId);

    @Query("select tr from Et_Transaction tr where tr.userId =?1 and tr.categoryId =?2")
    List<Transaction> fetchAllTransactionById(int userId, int categoryId);

    @Query("delete from Et_Transaction tr where tr.userId =?1 and tr.categoryId =?2 and tr.transactionId =?3")
    Transaction deleteTransactionById(int userId, int categoryId, int transactionId);
}
