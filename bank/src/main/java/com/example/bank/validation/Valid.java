package com.example.bank.validation;

import ch.qos.logback.core.net.server.Client;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface ValidEntity {
    String message() default "{com.example.validation.ValidEntity.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class EntityValidator {

    public static boolean isValidClient(Client client) {
        return !client.getName().isEmpty() && !client.getShortName().isEmpty() && !client.getAddress().isEmpty();
    }

    public static boolean isValidBank(Bank bank) {
        return !bank.getName().isEmpty() && !bank.getBic().isEmpty();
    }

    public static boolean isValidDeposit(Deposit deposit) {
        return deposit.getClient() != null && deposit.getBank() != null && !deposit.getOpeningDate().isBefore(LocalDate.now()) && deposit.getRate() > 0 && deposit.getTermInMonths() > 0;
    }

    // Валидация для всех сущностей
    public static boolean isValid(Object entity) {
        if (entity instanceof Client) {
            return isValidClient((Client) entity);
        } else if (entity instanceof Bank) {
            return isValidBank((Bank) entity);
        } else if (entity instanceof Deposit) {
            return isValidDeposit((Deposit) entity);
        }
        return false;
    }
}