package com.grp3.bid.Utils;

import com.grp3.bid.entities.User;
import com.grp3.bid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements AuthenticationFacadeInterface {

    @Autowired
    private UserService userService;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getConnectedUser() {
        if (!(getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return userService.getUserByPseudo(getAuthentication().getName());
        }
        return null;
    }

    public boolean isAuthenticated() {
        return !(getAuthentication() instanceof AnonymousAuthenticationToken);
    }

}