package com.grp3.bid.validators;

import com.grp3.bid.Utils.AuthenticationFacade;
import com.grp3.bid.constraints.UniqueEmailConstraint;
import com.grp3.bid.constraints.UniquePseudoConstraint;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class UniqueEmailValidator implements
        ConstraintValidator<UniqueEmailConstraint, String> {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationFacade authenticationFacade;

    @Override
    public void initialize(UniqueEmailConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        // si l'user est connecté et n'a pas modif son email
        if (null != authenticationFacade.getConnectedUser()
                &&
                emailField.equals(authenticationFacade.getConnectedUser().getEmail())
        ) {
            return true;
        }
        if (emailField.equals(authenticationFacade.getAuthentication().getName())) {
            return true;
        }
        try {
            userService.getUserByemail(emailField);
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
        return false;
    }

}