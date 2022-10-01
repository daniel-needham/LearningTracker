package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Statistics {

    LinkedHashSet<Student> studentSet;
    HashMap<CourseNames, Course> currentCoursesMap;


    public Statistics(StudentManager studentManager) {

        studentSet = studentManager.getStudentSet();
        currentCoursesMap = studentManager.getCurrentCoursesMap();
    }

    public void startCommandLineInterface(Scanner scanner) {
        String mostPop, leastPop, highestActiv, lowestActiv, easiestCourse, hardestCourse;
        if (currentCoursesMap.values().stream().allMatch(course -> course.amountsOfEnrolledStudents() == 0)){
            mostPop = "N/A";
            leastPop = "N/A";
            highestActiv = "N/A";
            lowestActiv = "N/A";
            easiestCourse = "N/A";
            hardestCourse = "N/A";
        } else {
            mostPop = calculateMostPopular();
            leastPop = calculateLeastPopular();
            highestActiv = calculateHighestActivity();
            lowestActiv = calculateLowestActivity();
            easiestCourse = calculateEasiestCourse();
            hardestCourse = calculateHardestCourse();
        }
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println(String.format("""
                        Most popular: %s\s
                        Least popular: %s\s
                        Highest activity: %s\s
                        Lowest activity: %s\s
                        Easiest course: %s\s
                        Hardest course: %s""",
                mostPop, leastPop, highestActiv, lowestActiv, easiestCourse, hardestCourse));

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            //back
            if (Objects.equals(command.toLowerCase(), "back")) {
                break;
            }

            //print stats on
            for (CourseNames course : CourseNames.values()) {
                if (command.equals(course.label)) {
                    printTopLearners(course);
                }
            }

            //inputted course doesn't match
            System.out.println("Unknown course.");
        }
    }

    public String calculateMostPopular() {
        String returnString;
        Optional<Course> mostPopular = currentCoursesMap.values()
                .stream()
                .filter((course) -> course.amountsOfEnrolledStudents() != 0)
                .max(Comparator.comparing(Course::amountsOfEnrolledStudents));
        try {
            Course mostPopularEntry = mostPopular.orElseThrow(NullPointerException::new);
            returnString = mostPopularEntry.getCourseName().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }

    public String calculateLeastPopular() {
        String returnString;
        Optional<Course> leastPopular = currentCoursesMap.values()
                .stream()
                .min(Comparator.comparing(Course::amountsOfEnrolledStudents));
        try {
            Course leastPopularEntry = leastPopular.orElseThrow(NullPointerException::new);
            returnString = leastPopularEntry.getCourseName().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }

    public String calculateHighestActivity() {
        Optional<Course> highestActivityOptional = currentCoursesMap.values().stream()
                .max(Comparator.comparing((course) -> {
                    int totalAssignments = 0;
                    for (Student student : course.getEnrolledStudents()) {
                        for (CourseNames name : CourseNames.values()) {
                            totalAssignments = totalAssignments + student.getPoints().getCourseAssignments(name);
                        }
                    }
                    return totalAssignments;
                }));
        String returnString;
        try {
            Course highestActivity = highestActivityOptional.orElseThrow(NullPointerException::new);
            returnString = highestActivity.getCourseName().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }

    public String calculateLowestActivity() {
        Optional<Course> lowestActivityOptional = currentCoursesMap.values().stream()
                .min(Comparator.comparing((course) -> {
                    int totalAssignments = 0;
                    for (Student student : course.getEnrolledStudents()) {
                        for (CourseNames name : CourseNames.values()) {
                            totalAssignments = totalAssignments + student.getPoints().getCourseAssignments(name);
                        }
                    }
                    return totalAssignments;
                }));
        String returnString;
        try {
            Course lowestActivity = lowestActivityOptional.orElseThrow(NullPointerException::new);
            returnString = lowestActivity.getCourseName().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }


    public String calculateEasiestCourse() {
        Optional<Map.Entry<CourseNames, Integer>> maxOptional = getTotalAverageAssignmentScoreHashMap().entrySet().stream()
                .filter((entry) -> entry.getValue() != 0)
                .max(Comparator.comparing(Map.Entry::getValue));
        String returnString;
        try {
            Map.Entry<CourseNames, Integer> max = maxOptional.orElseThrow(NullPointerException::new);
            returnString = max.getKey().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }

    public String calculateHardestCourse() {
        Optional<Map.Entry<CourseNames, Integer>> minOptional = getTotalAverageAssignmentScoreHashMap().entrySet().stream()
                .filter((entry) -> entry.getValue() != 0)
                .min(Comparator.comparing(Map.Entry::getValue));
        String returnString;
        try {
            Map.Entry<CourseNames, Integer> min = minOptional.orElseThrow(NullPointerException::new);
            returnString = min.getKey().label;
        } catch (NullPointerException e) {
            returnString = "N/A";
        }
        return returnString;
    }

    private HashMap<CourseNames, int[]> createCourseNamesHashMapArr() {
        HashMap<CourseNames, int[]> averageScorePerAssignment = new HashMap<>();
        averageScorePerAssignment.put(CourseNames.JAVA, new int[]{0, 0});
        averageScorePerAssignment.put(CourseNames.DSA, new int[]{0, 0});
        averageScorePerAssignment.put(CourseNames.DATABASES, new int[]{0, 0});
        averageScorePerAssignment.put(CourseNames.SPRING, new int[]{0, 0});
        return averageScorePerAssignment;
    }

    private HashMap<CourseNames, Integer> createCourseNamesHashMapInt() {
        HashMap<CourseNames, Integer> averageScorePerAssignment = new HashMap<>();
        averageScorePerAssignment.put(CourseNames.JAVA, 0);
        averageScorePerAssignment.put(CourseNames.DSA, 0);
        averageScorePerAssignment.put(CourseNames.DATABASES, 0);
        averageScorePerAssignment.put(CourseNames.SPRING, 0);
        return averageScorePerAssignment;
    }

    private HashMap<CourseNames, Integer> getTotalAverageAssignmentScoreHashMap() {
        HashMap<CourseNames, int[]> pointsAssignmentsMap = createCourseNamesHashMapArr();
        for (Student student : studentSet) {
            for (CourseNames course : CourseNames.values()) {
                pointsAssignmentsMap.computeIfPresent(course, (key, val) -> {
                    val[0] = val[0] + student.getPoints().getCoursePoints(course);
                    val[1] = val[1] + student.getPoints().getCourseAssignments(course);
                    return val;
                });
            }
        }
        HashMap<CourseNames, Integer> averagePointsPerAssignmentMap = createCourseNamesHashMapInt();
        for (CourseNames course: CourseNames.values()) {
            int average = 0;
            if (pointsAssignmentsMap.get(course)[1] > 0) {
                average = pointsAssignmentsMap.get(course)[0] / pointsAssignmentsMap.get(course)[1];
            }
            averagePointsPerAssignmentMap.put(course,average);
        }
        return averagePointsPerAssignmentMap;
    }

    public void printTopLearners(CourseNames course) {
        Course selectedCourse = currentCoursesMap.get(course);
        List<Student> enrolledStudents = selectedCourse.getEnrolledStudents();
        enrolledStudents.sort(new topLearnerComparator(course));
        //print
        System.out.println(course.label);
        System.out.println(String.format("id\tpoints\tcompleted"));
        for (Student student: enrolledStudents) {
            int totalPoints = student.getPoints().getCoursePoints(course);
            double d = (double) totalPoints / (double) selectedCourse.completedPoints;
            BigDecimal decimal = new BigDecimal(d).setScale(1, RoundingMode.HALF_UP);
            System.out.println(String.format("%s\t%d\t" + decimal + "%", student.getId(), totalPoints));
        }
    }
    public String returnTopLearnersTest(CourseNames course) {
        Course selectedCourse = currentCoursesMap.get(course);
        List<Student> enrolledStudents = selectedCourse.getEnrolledStudents();
        enrolledStudents.sort(new topLearnerComparator(course));
        StringBuilder sb = new StringBuilder();
        enrolledStudents.forEach(sb::append);
        return sb.toString();

    }

    private static class topLearnerComparator implements Comparator<Student> {

        CourseNames course;

        topLearnerComparator(CourseNames course) {
            this.course = course;
        }

        @Override
        public int compare(Student o1, Student o2) {

            int pointsCompare = Integer.compare(o2.getPoints().getCoursePoints(course), o1.getPoints().getCoursePoints(course));
            int idCompare = Integer.compare(o2.getIdInt(),o1.getIdInt());

            return (pointsCompare == 0) ? idCompare : pointsCompare;
        }
    }
}
