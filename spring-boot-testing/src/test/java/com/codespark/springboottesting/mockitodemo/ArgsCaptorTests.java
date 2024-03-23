package com.codespark.springboottesting.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

@SuppressWarnings("unchecked")
public class ArgsCaptorTests {

    @Test
    void testArgumentCaptor() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        List<String> mockList = mock(List.class);
        mockList.add("TEST");

        verify(mockList).add(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), "TEST");
    }

    @Test
    void testArgumentCaptor_MyController1() {
        // GIVEN
        TestService mockService = mock(TestService.class);
        TestController myController = new TestController(mockService);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // WHEN
        myController.getById("1");

        // THEN
        verify(mockService).getById(argumentCaptor.capture());
        assertEquals("U1", argumentCaptor.getValue());
    }

    @Test
    void testArgumentCaptor_MyController2() {
        // GIVEN
        TestService mockService = mock(TestService.class);
        TestController myController = new TestController(mockService);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // WHEN
        myController.getById("1");
        myController.getById("2");
        myController.getById("3");

        // THEN
        verify(mockService, times(3)).getById(argumentCaptor.capture());
        assertEquals("U1", argumentCaptor.getAllValues().get(0));
        assertEquals("U2", argumentCaptor.getAllValues().get(1));
        assertEquals("U3", argumentCaptor.getAllValues().get(2));
    }

}

class TestController {
    private TestService testService;

    public TestController(TestService myService) {
        this.testService = myService;
    }

    public List<String> getById(String id) {
        return testService.getById("U" + id);
    }
}

interface TestService {
    List<String> getById(String id);
}

class MyTestServiceImpl implements TestService {
    private static final Map<String, List<String>> data = new HashMap<>();

    static {
        data.put("U1", Arrays.asList("John", "Mary"));
        data.put("U2", Arrays.asList("Jane", "Mark"));
        data.put("U3", Arrays.asList("Jill", "Smith"));
    }

    @Override
    public List<String> getById(String id) {
        return data.get(id);
    }
}