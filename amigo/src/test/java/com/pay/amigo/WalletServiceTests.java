package com.pay.amigo;

import com.pay.amigo.entities.Wallet;
import com.pay.amigo.services.WalletService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Currency;

import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletServiceTests {
    @Autowired
    WalletService walletService;


    @Test
    public void testExistingWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(1000.0);
        wallet = walletService.createWallet(wallet);
        boolean walletExists = walletService.walletExists(wallet.getId());

        assertTrue(walletExists);
    }

    @Test
    public void testChangeCurrency() {
        Wallet wallet = new Wallet();
        wallet.setCurrency(Currency.getInstance("EUR"));

        Wallet createdWallet = walletService.createWallet(wallet);

        Currency newCurrency = Currency.getInstance("USD");
        Wallet updatedWallet = walletService.changeCurrency(createdWallet.getId(), newCurrency);

        assertEquals(newCurrency, updatedWallet.getCurrency());
    }

    @Test
    public void testEmptyWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(0.0);
        wallet = walletService.createWallet(wallet);

        boolean isWalletEmpty = walletService.isWalletEmpty(wallet.getId());

        assertEquals(true, isWalletEmpty);
    }

    @Test
    public void testNotEmptyWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(1000.0);
        wallet = walletService.createWallet(wallet);

        boolean isWalletEmpty = walletService.isWalletEmpty(wallet.getId());

        assertEquals(true, isWalletEmpty);
    }


    @Test
    public void testGetAllWallets() {
        Currency usd = Currency.getInstance("USD");
        List<Wallet> wallets = new ArrayList<>();
        wallets.add(new Wallet(1, "Dana Irimia", 12345, 1000.00, usd));
        wallets.add(new Wallet(2, "Ilie George", 100, 500.00, usd));
        wallets.add(new Wallet(3, "Dan Ion", 1, 200.00, usd));

        List<Wallet> actualWallets = walletService.getAllWallets();

        assertArrayEquals(wallets.toArray(), actualWallets.toArray());
    }

}
