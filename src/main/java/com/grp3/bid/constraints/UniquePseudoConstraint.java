package com.grp3.bid.constraints;

import com.grp3.bid.validators.UniquePseudoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePseudoValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePseudoConstraint {
    String message() default "Pseudo déjà utilisé";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
