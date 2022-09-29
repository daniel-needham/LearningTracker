package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    StudentManager studentManager;
    Statistics statistics;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        studentManager = new StudentManager();
        statistics = new Statistics(studentManager);
        System.setOut(new PrintStream(outputStreamCaptor));
        for (String input : userArgumentFactory()) {
            String[] inputSplit = input.split(" ");
            studentManager.addToList(inputSplit[0], inputSplit[1], inputSplit[2]);
        }
        for (String input : pointsUpdateFactory()) {
            studentManager.addPointsFromArrayForTesting(input);
        }


    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    public static List<String> userArgumentFactory() {
        return List.of("dan needham dn@gmail.com", "steve needham sn@gmail.com", "joe needham jn@gmail.com", "pete needham pn@gmail.com", "loud needham ln@gmail.com");
    }

    public static List<String> pointsUpdateFactory() {
        return List.of("1001 5 5 5 0", "1002 10 100 5 0", "1003 10 0 0 0", "1004 100 4 4 0", "1005 10 5 10000 1");
    }

}