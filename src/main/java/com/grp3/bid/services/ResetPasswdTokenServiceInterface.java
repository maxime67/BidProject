package com.grp3.bid.services;

import com.grp3.bid.entities.ResetPasswdToken;
import com.grp3.bid.entities.User;

public interface ResetPasswdTokenServiceInterface {

    void createRequestResetPasswdTokenForUser(User user);

    ResetPasswdToken getResetPasswdTokenByToken(String token);

    boolean updateAlreadyUsed(ResetPasswdToken resetPasswdToken, boolean value);
}
