package com.test.service;

import com.test.model.Account;
import com.test.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

     /**
      * handle account service (deposit/ transaction)
      * @param account: account of associated transaction
      * @param transaction: transaction in associated account
      * @return created transaction
      */
     Transaction handleAccount(final Account account, final Transaction transaction);

     /**
      * get balance for associated account id
      * @param id: account identifier
      * @return: balance value
      */
     BigDecimal getBalance(final Long id);

     /**
      * get all transactions of associated account id
      * @param id: account identifier
      * @return: list of transaction
      */
     List<Transaction> getTransactions(final Long id);
}
