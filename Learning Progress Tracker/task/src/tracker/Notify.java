package tracker;

import java.io.FilterOutputStream;
import java.util.*;
import static java.util.stream.Collectors.toList;


public class Notify {

    final StudentManager studentManager;
    private List<NotificationPair> listOfCompletedStudents;

    public Notify(StudentManager studentManager) {
        this.studentManager = studentManager;
        listOfCompletedStudents = new ArrayList<>();
    }

    public void printStudentsToNotify() {
        populateListOfCompletedStudents();
        List<NotificationPair> needToNotifyList = listOfCompletedStudents.stream()
                .filter((tuple) -> !tuple.notified()).collect(toList());
        needToNotifyList.forEach((notificationPair -> {
            System.out.printf("To: %s \nRe: Your Learning Progress\nHello, %s %s! You have accomplished our %s course!",
                    notificationPair.getStudent().getEmail(),notificationPair.getStudent().getFirstName(),
                    notificationPair.getStudent().getLastName(), notificationPair.getCourse().label);
            System.out.println();
        }));
        long studentsNotifued = needToNotifyList.stream()
                        .map((NotificationPair::getStudent))
                        .distinct()
                                .count();

        System.out.printf("Total %d students have been notified.", studentsNotifued);
        needToNotifyList.forEach((NotificationPair::studentNotifiedTrue));
    }

    private void populateListOfCompletedStudents() {
            for (Student student : studentManager.getStudentSet()) {
                for (CourseNames course : CourseNames.values()) {
                    if (student.getPoints().getCoursePoints(course) >= studentManager.getCurrentCoursesMap().get(course).completedPoints) {
                        NotificationPair courseComplete = new NotificationPair(student,course);
                        if (!listOfCompletedStudents.contains(courseComplete)) {
                            listOfCompletedStudents.add(courseComplete);
                        }
                    }
                }
            }
    }

}
