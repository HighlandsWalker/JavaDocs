package com.java_8_training.problems.lambdas.func_interface;


import com.java_8_training.problems.lambdas.model.User;

public interface UserFieldValidator {
    /**
     * Validates a field
     * @return null if the field is valid, a String containing the error if it is not
     */
    String validate(User user);

}
