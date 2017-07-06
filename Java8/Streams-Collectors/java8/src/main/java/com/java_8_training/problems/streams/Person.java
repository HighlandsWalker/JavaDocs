package com.java_8_training.problems.streams;


public class Person {

    private String name;
    private Integer age;
    private boolean male;
    private Nationality nationality;
    private Integer salary;

    public Person(){}

    public Person(String name, Integer age, boolean male, Nationality nationality, Integer salary) {
        this.name = name;
        this.age = age;
        this.male = male;
        this.nationality = nationality;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String toString() {
        return  this.name + ", " + this.age + ", " + (isMale() ? "male" : "female") + ", " +
                this.getNationality() + ", " + this.salary;
    }

}
