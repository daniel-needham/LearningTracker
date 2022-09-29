package tracker;

import java.util.*;

public class Statistics {

    LinkedHashSet<Student> studentSet;
    HashMap<CourseNames, Course> currentCoursesMap;


    public Statistics(StudentManager studentManager) {

        studentSet = studentManager.getStudentSet();
        currentCoursesMap = studentManager.getCurrentCoursesMap();
    }

    public void startCommandLineInterface(Scanner scanner) {
        System.out.println(String.format("""
                        Most popular: %s\s
                        Least popular: %s\s
                        Highest activity: \s
                        Lowest activity: \s
                        Easiest course: \s
                        Hardest course:""",
        calculateMostPopular(),calculateLeastPopular()));


    }

    private String calculateMostPopular() {
        String returnString;
        Optional<Map.Entry<CourseNames, Course>> mostPopular = currentCoursesMap.entrySet()
                .stream()
                .max(Comparator.comparing((Entry) -> {
                    return Entry.getValue().amountsOfEnrolledStudents();
                }));
        try {
            Map.Entry<CourseNames,Course> mostPopularEntry = mostPopular.orElseThrow(NullPointerException::new);
            returnString = mostPopularEntry.getKey().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }

    private String calculateLeastPopular(){
        String returnString;
        Optional<Map.Entry<CourseNames, Course>> leastPopular = currentCoursesMap.entrySet()
                .stream()
                .min(Comparator.comparing((Entry) -> {
                    return Entry.getValue().amountsOfEnrolledStudents();
                }));
        try {
            Map.Entry<CourseNames,Course> leastPopularEntry = leastPopular.orElseThrow(NullPointerException::new);
            returnString = leastPopularEntry.getKey().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }

   private String calculateHighestActivity(){

    }

    private String calculateLowestActivity(){

    }

    /*
    private String easiestCourse(){

    }

    private String hardestCourse(){

    }*/
}
