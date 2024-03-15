package com.grp3.bid.services;

import com.grp3.bid.entities.ResetPasswdToken;
import com.grp3.bid.entities.User;
import com.grp3.bid.repositories.ResetPasswdTokenDAO;
import com.grp3.bid.repositories.ResetPasswdTokenDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Random;

@Service
public class ResetPasswdTokenService implements ResetPasswdTokenServiceInterface {

    @Autowired
    ResetPasswdTokenDAOInterface resetPasswdTokenDAO;

    @Override
    public void createRequestResetPasswdTokenForUser(User user) {
        ResetPasswdToken resetPasswdToken = new ResetPasswdToken();
        resetPasswdToken.setUser(user);
        Random rand = new Random();
        resetPasswdToken.setToken(Integer.toString(rand.nextInt(100000000, 999999999)));
        resetPasswdToken.setExpiration(LocalDateTime.now().plusMinutes(10));
        resetPasswdToken.setAlreadyUsed(false);
        resetPasswdTokenDAO.insertResetPasswdToken(resetPasswdToken);
    }

    @Override
    public ResetPasswdToken getResetPasswdTokenByToken(String token) {
        try  {
            return resetPasswdTokenDAO.getResetPasswdTokenByToken(token);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updateAlreadyUsed(ResetPasswdToken resetPasswdToken, boolean value) {
        return resetPasswdTokenDAO.updateAlreadyUsed(resetPasswdToken, value);
    }


}
