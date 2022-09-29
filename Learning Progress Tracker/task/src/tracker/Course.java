package tracker;

import java.util.*;

public class Course {

    CourseNames courseName;
    List<Student> enrolledStudents;

    public Course(CourseNames courseName) {
        this.courseName = courseName;
        enrolledStudents = new ArrayList<>();
    }

    public void addStudentToCourse(Student student) {
        enrolledStudents.add(student);
    }
    public int amountsOfEnrolledStudents(){
        return enrolledStudents.size();
    }


}
