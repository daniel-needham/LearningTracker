package tracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class studentManagerTest {

    studentManager sm = new studentManager();

    @ParameterizedTest(name = "{index} => {0} == true")
    @CsvSource({"Steve", "Steve-sing", "Stevey's"})
    void testFirstNameMatchTrue(String name) {
        Assertions.assertTrue(studentManager.matchFirstName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == false")
    @CsvSource({"'", "S.", ".", "1313131", "tom-"})
    void testFirstNameMatchFalse(String name) {
        Assertions.assertFalse(studentManager.matchFirstName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == true")
    @CsvSource({"O'reilly", "Reilly", "Double-barrel", "cheese stick", "c-heese 'stick oman"})
    void testLastNameMatchTrue(String name) {
        Assertions.assertTrue(studentManager.matchLastName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == false")
    @CsvSource({"-eese 'stick oman", "a", "supa'", "1313131"})
    void testLastNameMatchFalse(String name) {
        Assertions.assertFalse(studentManager.matchLastName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == true")
    @CsvSource({"steve50@gmail.com", "danielneedhamdn@gmail.com", "bing@bong.com", "niceguy50@msn.com"})
    void testEmailMatchTrue(String name) {
        Assertions.assertTrue(studentManager.matchEmail(name));

    }

    @ParameterizedTest(name = "{index} => {0} == false")
    @CsvSource({"-eese 'stick oman", "a", "supa'", "1313131", "something@gmailcom"})
    void testEmailMatchFalse(String name) {
        Assertions.assertFalse(studentManager.matchEmail(name));

    }


}