package com.pairlearning.expensetracker.pojo;

import javax.persistence.*;

@Entity(name = "Et_Transaction")
@Table(name = "Et_Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_Id")
    private int transactionId;
    @Column(name = "userId")
    private int userId;
    @Column(name = "categoryId")
    private int categoryId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "note")
    private String note;
    @Column(name = "transactionDate")
    private Long transactionDate;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }
}
