package tracker;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Statistics statistics = new Statistics(studentManager);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        entryLoop:
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            //no input
            if (command.isEmpty() || command.isBlank()) {
                System.out.println("No input");
                continue;
            }
            //back
            if (Objects.equals(command.toLowerCase(), "back")){
                System.out.println("Enter 'exit' to exit the program.");
                continue;
            }
            //add students start
            if (Objects.equals(command.toLowerCase(), "add students")) {
                studentManager.addStudentFromCommandLine(scanner);
                continue;
            }
            //list students
            if (Objects.equals(command.toLowerCase(), "list")) {
                studentManager.listAllStudents();
                continue;
            }
            //add points
            if (Objects.equals(command.toLowerCase(), "add points")) {
                studentManager.addPointsFromCommandLine(scanner);
                continue;
            }
            //find
            if (Objects.equals(command.toLowerCase(), "find")) {
                studentManager.findStudentFromCommandLine(scanner);
                continue;
            }
            //statistics
            if (Objects.equals(command.toLowerCase(), "statistics")) {
                statistics.startCommandLineInterface(scanner);
                continue;
            }
            //exit
            if (Objects.equals(command.toLowerCase(), "exit")) {
                System.out.println("Bye!");
                break;
            }
            System.out.println("Unknown command!");
        }

    }
}