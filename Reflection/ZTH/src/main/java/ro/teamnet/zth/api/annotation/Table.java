package ro.teamnet.zth.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Gabriel.Tabus on 7/12/2017.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
    String name() default "";
}
