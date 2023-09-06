package com.pay.amigo.services;

import com.pay.amigo.entities.Users;
import com.pay.amigo.repository.UsersRepository;
import com.pay.amigo.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UsersService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    WalletRepository walletRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUserById( Integer id ){
        Users user =  usersRepository.findUserById(id);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }
        return user;
    }

    public Users addUser(Users users){
        return usersRepository.save(users);

    }

    public boolean UserPaysItself(Users users, Float amount){
        if( walletRepository.findBalanceByUserId(users.getId()) - amount < 0 )
            return false;
        else
            return true;
    }

    public boolean UserPaysPositiveAmount(Users users, Float amount){
        if (amount < 0) {
            return false;
        }

        return true;
    }


}
