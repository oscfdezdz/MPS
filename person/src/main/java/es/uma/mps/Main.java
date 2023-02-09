package es.uma.mps;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Ana", 54, "Female");
        Person person2 = new Person("Bea", 20, "Female");

        List<Person> persons = new ArrayList<>();

        persons.add(person1);
        persons.add(person2);

        System.out.println(person1.averageAgePerGender(persons)[0] + " " + person1.averageAgePerGender(persons)[1]);
    }
}