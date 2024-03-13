package com.grp3.bid.validators;

import com.grp3.bid.constraints.UniquePseudoConstraint;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class UniquePseudoValidator implements
        ConstraintValidator<UniquePseudoConstraint, String> {
    @Autowired
    UserService userService;

    @Override
    public void initialize(UniquePseudoConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String pseudoField, ConstraintValidatorContext cxt) {
        try {
            userService.getUserByPseudo(pseudoField);
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
        return false;
    }

}