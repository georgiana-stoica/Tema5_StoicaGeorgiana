package com.pay.amigo.services;

import com.pay.amigo.entities.Transactions;
import com.pay.amigo.entities.Wallet;
import com.pay.amigo.repository.TransactionsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    public List<Transactions> getAllTransactions(){
        return transactionsRepository.findAll();
    }
    public Transactions createTransaction(Transactions transaction) {
        transaction.setCreated_at(new Date());
        return transactionsRepository.save(transaction);
    }

    public Transactions getTransactionById(Integer id) {
        return transactionsRepository.findById(id).orElse(null);
    }

    public List<Transactions> getTransactionsBySourceWalletId(Integer id) {
        return transactionsRepository.findBySourceId(id);
    }

    public List<Transactions> getTransactionsByDestinationWalletId(Integer id) {
        return transactionsRepository.findByDestinationId(id);
    }

    public boolean EnoughBalance(Wallet sourceWallet, Float amount) {
        if ( amount - sourceWallet.getBalance()  > 0) {
            return false;
        }

        return true;
    }

    public boolean isPaymentDateValid(Date paymentDate) {
        Date currentDate = new Date();
        if (paymentDate.before(currentDate)) {
            return false;
        } else {
            return true;
        }
    }

}
