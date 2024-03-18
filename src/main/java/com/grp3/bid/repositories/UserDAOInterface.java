package com.grp3.bid.repositories;

import com.grp3.bid.entities.User;

import java.util.List;

public interface UserDAOInterface {
    User getUserById(Integer id);
    User getUserBydEmail(String email);
    List<User> getAll();
    int InsertUser(User user);
    boolean updateAccountWallet(User user);
    boolean updateUser(User user);

    User findByPseudo(String pseudo);
    boolean deleteUser(User user);
    User getUserByPseudo(String pseudo);
    boolean updateUserPassword(User user, String password);
}
