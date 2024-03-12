package com.grp3.bid.services;

import com.grp3.bid.repositories.UserDAO;
import com.grp3.bid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    UserDAO userDAO;
    @Override
    public User getUserByid(Integer id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByemail(String email) {
        return userDAO.getUserBydEmail(email);
    }

    @Override
    public boolean insertUser(User user) {
        return userDAO.InsertUser(user);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public boolean updateUser(Integer id, User user) {
        return userDAO.updateUser(id,user);
    }
}
