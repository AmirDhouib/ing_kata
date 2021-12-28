package com.test.service.serviceImp;

import com.test.config.ApplicationConfig;
import com.test.dao.AccountRepository;
import com.test.dao.TransactionRepository;
import com.test.model.Account;
import com.test.model.Transaction;
import com.test.model.utilities.TransactionType;
import com.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final ApplicationConfig config;

    /**
     * constructor
     * @param transactionRepository: transaction repository
     * @param accountRepository: account repository
     * @param config: application properties config
     */
    @Autowired
    public AccountServiceImp(TransactionRepository transactionRepository, AccountRepository accountRepository, ApplicationConfig config) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.config = config;
    }

    /**
     * handle account service (deposit/ transaction)
     * @param account: account of associated transaction
     * @param transaction: transaction in associated account
     * @return created transaction
     */
    @Transactional
    public Transaction handleAccount(final Account account, final Transaction transaction) {
        Objects.requireNonNull(account);
        Objects.requireNonNull(transaction);
        Transaction result = null;
        TransactionType type = transaction.getType();
        if (type.equals(TransactionType.DEPOSIT)) {
            result = deposit(account, transaction);
        } else if (type.equals(TransactionType.WITHDRAW)) {
            result = withdraw(account, transaction);
        }
        return result;
    }

    /**
     * deposit action
     * @param account: account of associated transaction
     * @param transaction: transaction in associated account
     * @return created transaction
     */
    private Transaction deposit(final Account account, final Transaction transaction) {

        Objects.requireNonNull(account);
        Objects.requireNonNull(transaction);
        Transaction savedTransaction = null;
        try {
            if (transaction.getAmount().compareTo(config.getMinDepositValue()) > 0) {
                savedTransaction = transactionRepository.save(transaction);
                Objects.requireNonNull(savedTransaction);
                account.setBalance(account.getBalance().add(transaction.getAmount()));
                accountRepository.save(account);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Transaction not valid");
        }
        return savedTransaction;
    }

    /**
     * withdraw action
     * @param account: account of associated transaction
     * @param transaction: transaction in associated account
     * @return created transaction
     */
    private Transaction withdraw(final Account account, final Transaction transaction) {

        Objects.requireNonNull(account);
        Objects.requireNonNull(transaction);
        Transaction savedTransaction = null;
        try {
            if (account.getBalance().compareTo(transaction.getAmount()) > 0) {
                savedTransaction = transactionRepository.save(transaction);
                Objects.requireNonNull(savedTransaction);
                account.setBalance(account.getBalance().min(transaction.getAmount()));
                accountRepository.save(account);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Transaction not valid");
        }
        return savedTransaction;
    }

    /**
     * get balance for associated account id
     * @param id: account identifier
     * @return: balance value
     */
    @Transactional
    public BigDecimal getBalance(final Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get().getBalance();
        } else {
            return new BigDecimal(0);
        }
    }

    /**
     * get all transactions of associated account id
     * @param id: account identifier
     * @return: list of transaction
     */
    @Transactional
    public List<Transaction> getTransactions(final Long id) {

        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get().getTransactions();
        } else {
            return new ArrayList<>();
        }
    }
}
