package es.uma.mps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {
    List<Person> persons;

    @BeforeEach
    void setup() {
        Person person1 = new Person("Ana", 54, "Female");
        Person person2 = new Person("Juan", 20, "Male");

        persons = new ArrayList<>();

        persons.add(person1);
        persons.add(person2);
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
}