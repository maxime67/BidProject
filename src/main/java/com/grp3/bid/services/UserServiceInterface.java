package com.grp3.bid.services;

import com.grp3.bid.entities.*;

import java.util.List;

public interface UserServiceInterface {
    User getUserByid(Integer id);
    User getUserByemail(String email);
    User getUserByPseudo(String pseudo);
    int insertUser(User user);
    List<User> getAll();
    boolean updateUser(Integer id, User user);

    boolean updateAccountWallet(User user);

    boolean deleteUser(User user);

}
