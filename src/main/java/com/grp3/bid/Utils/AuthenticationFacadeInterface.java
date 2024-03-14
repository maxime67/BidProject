package com.grp3.bid.Utils;

import com.grp3.bid.entities.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacadeInterface {
    Authentication getAuthentication();
    User getConnectedUser();
    boolean isAuthenticated();
}
