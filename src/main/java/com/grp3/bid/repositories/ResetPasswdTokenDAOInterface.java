package com.grp3.bid.repositories;

import com.grp3.bid.entities.ResetPasswdToken;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.ResetPasswdTokenService;
import com.grp3.bid.services.ResetPasswdTokenServiceInterface;

public interface ResetPasswdTokenDAOInterface {
    int insertResetPasswdToken(ResetPasswdToken resetPasswdToken);

    ResetPasswdToken getResetPasswdTokenByToken(String token);

    boolean updateAlreadyUsed(ResetPasswdToken resetPasswdToken, boolean value);
}
