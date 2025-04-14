package org.example.homework.to03_04.utils;

import static org.example.homework.to03_04.utils.Constants.RULES;

import org.example.homework.to03_04.domain.MethodClass;
import org.example.homework.to03_04.exeption.InvalidInitClassException;

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
