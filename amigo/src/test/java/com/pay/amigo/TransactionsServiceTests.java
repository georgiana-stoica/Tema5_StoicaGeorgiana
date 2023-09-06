package com.pay.amigo;

import com.pay.amigo.entities.Transactions;
import com.pay.amigo.entities.Wallet;
import com.pay.amigo.services.TransactionsService;
import com.pay.amigo.services.WalletService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsServiceTests {
    @Autowired
    TransactionsService transactionsService;
    @Autowired
    WalletService walletService;


    @Test
    public void testIsPaymentDateValidFuture() {
        Date d = new Date(2025, 11, 21);

        boolean isValid = transactionsService.isPaymentDateValid(d);
        assertTrue(isValid);
    }

    @Test
    public void testIsPaymentDateInvalid() {
        Date d = new Date(2020, 01, 01);

        boolean isValid = transactionsService.isPaymentDateValid(d);
        assertFalse(!isValid);
    }

    @Test
    public void testEnoughBalance() {
        Wallet sourceWallet = new Wallet();
        sourceWallet.setName("Ion Dinu");
        sourceWallet.setUser_id(1);
        sourceWallet.setBalance(100.0);
        sourceWallet.setCurrency(Currency.getInstance("USD"));

        boolean enoughBalance = transactionsService.EnoughBalance(sourceWallet, 50.0f);
        assertTrue(enoughBalance);
    }

    @Test
    public void testNotEnoughBalance() {
        Wallet sourceWallet = new Wallet();
        sourceWallet.setName("Dana Ion");
        sourceWallet.setUser_id(2);
        sourceWallet.setBalance(50.0);
        sourceWallet.setCurrency(Currency.getInstance("EUR"));

        boolean enoughBalance = transactionsService.EnoughBalance(sourceWallet, 700.0f);
        assertFalse(enoughBalance);
    }

    @Test
    public void testCreateTransactionIsNotNull() {
        Transactions transaction = new Transactions();
        transaction.setSource_id(1);
        transaction.setDestination_id(2);
        transaction.setAmount(500.0f);
        transaction.setCommission_percent(0.5f);
        transaction.setGetCommission_amount(3.5f);
        transaction.setCurrency(Currency.getInstance("USD"));
        transaction.setCreated_at(new Date());

        assertNotNull(transactionsService.createTransaction(transaction));
    }


    @Test
    public void testGetTransactionsBySourceWalletId() {
        assertNotNull(transactionsService.getTransactionsBySourceWalletId(1));
    }

    @Test
    public void testGetTransactionsByDestinationWalletId() {
        assertNotNull(transactionsService.getTransactionsByDestinationWalletId(2));
    }

    @Test
    public void testGetAllTransactions() {
        assertNotNull(transactionsService.getAllTransactions());
    }


    @Test
    public void testCreateTransactionIsNull() {
        Transactions transaction = new Transactions();
        assertNotNull(transactionsService.createTransaction(transaction));
    }

    @Test
    public void testGetTransactionById() {
        Transactions transaction = new Transactions();
        transaction.setSource_id(1);
        transaction.setDestination_id(2);
        transaction.setAmount(500.0f);
        transaction.setCommission_percent(0.5f);
        transaction.setGetCommission_amount(3.5f);
        transaction.setCurrency(Currency.getInstance("USD"));
        transaction.setCreated_at(new Date());

        Transactions savedTransaction = transactionsService.createTransaction(transaction);

        assertEquals(savedTransaction, transactionsService.getTransactionById(savedTransaction.getId()));
    }

}
