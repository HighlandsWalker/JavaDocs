package exercise3;

/**
 * Created by Gabriel.Tabus on 7/7/2017.
 */
public class StudentBadHashCode extends Student{
    public StudentBadHashCode(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        return lastName != null ? lastName.equals(student.lastName) : student.lastName == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
