package tracker;

public class InputMatch {

    static boolean matchFirstName(String firstName) {
        String regex = "^(?=.{2,100}$)[a-zA-Z]+(?:[-'][a-zA-Z]+)*$";
        return firstName.matches(regex);
    }

    static boolean matchLastName(String lastName) {
        String regex = "^(?=.{2,100}$)[a-zA-Z ]+(?:[-'][a-zA-Z ]+)*$";
        return lastName.matches(regex);
    }

    static boolean matchEmail(String email) {
        String emailRegex = "[a-zA-Z0-9\\.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        return email.matches(emailRegex);
    }

    static boolean matchAddPoints(String input) {
        String inputRegex = "[0-9a-zA-Z]+( [0-9]+){4}";
        return input.matches(inputRegex);
    }
}
