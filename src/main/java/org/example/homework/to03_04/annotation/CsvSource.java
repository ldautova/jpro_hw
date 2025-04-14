package org.example.homework.to03_04.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CsvSource.
 *
 * @author Lina_Dautova
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvSource {
    String value() default "10, Java, 20, true";
}
