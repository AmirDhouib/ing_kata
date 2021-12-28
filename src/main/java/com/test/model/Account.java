package com.test.model;

import com.test.model.utilities.AccountType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    Long id;

    @Column(name = "type")
    AccountType type;

    @Column(name = "balance")
    BigDecimal balance;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    List<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Account() {
        // used for persistence issue
    }

    /**
     * constructor
     * @param type: account type
     * @param transactions: list of transactions
     * @param balance: balance value for thr associated account
     */
    public Account(AccountType type, List<Transaction> transactions, BigDecimal balance) {
        this.type = type;
        this.transactions = transactions;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                type == account.type &&
                Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, transactions);
    }
}
