package com.codespark.springboottesting.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class ExtraDemoTests {

    @Test
    void testMockStaticMethod() { // MOCK STATIC METHODS
        // Try with resources block ensure mock is automatically closed
        try (MockedStatic<EmployeeTestClass> mockedStatic = Mockito.mockStatic(EmployeeTestClass.class)) {
            // GIVEN
            mockedStatic.when(() -> EmployeeTestClass.getEmpNames(anyInt()))
                    .thenReturn(List.of("Peter", "Carl", "Tony"));

            // WHEN
            List<String> results = EmployeeTestClass.getEmpNames(123);

            // THEN
            assertEquals(List.of("Peter", "Carl", "Tony"), results);
        }
    }

    @Test
    void testMockPrivateMethods() { // MOCKING PRIVATE METHODS IS NOT POSSIBLE. HENCE WE CHANGE ACCESS MODIFIER TO
                                    // DEFAULT FOR TESTABILITY. THIS IS NOT RECOMMENDED FROM TESTING PRACTICES.
        // GIVEN
        EmployeeTestClass mockObj = new EmployeeTestClass();
        EmployeeTestClass spyObj = Mockito.spy(mockObj);

        doReturn(123).when(spyObj).getEmpId();

        // WHEN
        int result = spyObj.getEmpId();

        // THEN
        assertEquals(123, result);
    }

}

class EmployeeTestClass {
    public static List<String> getEmpNames(int id) {
        return List.of("John", "Jane", "Joe");
    }

    // private int getEmpId() {
    int getEmpId() { // CHANGE PRIVATE METHOD TO DEFAULT ACCESS MODIFIER
        return 123; // FOR TESTABILITY. ELSE MOCKING PRIVATE METHOD IS NOT POSSIBLE.
    }
}