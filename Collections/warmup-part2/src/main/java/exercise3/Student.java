package exercise3;

/**
 * Created by Gabriel.Tabus on 7/7/2017.
 */
public class Student {
    public String firstName;
    public String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // GETTERS

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    // SETTERS

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (firstName != null ? firstName.equals(student.firstName) : student.firstName != null) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
