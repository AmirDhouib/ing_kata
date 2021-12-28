package com.test.dto;

import com.test.model.Account;
import com.test.model.Transaction;

public class TransactionDTO {

    private Account account;

    private Transaction transaction;

    /**
     * constructor
     * @param account: used account
     * @param transaction: transaction associated to the appropriate account
     */
    public TransactionDTO(Account account, Transaction transaction) {
        this.account = account;
        this.transaction = transaction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
