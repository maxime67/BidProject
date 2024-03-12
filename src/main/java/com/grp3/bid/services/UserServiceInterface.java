package com.grp3.bid.services;

import com.grp3.bid.entities.*;

import java.util.List;

public interface UserServiceInterface {
    User getUserByid(Integer id);
    User getUserByemail(String email);

    boolean insertUser(User user);
    List<User> getAll();
    boolean updateUser(Integer id, User user);
    boolean deleteUser(User user);
    User getUserByPseudo(String pseudo);

}
