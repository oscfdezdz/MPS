package es.uma.mps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
    1. constructorArrayBoundedException(): verifica que se lance una excepción IllegalArgumentException si se crea
    una instancia de ArrayBoundedQueue con un valor de capacidad negativo.
    2. putFullBoundedQueueException(): verifica que se lance una excepción FullBoundedQueueException si se intenta
    agregar un elemento a una cola llena.
    3. putIllegalArgumentException(): verifica que se lance una excepción IllegalArgumentException si se intenta
    agregar un elemento nulo a la cola.
    4. getEmptyBoundedQueueException(): verifica que se lance una excepción EmptyBoundedQueueException si se intenta
    obtener un elemento de una cola vacía.
    5. getElement(): verifica que el método get() funcione correctamente.
    6. iteratorNextNoSuchElementException(): verifica que se lance una excepción NoSuchElementException si se llama
    al método next() del iterador de la cola después de que se hayan recorrido todos los elementos de la cola.
    7. iteratorMethods(): verifica que los métodos hasNext() y next() del iterador de la cola funcionen correctamente.
    8. PrivateFields: Esta clase anidada contiene pruebas para cada uno de los campos privados de la clase
    ArrayBoundedQueue, que se establecen mediante el uso de la clase ReflectionTestUtils de Spring. Cada método de
    prueba establece un valor para el campo correspondiente y luego verifica que se haya establecido correctamente.
        8.1. setThreeObjectsBuffer: esta prueba establece un nuevo array de tamaño 3 para el campo privado buffer de la
        instancia de ArrayBoundedQueue y luego verifica que se haya establecido correctamente.
        8.2. setFirstToNine: esta prueba establece el campo privado first de la instancia de ArrayBoundedQueue en 9 y
        luego verifica que se haya establecido correctamente.
        8.3. setNextFreeToThree: esta prueba establece el campo privado nextFree de la instancia de ArrayBoundedQueue
        en 3 y luego verifica que se haya establecido correctamente.
        8.4. setSizeToOne: esta prueba establece el campo privado size de la instancia de ArrayBoundedQueue en 1 y luego
        verifica que se haya establecido correctamente.
        8.5. setAdvanceWhenIndexIsNMinusOne: esta prueba llama al método privado advance de la instancia de
        ArrayBoundedQueue con un índice de 9 (n-1) y verifica que devuelve 0, que es el índice esperado después de
        avanzar en un array circular.
        8.6. setIteratorVisitedToZero: esta prueba establece el campo privado visited de la instancia de
        Iterator<Integer> devuelta por ArrayBoundedQueue.iterator() en 0 y luego verifica que se haya establecido
        correctamente.
 */

class ArrayBoundedQueueTest {
    private BoundedQueue<Integer> arrayBoundedQueue;

    @Test
    void constructorArrayBoundedException() {
        int capacityValue = -1;
        String exMessage = "ArrayBoundedException: capacity must be positive";

        assertThatThrownBy(() -> new ArrayBoundedQueue<Integer>(capacityValue)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(exMessage);
    }

    @Test
    void putFullBoundedQueueException() {
        arrayBoundedQueue = new ArrayBoundedQueue<>(10);
        String exMessage = "put: full bounded queue";
        int value = 0;

        for (int i = 0; i < 10; i++) {
            arrayBoundedQueue.put(i);
        }

        assertThatThrownBy(() -> arrayBoundedQueue.put(value)).isInstanceOf(FullBoundedQueueException.class).hasMessage(exMessage);
    }

    @Test
    void putIllegalArgumentException() {
        arrayBoundedQueue = new ArrayBoundedQueue<>(10);
        String exMessage = "put: element cannot be null";

        for (int i = 0; i < 9; i++) {
            arrayBoundedQueue.put(i);
        }

        assertThatThrownBy(() -> arrayBoundedQueue.put(null)).isInstanceOf(IllegalArgumentException.class).hasMessage(exMessage);
    }

    @Test
    void getEmptyBoundedQueueException() {
        arrayBoundedQueue = new ArrayBoundedQueue<>(10);
        String exMessage = "get: empty bounded queue";

        assertThatThrownBy(() -> arrayBoundedQueue.get()).isInstanceOf(EmptyBoundedQueueException.class).hasMessage(exMessage);
    }

    @Test
    void getElement() {
        arrayBoundedQueue = new ArrayBoundedQueue<>(10);
        Iterator<Integer> iterator = arrayBoundedQueue.iterator();
        int value = 0;
        int expectedValue = 0;

        arrayBoundedQueue.put(value);
        arrayBoundedQueue.put(value + 1);

        assertThat(arrayBoundedQueue.get()).isEqualTo(expectedValue).isNotNull();
        assertThat(arrayBoundedQueue.isEmpty()).isFalse();
        assertThat(arrayBoundedQueue.isFull()).isFalse();
        assertThat(iterator.next()).isNull();
    }

    @Test
    void iteratorNextNoSuchElementException() {
        arrayBoundedQueue = new ArrayBoundedQueue<>(10);
        Iterator<Integer> iterator = arrayBoundedQueue.iterator();
        String exMessage = "next: bounded queue iterator exhausted";

        assertThatThrownBy(() -> {
            int i = 0;

            while (i < 11) {
                iterator.next();
                i++;
            }
        }).isInstanceOf(NoSuchElementException.class).hasMessage(exMessage);
    }

    @Test
    void iteratorMethods() {
        arrayBoundedQueue = new ArrayBoundedQueue<>(1);
        Iterator<Integer> iterator = arrayBoundedQueue.iterator();
        int value = 0;
        int expectedValue = 0;

        arrayBoundedQueue.put(value);

        assertThat(iterator.hasNext()).isTrue();
        assertThat(arrayBoundedQueue.isEmpty()).isFalse();
        assertThat(arrayBoundedQueue.isFull()).isTrue();
        assertThat(iterator.next()).isEqualTo(expectedValue).isNotNull();
    }

    @Nested
    class PrivateFields {
        @BeforeEach
        void setup() {
            arrayBoundedQueue = new ArrayBoundedQueue<>(10);
        }

        @Test
        void setThreeObjectsBuffer() {
            ReflectionTestUtils.setField(arrayBoundedQueue, "buffer", new Object[3]);

            assertThat(ReflectionTestUtils.getField(arrayBoundedQueue, "buffer")).isNotNull();
        }

        @Test
        void setFirstToNine() {
            int value = 9;
            int expectedValue = 9;

            ReflectionTestUtils.setField(arrayBoundedQueue, "first", value);

            assertThat(ReflectionTestUtils.getField(arrayBoundedQueue, "first")).isEqualTo(expectedValue).isNotNull();
        }

        @Test
        void setNextFreeToThree() {
            int value = 3;
            int expectedValue = 3;

            ReflectionTestUtils.setField(arrayBoundedQueue, "nextFree", value);

            assertThat(ReflectionTestUtils.getField(arrayBoundedQueue, "nextFree")).isEqualTo(expectedValue).isNotNull();
        }

        @Test
        void setSizeToOne() {
            int value = 1;
            int expectedValue = 1;

            ReflectionTestUtils.setField(arrayBoundedQueue, "size", value);

            assertThat(arrayBoundedQueue.size()).isEqualTo(expectedValue).isNotNull();
        }

        @Test
        void setAdvanceWhenIndexIsNMinusOne() {
            int index = 9;
            int expectedIndex = 0;

            assertThat(Objects.equals(ReflectionTestUtils.invokeMethod(arrayBoundedQueue, "advance", index),
                    expectedIndex)).isTrue();
        }

        @Test
        void setIteratorVisitedToZero() {
            Iterator<Integer> iterator = arrayBoundedQueue.iterator();
            int value = 0;
            int expectedValue = 0;

            ReflectionTestUtils.setField(iterator, "visited", value);

            assertThat(ReflectionTestUtils.getField(iterator, "visited")).isEqualTo(expectedValue).isNotNull();
        }

        @Test
        void setIteratorCurrentToOne() {
            Iterator<Integer> iterator = arrayBoundedQueue.iterator();

            ReflectionTestUtils.setField(iterator, "current", 1);

            assertThat(ReflectionTestUtils.getField(iterator, "current")).isEqualTo(1).isNotNull();
        }
    }
}