package com.grp3.bid.repositories;

import com.grp3.bid.entities.User;

import java.util.List;

public interface UserDAOInterface {
    User getUserById(Integer id);
    User getUserBydEmail(String email);
    List<User> getAll();
    int InsertUser(User user);
    boolean updateUser(Integer id, User user);

    boolean updateAccountWallet(Integer id, User user);

    User findByPseudo(String pseudo);
    boolean deleteUser(User user);
    User getUserByPseudo(String pseudo);
}
