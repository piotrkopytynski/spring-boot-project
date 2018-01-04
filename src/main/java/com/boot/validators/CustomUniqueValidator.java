package com.boot.validators;

import com.boot.dao.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
public class CustomUniqueValidator implements ConstraintValidator<CustomUniqueValidator.Unique, String> {

    private Class<? extends AbstractDao> dao;

    private String fieldName;

    @Constraint(validatedBy = CustomUniqueValidator.class)
    @Target( { ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Unique {

        String message() default "Provided unique value already in use";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
        Class<? extends AbstractDao> dao();
        String fieldName();
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void initialize(final Unique unique) {
        this.dao = unique.dao();
        this.fieldName = unique.fieldName();
    }

    @Override
    public boolean isValid(final String uniqueValue, final ConstraintValidatorContext cxt) {
        return !applicationContext.getBean(dao).findByUniqueField(fieldName, uniqueValue).isPresent();
    }
}