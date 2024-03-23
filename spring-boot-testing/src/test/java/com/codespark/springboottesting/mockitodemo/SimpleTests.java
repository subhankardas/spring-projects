package com.codespark.springboottesting.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SimpleTests {

    @Test
    void testMyControllerMock() {
        // 1. Create mock dependencies - GIVEN
        MyService mockService = mock(MyService.class);
        when(mockService.getAll()).thenReturn(List.of("A", "B"));

        // 2. Create controller using mock service
        MyController controller = new MyController(mockService);

        // 3. Call controller methods - WHEN
        List<String> actual = controller.getAll();

        // 4. Assert results - THEN
        assertEquals(List.of("A", "B", "D", "E"), actual);

        // 5. Verify service methods were called - THEN
        verify(mockService, times(1)).getAll();
        // verify(mockService, never()).getAll(); // Verify that method was never called
    }

    @Test
    void testMyControllerMockSpy() {
        // 1. Create real object to be partially mocked
        MyService serviceObj = new MyServiceImpl();

        // 2. Create spy using real object
        MyService spyService = spy(serviceObj);
        assertEquals(List.of("A", "B", "C"), spyService.getAll());

        // 3. Mock calls for spy object methods
        when(spyService.getAll()).thenReturn(List.of("D", "E"));
        assertEquals(List.of("D", "E"), spyService.getAll());
    }
}

// Business logic (SUT - System under test)
class MyController {

    private final MyService myService; // Dependency (to be mocked)

    public MyController(MyService myService) {
        this.myService = myService;
    }

    public List<String> getAll() {
        List<String> result = myService.getAll();
        result = new ArrayList<>(result);
        result.add("D");
        result.add("E");
        return result;
    }

}

// Service interface
interface MyService {

    List<String> getAll();

}

// Service implementation
class MyServiceImpl implements MyService {

    @Override
    public List<String> getAll() {
        return new ArrayList<>(Arrays.asList("A", "B", "C"));
    }

}
