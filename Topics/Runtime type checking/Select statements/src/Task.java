// You can experiment here, it won’t be checked

public class Task {
  public static void main(String[] args) {
    // put your code here
    class Person {

    }
    class Employee extends Person {

    }

    Person p1 = new Employee();
    System.out.println(p1 instanceof Person);
    System.out.println(p1.getClass() == Person.class);
  }
}
