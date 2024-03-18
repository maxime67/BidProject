package com.grp3.bid.services;

import com.grp3.bid.entities.User;
import com.grp3.bid.repositories.UserDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements AccountServiceInterface{
    @Autowired
    UserDAOInterface userDAO;

    @Override
    public void addToAccount(User user, Float ammount) {
        user.setAccountWallet(userDAO.getUserById(user.getId()).getAccountWallet() + ammount);
        userDAO.updateAccountWallet(user);
        System.out.println(userDAO.getUserById(user.getId()));

    }

    @Override
    public void decrementAccount(User user, Float ammount) {
        user.setAccountWallet(userDAO.getUserById(user.getId()).getAccountWallet() - ammount);
        userDAO.updateAccountWallet(user);
    }

    @Override
    public boolean isSolvent(User user, Float ammount) {
        if(user.getAccountWallet() >= ammount){
            return true;
        }
        return false;
    }
}
