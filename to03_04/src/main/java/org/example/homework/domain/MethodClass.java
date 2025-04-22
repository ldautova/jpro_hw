package org.example.homework.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * MethodClass.
 *
 * @author Lina_Dautova
 */
public class MethodClass {
    private Method beforeSuite = null;
    private Method afterSuite = null;
    private final List<Method> beforeTest = new LinkedList<>();
    private final List<Method> afterTest = new LinkedList<>();
    private final List<Method> tests = new LinkedList<>();
    HashMap<Class<? extends Annotation>, Integer> countAnnotations = new HashMap<>();

    public Method getBeforeSuite() {
        return beforeSuite;
    }

    public void setBeforeSuite(Method beforeSuite) {
        this.beforeSuite = beforeSuite;
    }

    public Method getAfterSuite() {
        return afterSuite;
    }

    public void setAfterSuite(Method afterSuite) {
        this.afterSuite = afterSuite;
    }

    public List<Method> getBeforeTest() {
        return beforeTest;
    }

    public void addBeforeTest(Method method) {
        this.beforeTest.add(method);
    }

    public List<Method> getAfterTest() {
        return afterTest;
    }

    public void addAfterTest(Method method) {
        this.afterTest.add(method);

    }

    public List<Method> getTests() {
        return tests;
    }

    public void addTest(Method method) {
        this.tests.add(method);
    }

    public HashMap<Class<? extends Annotation>, Integer> getCountAnnotations() {
        return countAnnotations;
    }

    public void setCountAnnotations(HashMap<Class<? extends Annotation>, Integer> countAnnotations) {
        this.countAnnotations = countAnnotations;
    }
}
