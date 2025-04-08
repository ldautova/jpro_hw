package org.example.utils;

import org.example.annotation.AfterSuite;
import org.example.annotation.AfterTest;
import org.example.annotation.BeforeSuite;
import org.example.annotation.BeforeTest;

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
