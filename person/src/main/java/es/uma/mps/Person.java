package es.uma.mps;

import java.util.List;

/**
 * Class representing a person with a name, age and gender
 *
 * @author Óscar Fernández Díaz
 */
public class Person {
    private final String name;
    private final int age;
    private final String gender; // Male, Female

    /**
     * Constructs a person with a name, age and gender.
     *
     * @param name
     * @param age
     * @param gender
     */
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public String gender() {
        return gender;
    }

    /**
     * Computes the average age of male and female persons in a list and returns the result in
     * an array of two elements (the first element is the male mean age and the second one is the
     * female mean age)
     *
     * @param persons
     * @return
     */
    public double[] averageAgePerGender(List<Person> persons) {
        int males = 0;
        int females = 0;
        double maleAges = 0.0;
        double femaleAges = 0.0;

        for (Person person : persons) {
            if (person.gender.equals("Male")) {
                males++;
                maleAges += person.age;
            } else {
                females++;
                femaleAges += person.age;
            }
        }

        return new double[] {maleAges/males, femaleAges/females};
    }
}
