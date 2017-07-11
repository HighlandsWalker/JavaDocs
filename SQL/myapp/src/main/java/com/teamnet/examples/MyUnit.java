package com.teamnet.examples;

import java.util.Random;

/**
 * Created by Gabriel.Tabus on 7/10/2017.
 */
public class MyUnit {
    public String concatenate(String one, String two) {
        if (one == null && two == null) {
            return null;
        } else if (one == null) {
            return two;
        } else if (two == null) {
            return one;
        }
        return one + two;
    }

    public Boolean getTheBoolean() {
        return new Random().nextBoolean();
    }
}
