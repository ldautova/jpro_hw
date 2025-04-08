package org.example.utils;

import static org.example.utils.Constants.RULES;

import org.example.domain.MethodClass;
import org.example.exeption.InvalidInitClassException;

/**
 * ValidationUtils.
 *
 * @author Lina_Dautova
 */
public class ValidationUtils {
    public static void validate(MethodClass methodClass) {
        for (var rule : RULES.entrySet()) {
            if (methodClass.getCountAnnotations().containsKey(rule.getKey())) {
                if (rule.getValue() > 0 && methodClass.getCountAnnotations().get(rule.getKey()) > rule.getValue()) {
                    throw new InvalidInitClassException(String.format("%s is present more than %d", rule.getKey(), rule.getValue()));
                }
            }
        }
    }
}
