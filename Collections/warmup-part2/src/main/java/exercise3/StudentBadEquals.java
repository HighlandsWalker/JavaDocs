package exercise3;

/**
 * Created by Gabriel.Tabus on 7/7/2017.
 */
public class StudentBadEquals extends Student {
    public StudentBadEquals(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
