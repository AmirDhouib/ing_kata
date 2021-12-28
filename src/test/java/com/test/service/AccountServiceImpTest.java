package com.test.service;

import com.test.config.ApplicationConfig;
import com.test.dao.AccountRepository;
import com.test.dao.TransactionRepository;
import com.test.model.Account;
import com.test.model.Transaction;
import com.test.model.utilities.AccountType;
import com.test.model.utilities.TransactionType;
import com.test.service.serviceImp.AccountServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceImpTest {

    @InjectMocks
    private AccountServiceImp accountServiceImp;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ApplicationConfig config;

    private Transaction transaction;

    private Account account;

    @BeforeEach
    public void setup() {
        transaction = new Transaction(LocalDate.of(2021, 12, 28),null, new BigDecimal(100));
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        account = new Account(AccountType.PERSONAL, transactions, new BigDecimal(200));
        account.setId(1L);
    }

    @Test
    void handleAccountDepositTest() {
        transaction.setType(TransactionType.DEPOSIT);
        when(accountRepository.save(account)).thenReturn(account);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(config.getMinDepositValue()).thenReturn(new BigDecimal(0.01));

        Transaction result = accountServiceImp.handleAccount(account, transaction);

        assertEquals(new BigDecimal(300), account.getBalance());
        assertNotNull(result);

    }

    @Test
    void handleAccountWithdrawTest() {
        transaction.setType(TransactionType.WITHDRAW);
        when(accountRepository.save(account)).thenReturn(account);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(config.getMinDepositValue()).thenReturn(new BigDecimal(0.01));

        Transaction result = accountServiceImp.handleAccount(account, transaction);

        assertEquals(new BigDecimal(100), account.getBalance());
        assertNotNull(result);

    }

    @Test
    void getBalance() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        BigDecimal result = accountServiceImp.getBalance(account.getId());
        assertEquals(account.getBalance(), result);
    }

    @Test
    void getTransactions() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        List<Transaction> result = accountServiceImp.getTransactions(account.getId());
        assertFalse(result.isEmpty());
    }
}