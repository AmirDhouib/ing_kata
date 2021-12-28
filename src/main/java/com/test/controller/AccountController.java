package com.test.controller;

import com.test.dto.TransactionDTO;
import com.test.model.Transaction;
import com.test.service.AccountService;
import com.test.service.serviceImp.AccountServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController {


    private final AccountService accountService;

    /**
     * constructor
     * @param AccountService
     */
    public AccountController(AccountService AccountService) {
        this.accountService = AccountService;
    }


    /**
     * get all transactions by account id
     * @param id: account id
     * @return: list of transaction
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam Long id) {
        List<Transaction> result = accountService.getTransactions(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * get balance by account id
     * @param id: account id
     * @return: the value balance
     */
    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam Long id) {
        BigDecimal result = accountService.getBalance(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * handle account(deposit/withdraw)
     * @param transactionDTO: transaction object
     * @return: crated transaction
     */
    @PostMapping("/handle/account")
    public ResponseEntity<Transaction> handleAccount(@RequestBody TransactionDTO transactionDTO) {
        Transaction result = accountService.handleAccount(transactionDTO.getAccount(), transactionDTO.getTransaction());
        if (Objects.isNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
    }
}
