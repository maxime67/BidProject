package com.grp3.bid.validators;

import com.grp3.bid.Utils.AuthenticationFacade;
import com.grp3.bid.constraints.UniquePseudoConstraint;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;

public class UniquePseudoValidator implements
        ConstraintValidator<UniquePseudoConstraint, String> {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationFacade authenticationFacade;

    @Override
    public void initialize(UniquePseudoConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String pseudoField, ConstraintValidatorContext cxt) {
        // si l'user est connecté et n'a pas modif son pseudo
        if (null != authenticationFacade.getConnectedUser()
                &&
                pseudoField.equals(authenticationFacade.getConnectedUser().getPseudo())
        ) {
            return true;
        }
        try {
            userService.getUserByPseudo(pseudoField);
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
        return false;
    }

}