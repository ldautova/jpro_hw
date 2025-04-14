package org.example.homework.to03_04.utils;

import org.example.homework.to03_04.annotation.AfterSuite;
import org.example.homework.to03_04.annotation.AfterTest;
import org.example.homework.to03_04.annotation.BeforeSuite;
import org.example.homework.to03_04.annotation.BeforeTest;

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
