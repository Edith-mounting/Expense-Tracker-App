package com.pairlearning.expensetracker.controller;

import com.pairlearning.expensetracker.pojo.Transaction;
import com.pairlearning.expensetracker.request.TransactionRequest;
import com.pairlearning.expensetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request,
                                                                @PathVariable("categoryId") int categoryId){
        int userId = (Integer)request.getAttribute("userId");
        List<Transaction> allTransactions = transactionService.fetchAllTransactions(userId,categoryId);
        return new ResponseEntity<>(allTransactions,HttpStatus.OK);
    }
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("categoryId") int categoryId,
                                                          @PathVariable("transactionId")int transactionId){
        int userId = (Integer)request.getAttribute("userId");
        Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                                      @PathVariable("categoryId") int categoryId,
                                                      @RequestBody TransactionRequest transactionRequest){
        int userId = (Integer)request.getAttribute("userId");
        Long date =(Long) new Date().getTime();
        Transaction transaction= transactionService.addTransaction(userId,categoryId,transactionRequest.getAmount(),transactionRequest.getNote(),date);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String , Boolean>> updateTransaction(HttpServletRequest request,
                                                                   @PathVariable("categoryId")int categoryId,
                                                                   @PathVariable("transactionId")int transactionId,
                                                                   @RequestBody Transaction transaction){
        int userId =(Integer) request.getAttribute("userId");
        Long date = (Long) new Date().getTime();
        transaction.setTransactionDate(date);
        transactionService.updateTransaction(userId,categoryId,transactionId,transaction);
        Map<String, Boolean> mp = new HashMap<>();
        mp.put("Success", true);
        return new ResponseEntity<>(mp, HttpStatus.OK);
    }
    @DeleteMapping("/{transactionId}")
    public Map<String, Integer> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId")int categoryId,
                                                                  @PathVariable("transactionId")int transactionId){
        int userId = (Integer)request.getAttribute("userId");
        transactionService.removeTransaction(userId,categoryId,transactionId);
        Map<String, Integer> mp = new HashMap<String, Integer>();
        mp.put("Success",1);
        mp.put("userId", userId);
        mp.put("categoryId", categoryId);
        mp.put("transactionId", transactionId);
        return mp;
    }
}
