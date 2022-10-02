package tracker;

import java.util.*;

public class Course {

    CourseNames courseName;
    int completedPoints;
    private List<Student> enrolledStudents;

    public Course(CourseNames courseName) {
        this.courseName = courseName;
        enrolledStudents = new ArrayList<>();
        this.completedPoints = courseName.completedPoints;
    }

    public void addStudentToCourse(Student student) {
        enrolledStudents.add(student);
    }
    public int amountsOfEnrolledStudents(){
        return enrolledStudents.size();
    }

    public CourseNames getCourseName() {
        return courseName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getTotalAssignmentsForCourse() {
        int totalAssignments= 0;
        for (Student student : getEnrolledStudents()) {
            for (CourseNames name : CourseNames.values()) {
                totalAssignments = totalAssignments + student.getPoints().getCourseAssignments(name);
            }
        }
        return totalAssignments;
    }
}
