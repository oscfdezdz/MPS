package es.uma.mps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockingExamplesTest {

    // Dummy
    private class ExampleClass1 {
        public void doSomethingWithList(List<String> list) {
            // do nothing
        }
    }

    @Test
    public void testDummy() {
        ExampleClass1 example = new ExampleClass1();
        List<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        example.doSomethingWithList(list);

        assertNotNull(example);
    }

    // Fake
    private class ExampleClass2 {
        public List<String> getList() {
            List<String> list = new ArrayList<>();
            list.add("item1");
            list.add("item2");
            return list;
        }
    }

    @Test
    public void testFake() {
        ExampleClass2 example = new ExampleClass2();
        List<String> list = example.getList();

        assertNotNull(list);
        assertEquals(2, list.size());
    }

    // Stub
    private interface ExampleInterface {
        int getNumber();
    }

    @Test
    public void testStub() {
        ExampleInterface example = Mockito.mock(ExampleInterface.class);
        Mockito.when(example.getNumber()).thenReturn(10);

        int number = example.getNumber();

        assertNotNull(example);
        assertEquals(10, number);
    }

    // Spy
    private class ExampleClass3 {
        public int getNumber() {
            return 5;
        }
    }

    @Test
    public void testSpy() {
        ExampleClass3 example = new ExampleClass3();
        ExampleClass3 spyExample = Mockito.spy(example);

        int number = spyExample.getNumber();

        assertNotNull(spyExample);
        assertEquals(5, number);
    }

    // Mock
    private interface ExampleService {
        String doSomething();
    }

    @Test
    public void testMock() {
        ExampleService example = Mockito.mock(ExampleService.class);
        Mockito.when(example.doSomething()).thenReturn("mock result");

        String result = example.doSomething();

        assertNotNull(example);
        assertEquals("mock result", result);
    }

}