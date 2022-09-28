import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Person, Integer> map = new HashMap();
        map.put(new Person(scanner.nextLine()), 1995);
        map.put(new Person(scanner.nextLine()), 1995);

        System.out.println(map.size());
    }
}

class Person {

    private String name;

    public Person(String name) {
       this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}