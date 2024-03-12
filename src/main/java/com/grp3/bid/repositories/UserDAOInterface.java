package com.grp3.bid.repositories;

import com.grp3.bid.entities.User;

import java.util.List;

public interface UserDAOInterface {
    User getUserById(Integer id);
    User getUserBydEmail(String email);
    List<User> getAll();
    boolean InsertUser(User user);
    boolean updateUser(Integer id, User user);

    User findByPseudo(String pseudo);
}
