package tracker;

import java.util.List;
import java.util.UUID;

public class Student {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;

    private int javaPoints, dsaPoints, databasesPoints, springPoints;


    public Student(String firstName,
                   String lastName,
                   String email,
                   String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        javaPoints = dsaPoints = databasesPoints = springPoints = 0;
    }

    public void addPoints(int[] inputs) {
        javaPoints = javaPoints + inputs[0];
        dsaPoints = dsaPoints + inputs[1];
        databasesPoints = databasesPoints +inputs[2];
        springPoints = springPoints + inputs[3];
    }

    public String getPoints() {
        return String.format("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d", id, javaPoints, dsaPoints, databasesPoints, springPoints);
    }

    public int[] returnPointsInArray(){
        return new int[]{javaPoints, dsaPoints, databasesPoints, springPoints};
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
