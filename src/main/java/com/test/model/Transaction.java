package com.test.model;

import com.test.model.utilities.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    Long id;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    TransactionType type;

    @Column(name = "amount")
    BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    Account account;


    public Transaction() {
        // for persistence issue
    }

    /**
     * constructor
     * @param date: transaction date
     * @param type: transaction type
     * @param amount: transaction amount
     */
    public Transaction(LocalDate date, TransactionType type, BigDecimal amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
