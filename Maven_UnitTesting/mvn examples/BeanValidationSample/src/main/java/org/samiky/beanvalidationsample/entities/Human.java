package org.samiky.beanvalidationsample.entities;

/**
 *
 * @author misandu
 */
public class Human {
    
    private Gender gender;
    
    private String name;
    
    private int age;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
