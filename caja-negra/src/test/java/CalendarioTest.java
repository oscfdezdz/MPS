import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static es.uma.informatica.mps.Calendario.diaSemana;
import static org.junit.jupiter.api.Assertions.*;

public class CalendarioTest {
    @Test
    @DisplayName("Valid months between 1 and 12")
    @Tag("1")
    void validMonths() {
        int dia = 2;
        int mes = 5;
        int anio = 2023;

        DayOfWeek dayOfWeek = diaSemana(dia, mes, anio);

        assertEquals(DayOfWeek.TUESDAY, dayOfWeek);
        assertDoesNotThrow(() -> dayOfWeek);
    }

    @Test
    @DisplayName("Invalid months less than 1 and greater than 12")
    @Tag("2")
    @Tag("3")
    void invalidMonths() {
        int dia = 13;
        int mes1 = -1, mes2 = 13;
        int anio = 2000;

        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia, mes1, anio));
        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia, mes2, anio));
    }

    @Test
    @DisplayName("Valid non-leap year February between 1 and 28")
    @Tag("4")
    void validNonLeapYearFebruary() {
        int dia = 28;
        int mes = 2;
        int anio = 2023;

        DayOfWeek dayOfWeek = diaSemana(dia, mes, anio);

        assertEquals(DayOfWeek.TUESDAY, dayOfWeek);
        assertDoesNotThrow(() -> dayOfWeek);
    }

    @Test
    @DisplayName("Invalid non-leap year February less than 1 and greater than 28")
    @Tag("5")
    @Tag("6")
    void invalidNonLeapYearFebruary() {
        int dia1 = -3, dia2 = 31;
        int mes = 2;
        int anio = 2023;

        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia1, mes, anio));
        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia2, mes, anio));
    }

    @Test
    @DisplayName("Valid leap year February between 1 and 29")
    @Tag("7")
    void validLeapYearFebruary() {
        int dia = 29;
        int mes = 2;
        int anio = 2024;

        DayOfWeek dayOfWeek = diaSemana(dia, mes, anio);

        assertEquals(DayOfWeek.THURSDAY, dayOfWeek);
        assertDoesNotThrow(() -> dayOfWeek);
    }

    @Test
    @DisplayName("Invalid leap year February less than 1 and greater than 29")
    @Tag("8")
    @Tag("9")
    void invalidLeapYearFebruary() {
        int dia1 = 0, dia2 = 30;
        int mes = 2;
        int anio = 2023;

        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia1, mes, anio));
        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia2, mes, anio));
    }

    @Test
    @DisplayName("Valid days in months with 30 days")
    @Tag("10")
    void validDaysMonthsWith30Days() {
        int dia = 30;
        int mes = 4;
        int anio = 2023;

        DayOfWeek dayOfWeek = diaSemana(dia, mes, anio);

        assertEquals(DayOfWeek.SUNDAY, dayOfWeek);
        assertDoesNotThrow(() -> dayOfWeek);
    }

    @Test
    @DisplayName("Invalid days in months with 30 days")
    @Tag("11")
    @Tag("12")
    void invalidDaysMonthsWith30Days() {
        int dia1 = 0, dia2 = 31;
        int mes = 4;
        int anio = 2023;

        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia1, mes, anio));
        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia2, mes, anio));
    }

    @Test
    @DisplayName("Valid days in months with 31 days")
    @Tag("13")
    void validDaysMonthsWith31Days() {
        int dia = 31;
        int mes = 1;
        int anio = 2023;

        DayOfWeek dayOfWeek = diaSemana(dia, mes, anio);

        assertEquals(DayOfWeek.TUESDAY, dayOfWeek);
        assertDoesNotThrow(() -> dayOfWeek);
    }

    @Test
    @DisplayName("Invalid days in months with 31 days")
    @Tag("14")
    @Tag("15")
    void invalidDaysMonthsWith31Days() {
        int dia1 = -2, dia2 = 32;
        int mes = 1;
        int anio = 2023;

        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia1, mes, anio));
        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia2, mes, anio));
    }

    @Test
    @DisplayName("Valid date after 1st March 4")
    @Tag("16")
    void validDate() {
        int dia = 30;
        int mes = 4;
        int anio = 2023;

        DayOfWeek dayOfWeek = diaSemana(dia, mes, anio);

        assertEquals(DayOfWeek.SUNDAY, dayOfWeek);
        assertDoesNotThrow(() -> dayOfWeek);
    }

    @Test
    @DisplayName("Invalid date")
    @Tag("17")
    @Tag("18")
    void invalidDate() {
        int dia = 10;
        int mes1 = 2, mes2 = 10;
        int anio1 = 4, anio2 = 1582;

        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia, mes1, anio1));
        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia, mes2, anio2));
    }

    @Test
    @DisplayName("Valid leap year")
    @Tag("19")
    void validLeapYear() {
        int dia = 6;
        int mes = 6;
        int anio = 2012;

        DayOfWeek dayOfWeek = diaSemana(dia, mes, anio);

        assertEquals(DayOfWeek.WEDNESDAY, dayOfWeek);
        assertDoesNotThrow(() -> dayOfWeek);
    }

    @Test
    @DisplayName("Invalid leap year")
    @Tag("20")
    @Tag("21")
    void invalidLeapYear() {
        int dia = 29;
        int mes = 2;
        int anio1 = 1700, anio2 = 2007;

        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia, mes, anio1));
        assertThrows(IllegalArgumentException.class, () -> diaSemana(dia, mes, anio2));
    }
}
