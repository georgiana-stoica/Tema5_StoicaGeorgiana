package com.pay.amigo.services;

import com.pay.amigo.entities.Wallet;
import com.pay.amigo.repository.WalletRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Currency;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
}
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public boolean walletExists(int walletId) {
        if( walletRepository.findById(walletId) != null)
            return true;
        else
            return false;
    }

    public boolean isWalletEmpty(Integer id) {
        if( walletRepository.existsById(id))
            return true;
        else
            return false;
    }

    public Wallet changeCurrency(Integer id, Currency newCurrency) {
        Wallet wallet =  walletRepository.findById(id).orElse(null);
        if (wallet != null) {
            wallet.setCurrency(newCurrency);
            wallet = walletRepository.save(wallet);
        }

        return wallet;
    }
}
