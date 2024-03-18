package com.grp3.bid.services;

import com.grp3.bid.entities.User;

public interface AccountServiceInterface {
    public void addToAccount(User user, Float ammount);
    public void decrementAccount(User user, Float ammount);
    public boolean isSolvent(User user, Float ammount);


}
