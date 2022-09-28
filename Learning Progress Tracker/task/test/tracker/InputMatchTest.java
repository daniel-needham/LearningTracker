package tracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static tracker.InputMatch.*;

class InputMatchTest {



    @ParameterizedTest(name = "{index} => {0} == true")
    @CsvSource({"Steve", "Steve-sing", "Stevey's"})
    void testFirstNameMatchTrue(String name) {
        Assertions.assertTrue(matchFirstName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == false")
    @CsvSource({"'", "S.", ".", "1313131", "tom-"})
    void testFirstNameMatchFalse(String name) {
        Assertions.assertFalse(matchFirstName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == true")
    @CsvSource({"O'reilly", "Reilly", "Double-barrel", "cheese stick", "c-heese 'stick oman"})
    void testLastNameMatchTrue(String name) {
        Assertions.assertTrue(matchLastName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == false")
    @CsvSource({"-eese 'stick oman", "a", "supa'", "1313131"})
    void testLastNameMatchFalse(String name) {
        Assertions.assertFalse(matchLastName(name));

    }

    @ParameterizedTest(name = "{index} => {0} == true")
    @CsvSource({"steve50@gmail.com", "danielneedhamdn@gmail.com", "bing@bong.com", "niceguy50@msn.com"})
    void testEmailMatchTrue(String name) {
        Assertions.assertTrue(matchEmail(name));

    }

    @ParameterizedTest(name = "{index} => {0} == false")
    @CsvSource({"-eese 'stick oman", "a", "supa'", "1313131", "something@gmailcom"})
    void testEmailMatchFalse(String name) {
        Assertions.assertFalse(matchEmail(name));

    }

    @ParameterizedTest(name = "{index} => {0} == true")
    @CsvSource({"1001 5 5 5 5", "5112 0 0 0 0", "40023 1 0 2 5000", "00001 500 2 0 23"})
    void testPointsAddTrue(String input){
        Assertions.assertTrue(matchAddPoints(input));
    }

    @ParameterizedTest(name = "{index} => {0} == false")
    @CsvSource({"@:@ZXX", "5112 0 0 0 ", "40023 1 0 2 -50", "1001 1 d s"})
    void testPointsAddFalse(String input){
        Assertions.assertFalse(matchAddPoints(input));
    }

}