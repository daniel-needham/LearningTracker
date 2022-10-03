package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.StatisticsTest.*;

class NotifyTest {

    StudentManager studentManager;
    Notify notify;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        studentManager = new StudentManager();
        notify = new Notify(studentManager);
        System.setOut(new PrintStream(outputStreamCaptor));
        for (String input : userArgumentFactory()) {
            String[] inputSplit = input.split(" ");
            studentManager.addToList(inputSplit[0], inputSplit[1], inputSplit[2]);
        }
        for (String input : pointsUpdateFactoryToCompleteCourses()) {
            studentManager.addPointsFromArrayForTesting(input);
        }
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void printStudentsToNotifyTest() {
        notify.printStudentsToNotify();
    }

}