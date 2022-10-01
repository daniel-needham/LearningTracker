package tracker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StudentManagerTest {

    StudentManager studentManager;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        studentManager = new StudentManager();
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    public static List<String> userArgumentFactory() {
        return List.of("dan needham dn@gmail.com", "steve needham sn@gmail.com", "joe needham jn@gmail.com", "pete needham pn@gmail.com", "loud needham ln@gmail.com");
    }


    @Test
    void displayEmptyListTest() {
        studentManager.listAllStudents();
        assertEquals("No students found.", outputStreamCaptor.toString().trim());
    }

    @Test
    void addToListTest() {
        for (String input : userArgumentFactory()) {
            String[] inputSplit = input.split(" ");
            studentManager.addToList(inputSplit[0], inputSplit[1], inputSplit[2]);
        }
        Assertions.assertEquals(5, studentManager.getStudentSet().size());
    }

}