package org.example;

import org.example.annotation.CsvSource;
import org.example.annotation.Test;
import org.example.domain.MethodClass;
import org.example.utils.AnalyzeClassUtils;
import org.example.utils.ValidationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


/**
 * TestRunner.
 *
 * @author Lina_Dautova
 */
public class TestRunner {
    public static void runTests(Class c) {
        MethodClass methodClass = AnalyzeClassUtils.analyze(c);
        ValidationUtils.validate(methodClass);

        Object instance = getInstance(c);
        invokeMethod(instance, methodClass.getBeforeSuite());
        if (!methodClass.getTests().isEmpty()) {
            for (Method testMethod : sortTestMethod(methodClass.getTests())) {
                invokeMethods(instance, methodClass.getBeforeTest());
                invokeMethod(instance, testMethod);
                invokeMethods(instance, methodClass.getAfterTest());
            }
        }
        invokeMethod(instance, methodClass.getAfterSuite());
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

    private static List<Method> sortTestMethod(List<Method> tests) {
        return tests.stream()
                .sorted(Comparator.comparingInt(v -> !v.isAnnotationPresent(CsvSource.class) ? v.getAnnotation(Test.class).priority() : 0))
                .toList();
    }

    private static void invokeMethods(Object instance, List<Method> methods) {
        if (!methods.isEmpty()) {
            for (Method method : methods) {
                invokeMethod(instance, method);
            }
        }
    }

    private static void invokeMethod(Object instance, Method method) {
        if (Objects.nonNull(method)) {
            try {
                if (method.isAnnotationPresent(CsvSource.class)) {
                    String value = method.getAnnotation(CsvSource.class).value();
                    Object[] params = parseCsvSourceParams(value);
                    if (Objects.nonNull(params)) {
                        method.invoke(instance, params);
                    } else {
                        System.out.println("Invalid csv parameter: " + value);
                    }
                } else {
                    method.invoke(instance);
                }
            } catch (Exception e) {
                System.out.println("Error on invoke : " + method.getName() + "::: " + e.getMessage());
                throw new RuntimeException(e);
            }
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
        return null;
    }
}