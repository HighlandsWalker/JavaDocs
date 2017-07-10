package org.samiky.beanvalidationsample.entities;

import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author misandu
 */
public class Person {
    
    @NotNull(message = "First name is mandatory")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    @Size(min = 1, max = 50, message = "First name must have between 1 and 50 characters")
    private String firstName;
    
    @NotNull(message = "Last name is mandatory")
    @Pattern(regexp = "[a-z-A-Z]*", message = "Last name has invalid characters")
    @Size(min = 1, max = 50, message = "Last name must have between 1 and 50 characters")
    private String lastName;
    
    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;
    
    @Min(1)
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
