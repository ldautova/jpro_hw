package org.example.homework.utils;

import org.example.homework.annotation.AfterSuite;
import org.example.homework.annotation.AfterTest;
import org.example.homework.annotation.BeforeSuite;
import org.example.homework.annotation.BeforeTest;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Constants.
 *
 * @author Lina_Dautova
 */
public class Constants {

    public static final Map<Class<? extends Annotation>, Integer> RULES =
            Map.of(BeforeSuite.class, 1,
                    AfterSuite.class, 1,
                    BeforeTest.class, -1,
                    AfterTest.class, -1);
}
