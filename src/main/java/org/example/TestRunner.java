package org.example;

import org.example.annotation.AfterSuite;
import org.example.annotation.AfterTest;
import org.example.annotation.BeforeSuite;
import org.example.annotation.BeforeTest;
import org.example.annotation.CsvSource;
import org.example.annotation.Test;
import org.example.utils.ValidationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;


/**
 * TestRunner.
 *
 * @author Lina_Dautova
 */
public class TestRunner {
    public static void runTests(Class c) {
        ValidationUtils.validate(c);

        invokeMethodByAnnotation(c.getMethods(), BeforeSuite.class);

        Object instance = getInstance(c);

        Arrays.stream(c.getMethods())
                .filter(m -> m.isAnnotationPresent(Test.class) || m.isAnnotationPresent(CsvSource.class))
                .sorted(Comparator.comparing(v -> !v.isAnnotationPresent(CsvSource.class) ? v.getAnnotation(Test.class).priority() : 0))
                .forEach(m -> {
                    try {
                        invokeMethodByAnnotation(c.getMethods(), BeforeTest.class);

                        invokeTestMethod(instance, m);

                        invokeMethodByAnnotation(c.getMethods(), AfterTest.class);
                    } catch (Exception e) {
                        System.out.println("Error on invoke Test: " + m.getName() + "::: " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                });

        invokeMethodByAnnotation(c.getMethods(), AfterSuite.class);
    }

    private static Object getInstance(Class c) {
        Constructor constructor = c.getConstructors()[0];

        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void invokeTestMethod(Object instance, Method m) throws InvocationTargetException, IllegalAccessException {
        if (m.isAnnotationPresent(CsvSource.class)) {
            String value = m.getAnnotation(CsvSource.class).value();
            m.invoke(instance, parseCsvSourceParams(value));
        } else {
            m.invoke(instance);
        }
    }

    private static Object[] parseCsvSourceParams(String value) {
        try {
            if (value.contains(",")) {
                String[] split = value.split(",");
                if (split.length == 4) {
                    return Arrays.asList(Integer.valueOf(split[0].trim()),
                            split[1].trim(),
                            Integer.valueOf(split[2].trim()),
                            Boolean.valueOf(split[3].trim())
                    ).toArray();
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to parse csv parameter: " + value + "::: " + e.getMessage());
        }
        throw new IllegalArgumentException("Invalid csv parameter: " + value);
    }

    private static <T extends Annotation> void invokeMethodByAnnotation(Method[] methods, Class<T> tClass) {
        Method method = Arrays.stream(methods)
                .filter(v -> v.isAnnotationPresent(tClass))
                .findFirst()
                .orElse(null);
        if (method != null) {
            try {
                method.invoke(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}