package com.codespark.springboottesting.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class ArgsMatcherTests {

    @Test
    void testArgumentMatcher() {
        List<String> mockList = mock(List.class); // Create mock object
        when(mockList.get(anyInt())).thenReturn("Mockito"); // Mock with argument as any integer

        assertEquals("Mockito", mockList.get(0));
        assertEquals("Mockito", mockList.get(1));
        assertEquals("Mockito", mockList.get(2));
    }

    @Test
    void testArgumentMatcher_Error() {
        List<String> mockList = mock(List.class); // Create mock object
        when(mockList.get(anyInt())).thenThrow(new RuntimeException("Error")); // Mock with argument as any integer

        // Assert exception
        Exception ex = assertThrows(RuntimeException.class, () -> {
            mockList.get(0);
        });
        assertEquals("Error", ex.getMessage());
    }

}
