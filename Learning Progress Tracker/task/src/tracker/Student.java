package tracker;

public class Student {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;

    private final CoursePoints points;


    public Student(String firstName,
                   String lastName,
                   String email,
                   String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        points = new CoursePoints();
    }

    public String getPointsAsString() {
        return String.format("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d", id, points.getCoursePoints(CourseNames.JAVA),
                points.getCoursePoints(CourseNames.DSA), points.getCoursePoints(CourseNames.DATABASES), points.getCoursePoints(CourseNames.SPRING));
    }

    public int[] returnPointsInArray() {
        return points.getCoursePointsArray();
    }

    public String getId() {
        return id;
    }

    public int getIdInt() {
        return Integer.parseInt(id);
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

    public CoursePoints getPoints() {
        return points;
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
