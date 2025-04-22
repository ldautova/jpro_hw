package org.example.homework.utils;

import org.example.homework.annotation.AfterSuite;
import org.example.homework.annotation.AfterTest;
import org.example.homework.annotation.BeforeSuite;
import org.example.homework.annotation.BeforeTest;
import org.example.homework.annotation.CsvSource;
import org.example.homework.annotation.Test;
import org.example.homework.domain.MethodClass;
import org.example.homework.exeption.InvalidInitClassException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * AnalyzeClassUtils.
 *
 * @author Lina_Dautova
 */
public class AnalyzeClassUtils {
    public static MethodClass analyze(Class c) {
        Method[] methods = c.getMethods();
        if (methods.length == 0) {
            throw new IllegalArgumentException("No methods in class " + c.getName());
        }

        MethodClass methodClass = new MethodClass();
        var countAnnotation = new HashMap<Class<? extends Annotation>, Integer>();
        for (Method method : methods) {

            if (method.isAnnotationPresent(Test.class)) {
                if (method.getAnnotation(Test.class).priority() < 1 || method.getAnnotation(Test.class).priority() > 10) {
                    throw new InvalidInitClassException(
                            String.format("Method = %s is annotated with @Test priority not in [1-10]", method.getName()));
                } else {
                    methodClass.addTest(method);
                }
            }
            if (method.isAnnotationPresent(CsvSource.class)) {
                methodClass.addTest(method);
            }
            if (method.isAnnotationPresent(AfterTest.class)) {
                checkStatic(method);
                methodClass.addAfterTest(method);
            }
            if (method.isAnnotationPresent(BeforeTest.class)) {
                checkStatic(method);
                methodClass.addBeforeTest(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                checkStatic(method);
                methodClass.setBeforeSuite(method);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                checkStatic(method);
                methodClass.setAfterSuite(method);
            }
            for (var entry : Constants.RULES.entrySet()) {
                if (method.isAnnotationPresent(entry.getKey())) {
                    var beforeCount = countAnnotation.getOrDefault(entry.getKey(), 0);
                    countAnnotation.put(entry.getKey(), beforeCount + 1);
                }
            }
        }
        methodClass.setCountAnnotations(countAnnotation);
        return methodClass;
    }

    private static void checkStatic(Method method) {
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new InvalidInitClassException(String.format("Method = %s is not static", method.getName()));
        }
    }
}
