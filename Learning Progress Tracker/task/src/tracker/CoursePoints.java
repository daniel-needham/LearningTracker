package tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class CoursePoints {
    
    private HashMap<CourseNames, int[]> coursesPointsMap;
    
    public CoursePoints() {
        coursesPointsMap = new HashMap<>(4);
        for (CourseNames course: CourseNames.values()) {
            coursesPointsMap.put(course,new int[]{0,0});
        }
    }

    public ArrayList<CourseNames> addPointsFromArray(int[] points) throws IllegalArgumentException {
        ArrayList<CourseNames> enrolledCourses = new ArrayList<>();
        int numberOfCourses = CourseNames.values().length;
        if (points.length > numberOfCourses) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numberOfCourses; i++) {

            if (addPointsForCourse(CourseNames.values()[i], points[i])) {
                enrolledCourses.add(CourseNames.values()[i]);
            };
        }
        return enrolledCourses;
    }

    //returns true, if this is the first time student has been awarded points in that course
    private boolean addPointsForCourse(CourseNames course, int points) {
        AtomicBoolean enrolled = new AtomicBoolean(false);
        coursesPointsMap.compute(course, (key, val) -> {
            if (val[0] == 0 && points > 0) {
                enrolled.set(true);
            }
            val[0] = val[0] + points;
            val[1]++;
            return val;
        });
        return enrolled.get();
    }

    public int getCoursePoints(CourseNames course) {
        return coursesPointsMap.get(course)[0];
    }

    public int[] getCoursePointsArray() {
        return coursesPointsMap.values().stream().mapToInt((arr) -> arr[0]).toArray();
    }

    public int getCourseAssignments(CourseNames course) {
        return coursesPointsMap.get(course)[1];
    }
}
