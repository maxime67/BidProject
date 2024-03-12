package com.grp3.bid.services;

import com.grp3.bid.repositories.UserDAOInterface;
import com.grp3.bid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    UserDAOInterface userDAO;
    @Autowired
    PasswordEncoder encodeur;
    @Override
    public User getUserByid(Integer id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByemail(String email) {
        return userDAO.getUserBydEmail(email);
    }

    @Override
    public User getUserByPseudo(String pseudo) {
        return userDAO.findByPseudo(pseudo);
    }

    @Override
    public int insertUser(User user) {
        user.setPassword(encodeur.encode(user.getPassword()));
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

    @Override
    public boolean deleteUser(User user) {
        return userDAO.deleteUser(user);
    }
}
