package ro.teamnet.zth.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Gabriel.Tabus on 7/12/2017.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Id {
    String name() default "id";
}
