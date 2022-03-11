package com.pairlearning.expensetracker.request;

public class TransactionRequest {
    private Double amount;
    private String note;

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
}
