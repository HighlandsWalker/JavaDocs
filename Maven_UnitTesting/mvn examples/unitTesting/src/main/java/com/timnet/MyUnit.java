package com.timnet;

public class MyUnit {
    public String concatenate(String one, String two){
        return one + two;
    }

    String[] getTheStringArray() {
        return new String[] {"one", "two", "three"};
    }

    boolean getTheBoolean() {
        return true;
    }

    Object getTheObject() {
        return new Object();
    }
    
    Object getTheSameObject() {
        return "a";
    }

    void throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }
}
