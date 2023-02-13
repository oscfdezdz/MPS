package es.uma.mps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private List<Person> emptyPersons, emptyFemales, emptyMales, persons;
    private Person person;

    @BeforeEach
    void setup() {
        person = new Person("Anonymous", 30, "Male");
        Person person1 = new Person("Alberto", 20, "Male");
        Person person2 = new Person("Ana", 54, "Female");
        Person person3 = new Person("Bernardo", 33, "Male");
        Person person4 = new Person("Beatriz", 27, "Female");

        emptyPersons = new ArrayList<>();
        emptyFemales = new ArrayList<>();
        emptyMales = new ArrayList<>();
        persons = new ArrayList<>();

        emptyFemales.add(person1);
        emptyFemales.add(person3);
        emptyMales.add(person2);
        emptyMales.add(person4);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
    }

    @AfterEach
    void shutdown() {
        persons = null;
    }

    @Test
    void nameIsNotEmpty() {
        for (Person person : persons) {
            assertFalse(person.name().isEmpty() && person.name().isBlank());
        }
    }

    @Test
    void genderIsMaleOrFemale() {
        String[] expectedValues = new String[]{"Male", "Female"};

        for (Person person : persons) {
            assertTrue(person.gender().equals(expectedValues[0]) || person.gender().equals(expectedValues[1]));
        }
    }

    @Test
    void ageGreaterOrEqualThanZero() {
        for (Person person : persons) {
            assertTrue(person.age() >= 0);
        }
    }

    @Test
    void averageAgePerGenderNullPerson() {
        persons.add(null);
        persons.add(new Person("Test", -1, "Female"));
        persons.add(new Person("Test2", 10, null));

        double[] expectedResult = {26.5, 40.5};
        double[] result = person.averageAgePerGender(persons);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void averageAgePerGenderEmptyList() {
        double[] expectedResult = {0.0, 0.0};
        double[] result = person.averageAgePerGender(emptyPersons);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void averageAgePerGenderEmptyFemales() {
        double[] expectedResult = {26.5, 0.0};
        double[] result = person.averageAgePerGender(emptyFemales);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void averageAgePerGenderEmptyMales() {
        double[] expectedResult = {0.0, 40.5};
        double[] result = person.averageAgePerGender(emptyMales);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void averageAgePerGenderCompleteList() {
        double[] expectedResult = {26.5, 40.5};
        double[] result = person.averageAgePerGender(persons);

        assertArrayEquals(expectedResult, result);
    }
}