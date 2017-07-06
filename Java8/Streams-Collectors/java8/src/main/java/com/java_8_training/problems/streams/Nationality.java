package com.java_8_training.problems.streams;

public class Nationality {

    private String name;

    public Nationality(){}

    public Nationality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return  this.name;
    }
}
