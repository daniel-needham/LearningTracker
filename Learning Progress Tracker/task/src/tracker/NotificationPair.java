package tracker;

import java.util.Objects;

public class NotificationPair {

    private final Student student;
    private final CourseNames course;
    private boolean notified;


    public NotificationPair(Student student, CourseNames course) {
        this.student = student;
        this.course = course;
        notified = false;
    }

    public Student getStudent() {
        return student;
    }

    public CourseNames getCourse() {
        return course;
    }

    public boolean notified() {
        return notified;
    }

    public void studentNotifiedTrue(){
        notified = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationPair that = (NotificationPair) o;
        return Objects.equals(this.getStudent(),that.getStudent()) && Objects.equals(this.getCourse(),that.getCourse());
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }


}
