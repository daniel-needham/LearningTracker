package tracker;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class Statistics {

    LinkedHashSet<Student> studentSet;

    public Statistics(StudentManager studentManager) {
        studentSet = studentManager.getListOfStudents();
    }

    public void startCommandLineInterface(Scanner scanner) {
        return;
    }

    private String calculateMostPopular() {
       int[] enrolled = new int[4];
        for (Student student: studentSet) {
            int[] points = student.returnPointsInArray();
            for (int i = 0; i < points.length; i++) {
                if (points[i] > 0 ) {
                    enrolled[i]++;
                }
            }
        }

    }

    private String calculateLeastPopular(){

    }

    private String calculateHighestActivity(){

    }

    private String calculateLowestActivity(){

    }

    private String easiestCourse(){

    }

    private String hardestCourse(){

    }
}
