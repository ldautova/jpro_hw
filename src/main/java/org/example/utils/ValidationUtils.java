package org.example.utils;

import org.example.annotation.AfterSuite;
import org.example.annotation.AfterTest;
import org.example.annotation.BeforeSuite;
import org.example.annotation.BeforeTest;
import org.example.exeption.InvalidInitClassException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * ValidationUtils.
 *
 * @author Lina_Dautova
 */
public class ValidationUtils {
    public static void validate(Class c) {
        Method[] methods = c.getMethods();
        if (methods.length == 0) {
            throw new IllegalArgumentException("No methods in class " + c.getName());
        }

        checkStaticCLass(methods, BeforeSuite.class);
        checkStaticCLass(methods, AfterSuite.class);
        checkStaticCLass(methods, BeforeTest.class);
        checkStaticCLass(methods, AfterTest.class);
    }

    private static <T> void checkStaticCLass(Method[] methods, Class<T> tClass) {
        long count = Arrays.stream(methods)
                .filter(v -> v.isAnnotationPresent(BeforeSuite.class) && Modifier.isStatic(tClass.getModifiers()))
                .count();
        if (count > 1) {
            throw new InvalidInitClassException(String.format("%s is present more than one", tClass.getName()));
        }
    }
}
