package tracker;

import java.util.*;

import static tracker.InputMatch.*;


public class StudentManager {

    private LinkedHashSet<Student> studentSet;
    private HashMap<CourseNames, Course> currentCoursesMap;


    public StudentManager() {
        studentSet = new LinkedHashSet<>();
        currentCoursesMap = new HashMap<>();
        for (CourseNames courseName : CourseNames.values()) {
            currentCoursesMap.put(courseName, new Course(courseName));
        }
    }

    public void addToList(String firstName,
                          String lastName,
                          String email) throws IllegalArgumentException {


        boolean success = studentSet.add(new Student(firstName,
                lastName,
                email, generateID()));

        if (!success) {
            throw new IllegalArgumentException();
        }
    }

    public void addStudentFromCommandLine(Scanner scanner) {
        System.out.println("Enter student credentials or 'back' to return:");
        int studentsAddedInSession = 0;
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            //back
            if (Objects.equals(command.toLowerCase(), "back")) {
                String message = "Total " + studentsAddedInSession + " students have been added";
                System.out.println(message);
                break;
            }

            String[] splitBySpace = command.split(" ");

            //incorrect credentials
            if (command.isEmpty() || command.isBlank() || splitBySpace.length < 3) {
                System.out.println("Incorrect credentials");
                continue;
            }

            //break string into parts
            String firstName = splitBySpace[0];
            String lastName = String.join(" ", Arrays.copyOfRange(splitBySpace, 1, splitBySpace.length - 1));
            String email = splitBySpace[splitBySpace.length - 1];

            //all match add student to list
            if (matchFirstName(firstName) && matchLastName(lastName) && matchEmail(email)) {
                try {
                    addToList(firstName, lastName, email);
                } catch (IllegalArgumentException e) {
                    System.out.println("This email is already taken.");
                    continue;
                }
                studentsAddedInSession++;
                System.out.println("The student has been added.");
                continue;
            }

            if (!matchFirstName(firstName)) {
                System.out.println("Incorrect first name.");
                continue;
            }

            if (!matchLastName(lastName)) {
                System.out.println("Incorrect last name.");
                continue;
            }
            if (!matchEmail(email)) {
                System.out.println("Incorrect email.");
            }

        }
    }

    public void addPointsFromCommandLine(Scanner scanner) {
        System.out.println("Enter an id and points or 'back' to return");
        System.out.println("Please enter points in the format id points points points points");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            //back
            if (Objects.equals(command.toLowerCase(), "back")) {
                break;
            }

            //input matches
            if (matchAddPoints(command)) {
                String[] inputs = command.split(" ");
                String id = inputs[0];
                try {
                    Student found = findStudentByID(id);
                    int[] inputsAsPointsArray = Arrays.stream(inputs).skip(1)
                            .mapToInt(Integer::valueOf)
                            .toArray();
                    ArrayList<CourseNames> enrolledCourses =
                            found.getPoints().addPointsFromArray(inputsAsPointsArray);
                    if (!enrolledCourses.isEmpty()) {
                        for (CourseNames courseName : enrolledCourses) {
                            currentCoursesMap.get(courseName).addStudentToCourse(found);
                        }
                    }
                    System.out.println("Points updated");
                    //id not found
                } catch (NoSuchElementException e) {
                    System.out.println(String.format("No student is found for id=%s", id));
                }
                continue;
            }

            //incorrect entry default
            System.out.println("Incorrect points format.");
        }
    }

    public void findStudentFromCommandLine(Scanner scanner) {
        System.out.println("Enter an id or 'back' to return");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            //back
            if (Objects.equals(command.toLowerCase(), "back")) {
                break;
            }

            //input in correct format
            if (command.matches("[0-9]+")) {
                try {
                    Student found = findStudentByID(command);
                    System.out.println(found.getPointsAsString());
                    continue;
                } catch (Exception ignored) {

                }
            }

            //incorrect entry default
            System.out.println(String.format("No student is found for id=%s.", command));
        }
    }


    public void listAllStudents() {
        if (studentSet.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students:");
            studentSet.forEach(System.out::println);
        }
    }

    public Student findStudentByID(String id) throws NoSuchElementException {
        return studentSet.stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public void addPointsFromArrayForTesting(String command) {
        if (matchAddPoints(command)) {
            String[] inputs = command.split(" ");
            String id = inputs[0];
            try {
                Student found = findStudentByID(id);
                int[] inputsAsPointsArray = Arrays.stream(inputs).skip(1)
                        .mapToInt(Integer::valueOf)
                        .toArray();
                ArrayList<CourseNames> enrolledCourses =
                        found.getPoints().addPointsFromArray(inputsAsPointsArray);
                if (!enrolledCourses.isEmpty()) {
                    for (CourseNames courseName : enrolledCourses) {
                        currentCoursesMap.get(courseName).addStudentToCourse(found);
                    }
                }
                System.out.println("Points updated");
                //id not found
            } catch (NoSuchElementException e) {
                System.out.println(String.format("No student is found for id=%s", id));
            }
        }
    }

    public LinkedHashSet<Student> getStudentSet() {
        return studentSet;
    }

    public HashMap<CourseNames, Course> getCurrentCoursesMap() {
        return currentCoursesMap;
    }

    private String generateID() {
        int id = studentSet.size() + 1;
        id = 1000 + id;
        return Integer.toString(id);
    }

}
