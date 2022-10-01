package tracker;

public enum CourseNames {

    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    public final String label;
    public final int completedPoints;

    CourseNames(String label, int completedPoints) {
        this.label = label;
        this.completedPoints = completedPoints;
    }
}
